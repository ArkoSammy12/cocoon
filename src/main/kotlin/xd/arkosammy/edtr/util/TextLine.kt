package xd.arkosammy.edtr.util

import com.googlecode.lanterna.TextCharacter

class TextLine(val textCharacters: List<TextCharacter> = listOf()) {

    companion object {

        fun fromString(string: String) : TextLine {
            val textCharacters: MutableList<TextCharacter> = mutableListOf()

            for (c: Char in string) {
                textCharacters.add(TextCharacter(c))
            }

            return TextLine(textCharacters.toList())
        }

    }


}