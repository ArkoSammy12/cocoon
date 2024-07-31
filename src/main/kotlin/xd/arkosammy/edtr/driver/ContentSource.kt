package xd.arkosammy.edtr.driver

interface ContentSource {
    /**
     * @param startLine the line of the contents (inclusive) to start reading.
     * @param endLine the line of the contents (exclusive) to start reading.
     * @return the slice of the contents within the given bounds.
     */
    fun readSlice(startLine: Int, endLine: Int): String
}