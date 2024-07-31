package xd.arkosammy.edtr.driver

data class Slice(val contents: String, val startLine: Int, val endLine: Int) {
    /**
     * @param newContents the new contents of the slice. Should always end in a newline.
     * @return the new slice.
     */
    fun modified(newContents: String): Slice {
        return Slice(newContents, startLine, endLine)
    }
}