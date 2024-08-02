package xd.arkosammy.cocoon.util

@JvmInline
value class RenderedView(val chars: List<RenderedLine>) {
    fun vMerge(right: RenderedView): RenderedView {
        TODO()
    }

    fun hMerge(bottom: RenderedView): RenderedView {
        TODO()
    }
}