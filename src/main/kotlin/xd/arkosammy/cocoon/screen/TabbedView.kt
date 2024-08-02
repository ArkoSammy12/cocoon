package xd.arkosammy.cocoon.screen

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.cocoon.util.RenderedView

class TabbedView<T : View>(override var size: TerminalSize, override val position: TerminalPosition) : View {
    val tabs: MutableList<T> = ArrayList()
    val currentTab: T? = if (tabs.isEmpty()) null else tabs.first()
    override var isFocused: Boolean = false

    override fun onKeyStroke(keyStroke: KeyStroke) {
        TODO("Not yet implemented")
    }

    override fun getRenderedView(): RenderedView {
        TODO("Not yet implemented")
    }
}