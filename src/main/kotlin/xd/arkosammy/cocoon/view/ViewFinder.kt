package xd.arkosammy.cocoon.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.cocoon.driver.ContentSource
import xd.arkosammy.cocoon.util.EditingMode
import xd.arkosammy.cocoon.util.TextLine


interface ViewFinder {

    val contentSource: ContentSource

    val textLines: List<TextLine>

    val cursorPosition: TerminalPosition

    var size: TerminalSize

    var editingMode: EditingMode

    fun onKeyStroke(keyStroke: KeyStroke)

    fun render(): List<TextLine>
}
