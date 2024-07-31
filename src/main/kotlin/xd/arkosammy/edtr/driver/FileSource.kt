package xd.arkosammy.edtr.driver

import java.io.File

class FileSource(private var path: String) : ContentSource {
    private var contents: String = File(path).readText()

    override fun readSlice(startLine: Int, endLine: Int): Slice {
        var result = ""

        contents.lines().subList(startLine, endLine).forEach {
            result += it
        }

        return Slice(result, startLine, endLine)
    }

    override fun writeSlice(slice: Slice) {
        val lines = contents.lines()
        val beforeSlice = lines.subList(0, slice.startLine)
        val afterSlice = lines.subList(slice.endLine, lines.size)
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