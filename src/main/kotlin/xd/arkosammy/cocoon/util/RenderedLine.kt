package xd.arkosammy.cocoon.util

import com.googlecode.lanterna.TextCharacter

class RenderedLine(length: UInt) {

    private val textCharacters: MutableList<TextCharacter>

    val length: UInt = length

    init {
        val textCharacters: MutableList<TextCharacter> = mutableListOf()
        repeat (length.toInt()) {
            textCharacters.add(TextCharacter(' '))
        }
        this.textCharacters = textCharacters
    }

    operator fun get(index: Int): TextCharacter = this.textCharacters[index]

    operator fun set(index: Int, textCharacter: TextCharacter) {
         this.textCharacters[index] = textCharacter
    }

    /*
    companion object {

        fun fromString(string: String, sizeX: Int) : RenderedLine {
            val textCharacters: MutableList<TextCharacter> = mutableListOf()

            for (c: Char in string) {
                textCharacters.add(TextCharacter(c))
            }

            for (size in sizeX downTo 0)
                textCharacters.add(TextCharacter(' '))

            return RenderedLine(textCharacters.toList())
        }

    }

     */

}