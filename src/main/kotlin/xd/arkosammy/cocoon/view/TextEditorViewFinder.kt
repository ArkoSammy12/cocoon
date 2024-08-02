package xd.arkosammy.cocoon.view

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import xd.arkosammy.cocoon.driver.ContentSource
import xd.arkosammy.cocoon.util.EditingMode
import xd.arkosammy.cocoon.util.TextLine

class TextEditorViewFinder(
    contentSource: ContentSource,
    size: TerminalSize,
    textLines: List<TextLine>,
    cursorPosition: TerminalPosition,
    override var editingMode: EditingMode
) : AbstractViewFinder(contentSource, size, textLines, cursorPosition, editingMode) {

}