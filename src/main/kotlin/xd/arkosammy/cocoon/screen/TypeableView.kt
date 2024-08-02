package xd.arkosammy.cocoon.screen

import com.googlecode.lanterna.TerminalPosition

interface TypeableView : View {

    val cursorPosition: TerminalPosition

}