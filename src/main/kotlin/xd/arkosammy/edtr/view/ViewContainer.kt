package xd.arkosammy.edtr.view

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.Terminal

interface ViewContainer {

    val terminal: Terminal

    val focusedViewFinder: ViewFinder

    fun addViewFinder(viewFinder: ViewFinder)

    fun onKeyStroke(keyStroke: KeyStroke)

}