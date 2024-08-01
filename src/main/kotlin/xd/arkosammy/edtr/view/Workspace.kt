package xd.arkosammy.edtr.view

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.Terminal

class Workspace(override val terminal: Terminal, focusedViewFinder: ViewFinder?) : ViewContainer<Workspace> {
    override var focusedViewFinder: ViewFinder? = focusedViewFinder
        private set

    private val viewFinders: MutableList<ViewFinder> = mutableListOf()

    init {
        focusedViewFinder?.let {
            viewFinders.add(it)
        }
    }

    override fun addViewFinder(viewFinder: ViewFinder): Workspace {
        viewFinders.add(viewFinder)

        if (focusedViewFinder == null) {
            focusedViewFinder = viewFinder
        }

        return this
    }

    override fun onKeyStroke(keyStroke: KeyStroke) {
        focusedViewFinder?.onKeyStroke(keyStroke)
    }

    override fun render() {
        TODO("Not yet implemented")
    }
}