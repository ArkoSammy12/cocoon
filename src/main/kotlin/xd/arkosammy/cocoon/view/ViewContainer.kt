package xd.arkosammy.cocoon.view

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.Terminal

class ViewContainer(val terminal: Terminal) {
    val workspaceView: WorkspaceView = WorkspaceView(TODO())

    fun onKeyStroke(keyStroke: KeyStroke) {
        TODO()
    }

    fun render() {
        TODO()
    }
}