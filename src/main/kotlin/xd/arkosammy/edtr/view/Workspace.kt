package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal

class Workspace(override val terminal: Terminal) : ViewContainer<Workspace> {
    private val viewFinders: MutableList<ViewFinder> = mutableListOf()
    override val focusedViewFinder: ViewFinder?
        get() = viewFinders.firstOrNull()

    override fun addViewFinder(viewFinder: ViewFinder): Workspace {
        viewFinders.add(viewFinder)
        return this
    }

    override fun onKeyStroke(keyStroke: KeyStroke) {
        focusedViewFinder?.let {
            if (keyStroke.isCtrlDown && (keyStroke.keyType == KeyType.ArrowDown || keyStroke.keyType == KeyType.ArrowLeft)) {
                viewFinders.addFirst(viewFinders.last())
            } else if (keyStroke.isCtrlDown && (keyStroke.keyType == KeyType.ArrowUp || keyStroke.keyType == KeyType.ArrowRight)) {
                viewFinders.add(viewFinders.first())
            } else it.onKeyStroke(keyStroke)
        }
    }

    override fun render() {
        focusedViewFinder?.let {
            val graphics = terminal.newTextGraphics()
            val lines = it.render()
            val cursorPosition: TerminalPosition = it.cursorPosition

            for ((y, line) in lines.withIndex()) {
                for ((x, character) in line.textCharacters.withIndex()) {
                    var char: TextCharacter = character
                    if (cursorPosition.row == y && cursorPosition.column == x) {
                        char = char.withBackgroundColor(TextColor.ANSI.WHITE).withForegroundColor(TextColor.ANSI.BLACK)
                    }
                    graphics.setCharacter(TerminalPosition(x, y), char)
                }
            }

            this.terminal.flush()
        }
    }
}