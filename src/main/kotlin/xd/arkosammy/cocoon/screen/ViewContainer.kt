package xd.arkosammy.cocoon.screen

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.Terminal

interface ViewContainer {

    val views: List<View>

    fun addView(view: View)

    fun removeView(view: View)

    fun onKeyStroke(keyStroke: KeyStroke)

    fun onTerminalResized(terminal: Terminal, newSize: TerminalSize)

    fun render(textGraphics: TextGraphics)

}