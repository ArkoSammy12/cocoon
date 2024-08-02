package xd.arkosammy.cocoon.util

import com.googlecode.lanterna.TextCharacter

@JvmInline
value class RenderedLine(val textCharacters: List<TextCharacter> = listOf()) {

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

}