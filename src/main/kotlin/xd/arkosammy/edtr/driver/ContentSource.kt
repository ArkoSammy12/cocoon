package xd.arkosammy.edtr.driver

interface ContentSource {
    /**
     * @param startLine the line of the contents (inclusive) to start reading.
     * @param endLine the line of the contents (exclusive) to stop reading.
     * @return the slice of the contents within the given bounds.
     */
    fun readSlice(startLine: Int, endLine: Int): Slice

    /**
     * Overwrites the slice's range with its contents.
     */
    fun writeSlice(slice: Slice)

    /**
     * @param overridePath the path to use instead of the content's load path. Permanently replaces the old path.
     * @return whether the save was successful or not.
     */
    fun save(overridePath: String? = null): Boolean
}