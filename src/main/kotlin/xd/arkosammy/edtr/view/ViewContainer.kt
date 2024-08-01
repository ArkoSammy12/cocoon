package xd.arkosammy.edtr.view

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.Terminal

interface ViewContainer<out T : ViewContainer<T>> {

    val terminal: Terminal

    val focusedViewFinder: ViewFinder?

    fun addViewFinder(viewFinder: ViewFinder): T

    fun onKeyStroke(keyStroke: KeyStroke)

    fun render()
}