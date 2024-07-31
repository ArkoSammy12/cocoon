package xd.arkosammy.edtr

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.ansi.UnixLikeTerminal
import java.nio.charset.Charset

object Edtr {

    val terminal: Terminal = DefaultTerminalFactory(System.out, System.`in`, Charset.defaultCharset())
        .setTerminalEmulatorTitle("Edtr")
        .setForceTextTerminal(true)
        .setPreferTerminalEmulator(false)
        .setUnixTerminalCtrlCBehaviour(UnixLikeTerminal.CtrlCBehaviour.TRAP)
        .createTerminal()

    @JvmStatic
    fun main(args: Array<String>) {

        println("Hello world")
        terminal.terminalSize
        terminal.cursorPosition = TerminalPosition(0, 0)
        terminal.putString("Hello world")
        terminal.flush()

    }

}