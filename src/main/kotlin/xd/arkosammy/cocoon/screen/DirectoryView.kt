package xd.arkosammy.cocoon.screen

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.cocoon.util.RenderedView

class DirectoryView(override var size: TerminalSize, override val position: TerminalPosition) : View {

    override var isFocused: Boolean = false

    override fun onKeyStroke(keyStroke: KeyStroke) {
        TODO("Not yet implemented")
    }

    override fun getRenderedView(): RenderedView {
        TODO("Not yet implemented")
    }

}