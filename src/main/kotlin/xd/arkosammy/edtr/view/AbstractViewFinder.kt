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

abstract class AbstractViewFinder(
    final override val contentSource: ContentSource,
    size: TerminalSize,
    textLines: List<TextLine>,
    cursorPosition: TerminalPosition,
    override var editingMode: EditingMode
) : ViewFinder {

    private var scrollX: UInt = 0u
    private var scrollY: UInt = 0u

    private var currentSlice: Slice = this.contentSource.readSlice(scrollY.toInt(), scrollY.toInt() + size.rows)

    private var backingCursorPosition: TerminalPosition = cursorPosition
    override val cursorPosition: TerminalPosition by ::backingCursorPosition

    private var backingSize: TerminalSize = size
    override val size: TerminalSize by ::backingSize

    private val backingTextLines: MutableList<TextLine> = textLines.toMutableList()
    override val textLines: List<TextLine>
        get() = backingTextLines.toList()

    override fun onKeyStroke(keyStroke: KeyStroke) {

        if (keyStroke.character == 's' && keyStroke.isCtrlDown) {
            this.contentSource.writeSlice(this.currentSlice)
            this.contentSource.save()
            return
        }

        val cursorX: UInt = this.backingCursorPosition.row.toUInt()
        val cursorY: UInt = this.backingCursorPosition.column.toUInt()

        val sizeX: UInt = this.size.rows.toUInt()
        val sizeY: UInt = this.size.columns.toUInt()

        val scrollDirection: ScrollDirection? = when (keyStroke.keyType) {
            KeyType.ArrowUp -> if (cursorY >= sizeY) ScrollDirection.DOWN else null
            KeyType.ArrowLeft -> if (cursorX <= 0u) ScrollDirection.LEFT else null
            KeyType.ArrowRight -> if (cursorX >= sizeX) ScrollDirection.RIGHT else null
            KeyType.ArrowDown -> if (cursorY <= 0u) ScrollDirection.UP else null
            else -> null
        }

        if (scrollDirection != null) {
            this.scroll(1u, scrollDirection)
            return
        }

        val newCursorPosition: TerminalPosition? = when (keyStroke.keyType) {
            KeyType.ArrowUp -> TerminalPosition(Math.clamp(cursorX.toLong(), 0, sizeX.toInt()), Math.clamp(sizeY.toLong() + 1, 0, sizeY.toInt()))
            KeyType.ArrowLeft -> TerminalPosition(Math.clamp(cursorX.toLong() - 1, 0, sizeX.toInt()), Math.clamp(sizeY.toLong(), 0, sizeY.toInt()))
            KeyType.ArrowRight -> TerminalPosition(Math.clamp(cursorX.toLong() + 1, 0, sizeX.toInt()), Math.clamp(sizeY.toLong(), 0, sizeY.toInt()))
            KeyType.ArrowDown -> TerminalPosition(Math.clamp(cursorX.toLong(), 0, sizeX.toInt()), Math.clamp(sizeY.toLong() - 1, 0, sizeY.toInt()))
            else -> null
        }

        if (newCursorPosition != null) {
            this.backingCursorPosition = newCursorPosition
            return
        }

        val newChar: Char? = keyStroke.character
        val lines: List<String> = this.currentSlice.contents.split("\n", "\r")
        val newLines: MutableList<String> = mutableListOf()

        for (y in lines.indices) {
            var currentLine: String = lines[y]
            if (this.cursorPosition.row == y) {
                val firstPart: String = currentLine.substring(0, this.cursorPosition.column)
                val secondPart: String = currentLine.substring(this.cursorPosition.row + 1, currentLine.length)
                currentLine = firstPart + newChar + secondPart + '\n'
            }
            newLines.add(currentLine)
        }

        var newContents: String = ""
        for (line in newLines) {
            newContents += line
        }

        this.currentSlice.modified(newContents)

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
        return lines.map { l -> TextLine.fromString(l) }.toList()
    }

}