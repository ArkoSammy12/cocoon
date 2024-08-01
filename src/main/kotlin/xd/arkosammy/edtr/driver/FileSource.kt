package xd.arkosammy.edtr.driver

import java.io.File

class FileSource(private var path: String) : ContentSource {
    private var contents: String = File(path).readText()

    override fun readSlice(startLine: Int, endLine: Int): Slice {
        val lines = contents.lines()
        val endLine = endLine.coerceAtMost(lines.size)
        var result = ""

        lines.subList(startLine, endLine).forEach {
            result += it
            result += '\n'
        }

        return Slice(result, startLine, endLine)
    }

    override fun writeSlice(slice: Slice) {
        val lines = contents.lines()
        val endLine = slice.endLine.coerceAtMost(lines.size)
        val beforeSlice = lines.subList(0, slice.startLine)
        val afterSlice = lines.subList(endLine, lines.size)
        var result = ""

        beforeSlice.forEach {
            result += it
            result += '\n'
        }

        result += slice.contents

        afterSlice.forEach {
            result += it
            result += '\n'
        }

        contents = result
    }

    override fun save(overridePath: String?): Boolean {
        try {
            if (overridePath is String)
                path = overridePath

            File(path).writeText(contents)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}