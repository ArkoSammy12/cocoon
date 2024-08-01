package xd.arkosammy.edtr.driver

import java.io.File
import java.nio.file.Files

class ShellSource : ContentSource {
    private val fout: File = Files.createTempFile("edtr", "fout").toFile()
    private val fin: File = Files.createTempFile("edtr", "fin").toFile()
    private val process: Process

    init {
        val pb = ProcessBuilder()
        pb.redirectInput(ProcessBuilder.Redirect.from(fin))
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(fout))
        pb.command("bash")
        process = pb.start()
    }

    override fun readSlice(startLine: Int, endLine: Int): Slice {
        val lines = fout.readLines()
        val endLine = endLine.coerceAtMost(lines.size)
        var result = ""

        lines.subList(startLine, endLine).forEach {
            result += it
            result += '\n'
        }

        return Slice(result, startLine, endLine)
    }

    override fun writeSlice(slice: Slice) {
        fout.writeText(slice.contents)
        fin.writeText(slice.contents)
    }

    override fun save(overridePath: String?): Boolean {
        // does nothing
        return true
    }
}