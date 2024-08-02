package xd.arkosammy.cocoon.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal
import xd.arkosammy.cocoon.util.RenderedView

class WorkspaceView(override var size: TerminalSize) : View {
    val directoryView: DirectoryView = DirectoryView(TODO())
    val fileViews: TabbedView<FileView> = TabbedView(TODO())
    val shellViews: TabbedView<ShellView> = TabbedView(TODO())

    override fun onKeyStroke(keyStroke: KeyStroke) {
        TODO("Not yet implemented")
    }

    override fun render(): RenderedView {
        TODO("Not yet implemented")
    }
}