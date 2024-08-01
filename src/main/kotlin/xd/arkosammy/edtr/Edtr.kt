package xd.arkosammy.edtr

import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.ansi.UnixLikeTerminal
import xd.arkosammy.edtr.driver.FileSource
import java.nio.charset.Charset
import com.googlecode.lanterna.input.KeyStroke

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

        terminal.enterPrivateMode()
        terminal.setCursorVisible(false)

        var key: KeyStroke? = terminal.pollInput()

        while (!(key?.isCtrlDown == true && key.character == 'c')) {
            key?.let { keyStroke: KeyStroke ->
            }

            //TODO: rendering?
//            terminal.terminalSize
//            terminal.cursorPosition = TerminalPosition(0, 0)
//            terminal.putString(file.readSlice(0, 5).contents)
//            terminal.flush()

            key = terminal.pollInput()
        }

        terminal.setCursorVisible(true)
        terminal.exitPrivateMode()
    }

}