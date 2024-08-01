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
        TODO("Not yet implemented")
    }

    override fun writeSlice(slice: Slice) {
        TODO("Not yet implemented")
    }

    override fun save(overridePath: String?): Boolean {
        // does nothing
        return true
    }
}