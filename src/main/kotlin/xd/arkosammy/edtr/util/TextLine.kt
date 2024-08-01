package xd.arkosammy.edtr.util

import com.googlecode.lanterna.TextCharacter

class TextLine(val textCharacters: List<TextCharacter> = listOf()) {

    companion object {

        fun fromString(string: String, sizeX: Int) : TextLine {
            val textCharacters: MutableList<TextCharacter> = mutableListOf()

            for (c: Char in string) {
                textCharacters.add(TextCharacter(c))
            }

            for (size in sizeX downTo 0)
                textCharacters.add(TextCharacter(' '))

            return TextLine(textCharacters.toList())
        }

    }

}