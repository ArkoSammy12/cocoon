package xd.arkosammy.edtr

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.ansi.UnixLikeTerminal
import xd.arkosammy.edtr.driver.FileSource
import java.nio.charset.Charset
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.edtr.util.EditingMode
import xd.arkosammy.edtr.view.TextEditorViewFinder
import xd.arkosammy.edtr.view.Workspace

object Edtr {

    val terminal: Terminal = DefaultTerminalFactory(System.out, System.`in`, Charset.defaultCharset())
        .setTerminalEmulatorTitle("Edtr")
        .setForceTextTerminal(false)
        .setPreferTerminalEmulator(true)
        .setUnixTerminalCtrlCBehaviour(UnixLikeTerminal.CtrlCBehaviour.TRAP)
        .createTerminal()

    @JvmStatic
    fun main(args: Array<String>) {
        val file = FileSource(args[0])
        val workspace = Workspace(terminal).addViewFinder(TextEditorViewFinder(file, terminal.terminalSize, listOf(), TerminalPosition.TOP_LEFT_CORNER, EditingMode.INSERT))

        terminal.enterPrivateMode()
        terminal.setCursorVisible(false)

        var key: KeyStroke? = terminal.pollInput()

        while (!(key?.isCtrlDown == true && key.character == 'c')) {
            key?.let { keyStroke: KeyStroke ->
                workspace.onKeyStroke(keyStroke)
            }

            workspace.render()
            key = terminal.pollInput()
        }

        terminal.setCursorVisible(true)
        terminal.exitPrivateMode()
    }

}