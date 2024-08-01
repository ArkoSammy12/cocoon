package xd.arkosammy.edtr.driver

data class Slice(private var innerContents: String, val startLine: Int, val endLine: Int, var hasChanged: Boolean = false) {
    val contents: String
        get() {
            return innerContents
        }

    /**
     * @param newContents the new contents of the slice. Should always end in a newline.
     * @return the current, now modified, slice.
     */
    fun modified(newContents: String): Slice {
        innerContents = newContents
        hasChanged = true
        return this
    }
}