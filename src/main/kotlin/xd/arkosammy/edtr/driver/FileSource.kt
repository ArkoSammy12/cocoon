package xd.arkosammy.edtr.driver

import java.io.File

class FileSource(path: String) : ContentSource {
    private val contents: String = File(path).readText()

    override fun readSlice(startLine: Int, endLine: Int): String {
        var result = ""

        contents.lines().subList(startLine, endLine).forEach {
            result += it
        }

        return result
    }
}