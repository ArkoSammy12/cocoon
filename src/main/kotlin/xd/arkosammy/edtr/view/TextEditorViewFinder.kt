package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import xd.arkosammy.edtr.driver.ContentSource
import xd.arkosammy.edtr.util.EditingMode
import xd.arkosammy.edtr.util.TextLine

class TextEditorViewFinder(
    contentSource: ContentSource,
    size: TerminalSize,
    textLines: List<TextLine>,
    cursorPosition: TerminalPosition,
    override var editingMode: EditingMode
) : AbstractViewFinder(contentSource, size, textLines, cursorPosition, editingMode) {

}