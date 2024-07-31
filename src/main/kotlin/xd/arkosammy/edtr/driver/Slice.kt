package xd.arkosammy.edtr.driver

data class Slice(val contents: String, val startLine: Int, val endLine: Int) {
    fun modified(newContents: String): Slice {
        return Slice(newContents, startLine, endLine)
    }
}