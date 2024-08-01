package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import xd.arkosammy.edtr.driver.ContentSource
import xd.arkosammy.edtr.driver.Slice
import xd.arkosammy.edtr.util.EditingMode
import xd.arkosammy.edtr.util.ScrollDirection
import xd.arkosammy.edtr.util.TextLine
import java.lang.Math.clamp

abstract class AbstractViewFinder(
    final override val contentSource: ContentSource,
    override var size: TerminalSize,
    textLines: List<TextLine>,
    cursorPosition: TerminalPosition,
    override var editingMode: EditingMode
) : ViewFinder {

    private var scrollX: UInt = 0u
    private var scrollY: UInt = 0u

    private var currentSlice: Slice = this.contentSource.readSlice(scrollY.toInt(), scrollY.toInt() + size.rows)

    private var backingCursorPosition: TerminalPosition = cursorPosition
    override val cursorPosition: TerminalPosition by ::backingCursorPosition

    private val backingTextLines: MutableList<TextLine> = textLines.toMutableList()
    override val textLines: List<TextLine>
        get() = backingTextLines.toList()

    override fun onKeyStroke(keyStroke: KeyStroke) {

        if (keyStroke.character == 's' && keyStroke.isCtrlDown) {
            this.contentSource.writeSlice(this.currentSlice)
            this.contentSource.save()
            return
        }

        val lines: List<String> = this.currentSlice.contents.split("\n", "\r")
        val cursorX: Int = this.backingCursorPosition.column
        val cursorY: Int = this.backingCursorPosition.row
        val sizeY: Int = lines.size - 1

        val newCursorPosition: TerminalPosition? = when (keyStroke.keyType) {
            KeyType.ArrowUp -> TerminalPosition(cursorX, cursorY - 1)
            KeyType.ArrowDown -> TerminalPosition(cursorX, cursorY + 1)
            KeyType.ArrowLeft -> TerminalPosition(cursorX - 1, cursorY)
            KeyType.ArrowRight -> TerminalPosition(cursorX + 1, cursorY)
            else -> null
        }

        newCursorPosition?.let {
            this.backingCursorPosition = TerminalPosition(clamp(it.column.toLong(), 0, lines[newCursorPosition.row].length), clamp(it.row.toLong(), 0, sizeY))
            return
        }

        if (keyStroke.keyType != KeyType.Character && keyStroke.keyType != KeyType.Backspace)
            return

        //TODO: fix
        val scrollDirection: ScrollDirection? = when (keyStroke.keyType) {
            KeyType.ArrowUp -> if (cursorY >= sizeY) ScrollDirection.DOWN else null
            KeyType.ArrowLeft -> if (cursorX <= 0) ScrollDirection.LEFT else null
            KeyType.ArrowRight -> if (cursorX >= lines[this.backingCursorPosition.row].length) ScrollDirection.RIGHT else null
            KeyType.ArrowDown -> if (cursorY <= 0) ScrollDirection.UP else null
            else -> null
        }

        if (scrollDirection != null) {
            this.scroll(1u, scrollDirection)
            return
        }

        val newChar: Char? = keyStroke.character
        val isBackSpace: Boolean = keyStroke.keyType == KeyType.Backspace

        if (newChar == null && !isBackSpace) {
            return
        }

        val newLines: MutableList<String> = mutableListOf()

        for (y in lines.indices) {
            var currentLine: String = lines[y]
            if (this.cursorPosition.row == y) {

                currentLine = if (isBackSpace) {
                    val firstPart: String = currentLine.substring(0, this.cursorPosition.column - 1)
                    val secondPart: String = currentLine.substring(this.cursorPosition.column, currentLine.length)
                    firstPart + secondPart
                } else {
                    val firstPart: String = currentLine.substring(0, this.cursorPosition.column)
                    val secondPart: String = currentLine.substring(this.cursorPosition.column, currentLine.length)
                    firstPart + newChar + secondPart
                }

            }
            newLines.add(currentLine)
        }

        var newContents: String = ""
        for (line in newLines) {
            newContents += line + '\n'
        }

        this.currentSlice.modified(newContents)
        val newCursorX: Int = if (isBackSpace) this.cursorPosition.column - 1 else this.cursorPosition.column + 1
        this.backingCursorPosition = TerminalPosition(newCursorX, this.cursorPosition.row)

    }

    private fun scroll(scrollAmount: UInt, scrollDirection: ScrollDirection) {

        this.contentSource.writeSlice(this.currentSlice)
        this.scrollY = if (scrollDirection == ScrollDirection.UP) scrollY - scrollAmount else scrollY + scrollAmount
        this.scrollY = Math.clamp(this.scrollY.toLong(), 0, this.size.rows).toUInt()
        val startLine: UInt = this.scrollY
        val endLine: UInt = this.scrollY + this.size.rows.toUInt()
        this.currentSlice = this.contentSource.readSlice(startLine.toInt(), endLine.toInt())

    }

    override fun render() : List<TextLine> {
        val lines: List<String> = this.currentSlice.contents.split("\n", "\r")
        return lines.map { l -> TextLine.fromString(l, size.columns) }.toList()
    }

}