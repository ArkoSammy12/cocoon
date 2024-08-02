package xd.arkosammy.cocoon

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.ansi.UnixLikeTerminal
import xd.arkosammy.cocoon.screen.ViewContainer
import xd.arkosammy.cocoon.screen.WorkSpace
import java.nio.charset.Charset

object ViewManager {

    val terminal: Terminal

    private var viewContainers: MutableList<ViewContainer> = mutableListOf()

    init {

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

    }

    fun start() {
        val container = WorkSpace()
        this.viewContainers.add(container)

        terminal.enterPrivateMode()
        terminal.setCursorVisible(false)

        var key: KeyStroke? = terminal.pollInput()

        while (!(key?.isCtrlDown == true && key.character == 'c')) {
            key?.let { keyStroke: KeyStroke ->
                this.getCurrentViewContainer().onKeyStroke(keyStroke)
            }

            this.getCurrentViewContainer().render(terminal.newTextGraphics())
            key = terminal.pollInput()
        }

        terminal.setCursorVisible(true)
        terminal.exitPrivateMode()
    }

    private fun getCurrentViewContainer() : ViewContainer {
        TODO()
    }

}