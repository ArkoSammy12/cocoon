package xd.arkosammy.cocoon.view

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.cocoon.util.RenderedView


interface View {
    var size: TerminalSize

    fun onKeyStroke(keyStroke: KeyStroke)

    fun render(): RenderedView
}
