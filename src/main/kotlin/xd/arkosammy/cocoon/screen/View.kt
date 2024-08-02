package xd.arkosammy.cocoon.screen

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.cocoon.util.RenderedView


interface View {

    var size: TerminalSize

    val position: TerminalPosition

    var isFocused: Boolean

    fun onKeyStroke(keyStroke: KeyStroke)

    fun getRenderedView(): RenderedView

}
