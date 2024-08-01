package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.edtr.driver.ContentSource
import xd.arkosammy.edtr.util.EditingMode
import xd.arkosammy.edtr.util.TextLine

class TextEditorViewFinder(
    override val contentSource: ContentSource,
    override val textLines: List<TextLine>,
    override val cursorPosition: TerminalPosition,
    override val size: TerminalSize,
    override var editingMode: EditingMode
) : ViewFinder {
    override fun onKeyStroke(keyStroke: KeyStroke) {
        TODO("Not yet implemented")
    }

    override fun render() {
        TODO("Not yet implemented")
    }
}