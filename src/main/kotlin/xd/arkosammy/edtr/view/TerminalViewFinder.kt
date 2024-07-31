package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.terminal.Terminal
import xd.arkosammy.edtr.driver.ContentSource
import xd.arkosammy.edtr.driver.Slice

class TerminalViewFinder(private val terminal: Terminal) : ViewFinder {

    private val currentLines: MutableList<MutableList<TextCharacter>> = mutableListOf()
    private var backingScrollAmount: UInt = 0u
    override val scrollAmount: UInt by ::backingScrollAmount

    override val size: TerminalSize
        get() = this.terminal.terminalSize

    override fun scroll(contentSource: ContentSource, scrollAmount: Int) {
        this.currentLines.clear()
        if (backingScrollAmount.toInt() < scrollAmount) {
            backingScrollAmount = 0u
        } else {
            backingScrollAmount += scrollAmount.toUInt()
        }

        val startLine: UInt = this.scrollAmount
        val endLine: Int = startLine.toInt() + this.size.rows
        val slice: Slice = contentSource.readSlice(startLine.toInt(), endLine)
        val lines: Array<String> = slice.contents.split("\\n").toTypedArray()

        for (line: String in lines) {
            val textCharLine: MutableList<TextCharacter> = mutableListOf()
            for (char: Char in line) {
                val textCharacter: TextCharacter = TextCharacter(char)
                textCharLine.add(textCharacter)
            }
            this.currentLines.add(textCharLine)
        }

    }

    override fun display() {

        val textGraphics: TextGraphics = this.terminal.newTextGraphics()
        for (y in 0 until this.currentLines.size) {
            val currentLine: MutableList<TextCharacter> = this.currentLines[y]
            for (x in 0 until currentLine.size) {
                val currentChar: TextCharacter = currentLine[x]
                textGraphics.setCharacter(x, y, currentChar)
            }
        }
        this.terminal.flush()

    }

}