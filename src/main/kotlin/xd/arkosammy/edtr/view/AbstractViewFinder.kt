package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.edtr.driver.ContentSource
import xd.arkosammy.edtr.util.EditingMode
import xd.arkosammy.edtr.util.ScrollDirection
import xd.arkosammy.edtr.util.TextLine

abstract class AbstractViewFinder(override val contentSource: ContentSource, size: TerminalSize) : ViewFinder {

    private val backingCursorPosition: TerminalPosition = TerminalPosition(0 ,0)
    override val cursorPosition: TerminalPosition by ::backingCursorPosition

    private var backingSize: TerminalSize = size
    override val size: TerminalSize by ::backingSize

    override var editingMode: EditingMode = EditingMode.INSERT

    private val backingTextLines: MutableList<TextLine> = mutableListOf()
    override val textLines: List<TextLine>
        get() = backingTextLines.toList()

    override fun onKeyStroke(keyStroke: KeyStroke) {


    }

    private fun scroll(scrollAmount: UInt, scrollDirection: ScrollDirection) {



    }

}