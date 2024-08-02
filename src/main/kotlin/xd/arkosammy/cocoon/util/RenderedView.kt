package xd.arkosammy.cocoon.util


class RenderedView(width: UInt, height: UInt) {

    private val renderedLines: MutableList<RenderedLine>

    init {
        val renderedLines: MutableList<RenderedLine> = mutableListOf()
        repeat (height.toInt()) {
            renderedLines.add(RenderedLine(width))
        }
        this.renderedLines = renderedLines
    }

    operator fun set(index: Int, renderedLine: RenderedLine) {
        this.renderedLines[index] = renderedLine
    }

    operator fun get(index: Int) : RenderedLine = this.renderedLines[index]

    // let's try to avoid using acronyms for stuff like this xd
    fun mergeVertically(right: RenderedView): RenderedView {
        TODO()
    }

    fun mergeHorizontally(bottom: RenderedView): RenderedView {
        TODO()
    }
}