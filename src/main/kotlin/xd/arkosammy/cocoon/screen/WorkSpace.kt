package xd.arkosammy.cocoon.screen

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.Terminal

class WorkSpace : ViewContainer {

    val directoryView: DirectoryView = DirectoryView(TODO(), TODO())
    val fileViews: TabbedView<FileView> = TabbedView(TODO(), TODO())
    val shellViews: TabbedView<ShellView> = TabbedView(TODO(), TODO())

    override val views: List<View>
        get() = listOf(directoryView, fileViews, shellViews)

    override fun addView(view: View) {
        TODO("Not yet implemented")
    }

    override fun removeView(view: View) {
        TODO("Not yet implemented")
    }

    override fun onKeyStroke(keyStroke: KeyStroke) {
        TODO("Not yet implemented")
    }

    override fun onTerminalResized(terminal: Terminal, newSize: TerminalSize) {
        TODO("Not yet implemented")
    }

    override fun render(textGraphics: TextGraphics) {
        TODO("Not yet implemented")
    }

}