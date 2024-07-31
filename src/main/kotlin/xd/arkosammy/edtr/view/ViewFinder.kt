package xd.arkosammy.edtr.view

import com.googlecode.lanterna.TerminalSize
import xd.arkosammy.edtr.driver.ContentSource

/**
 * Represents a generic object which can display content to the screen
 *
 */
interface ViewFinder {

    val scrollAmount: UInt

    val size: TerminalSize

    fun scroll(contentSource: ContentSource, scrollAmount: Int = 1)

    fun display()

}