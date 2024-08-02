package xd.arkosammy.cocoon.view

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.cocoon.util.RenderedView

class TabbedView<T : View>(override var size: TerminalSize) : View {
    val tabs: MutableList<T> = ArrayList()
    val currentTab: T? = if (tabs.isEmpty()) null else tabs.first()

    override fun onKeyStroke(keyStroke: KeyStroke) {
        TODO("Not yet implemented")
    }

    override fun render(): RenderedView {
        TODO("Not yet implemented")
    }
}