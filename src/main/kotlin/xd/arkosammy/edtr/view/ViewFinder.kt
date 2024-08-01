package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.edtr.driver.ContentSource
import xd.arkosammy.edtr.util.EditingMode
import xd.arkosammy.edtr.util.TextLine


interface ViewFinder {

    val contentSource: ContentSource

    val textLines: List<TextLine>

    val cursorPosition: TerminalPosition

    var size: TerminalSize

    var editingMode: EditingMode

    fun onKeyStroke(keyStroke: KeyStroke)

    fun render(): List<TextLine>
}
