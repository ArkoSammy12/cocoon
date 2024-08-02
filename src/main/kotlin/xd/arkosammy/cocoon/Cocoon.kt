package xd.arkosammy.cocoon

import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.ansi.UnixLikeTerminal
import xd.arkosammy.cocoon.driver.FileSource
import java.nio.charset.Charset
import com.googlecode.lanterna.input.KeyStroke
import xd.arkosammy.cocoon.view.ViewContainer

object Cocoon {

    lateinit var terminal: Terminal

    @JvmStatic
    fun main(args: Array<String>) {

        val terminalFactory: DefaultTerminalFactory = DefaultTerminalFactory(System.out, System.`in`, Charset.defaultCharset())
            .setTerminalEmulatorTitle("Cocoon")
            .setPreferTerminalEmulator(false)
            .setUnixTerminalCtrlCBehaviour(UnixLikeTerminal.CtrlCBehaviour.TRAP)

        terminal = try {
            terminalFactory
                .createTerminal()
        } catch (_: Exception) {
            terminalFactory
                .createTerminalEmulator()
        }

        val container = ViewContainer(terminal)

        terminal.enterPrivateMode()
        terminal.setCursorVisible(false)

        var key: KeyStroke? = terminal.pollInput()

        while (!(key?.isCtrlDown == true && key.character == 'c')) {
            key?.let { keyStroke: KeyStroke ->
                container.onKeyStroke(keyStroke)
            }

            container.render()
            key = terminal.pollInput()
        }

        terminal.setCursorVisible(true)
        terminal.exitPrivateMode()
    }

}