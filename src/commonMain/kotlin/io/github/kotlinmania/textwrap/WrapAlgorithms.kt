// port-lint: source wrap_algorithms.rs
package io.github.kotlinmania.textwrap

/**
 * Custom wrapping algorithm function.
 */
fun interface WrapAlgorithmFunction {
    fun wrap(words: List<Word>, lineWidths: List<Int>): List<List<Word>>
}

/**
 * Describes how to wrap words into lines.
 *
 * The simplest approach is to wrap words one by one until they no longer fit
 * on the current line. The [FirstFit] algorithm implements this greedy approach.
 *
 * A more sophisticated approach is to find the line breaks which produce the
 * "prettiest" lines. The [OptimalFit] algorithm implements this approach.
 */
sealed interface WrapAlgorithm {
    /**
     * Wrap words according to line widths.
     */
    fun wrap(
        words: List<Word>,
        lineWidths: List<Int>,
    ): List<List<Word>>

    /**
     * Wrap words using a fast and simple first-fit algorithm.
     */
    data object FirstFit : WrapAlgorithm {
        override fun wrap(
            words: List<Word>,
            lineWidths: List<Int>,
        ): List<List<Word>> {
            val f64LineWidths = lineWidths.map { it.toDouble() }
            return wrapFirstFit(words, f64LineWidths)
        }
    }

    /**
     * Wrap words using an advanced optimal-fit algorithm with look-ahead.
     */
    data class OptimalFit(
        val penalties: Penalties = Penalties.new(),
    ) : WrapAlgorithm {
        override fun wrap(
            words: List<Word>,
            lineWidths: List<Int>,
        ): List<List<Word>> {
            val f64LineWidths = lineWidths.map { it.toDouble() }
            return wrapOptimalFit(words, f64LineWidths, penalties)
        }
    }

    /**
     * Compare two wrap algorithms for equality.
     */
    fun eq(other: WrapAlgorithm): Boolean = this == other

    /**
     * Clone this wrap algorithm.
     */
    fun clone(): WrapAlgorithm = this

    /**
     * Custom wrapping function.
     */
    class Custom(
        val wrapper: WrapAlgorithmFunction,
    ) : WrapAlgorithm {
        override fun wrap(
            words: List<Word>,
            lineWidths: List<Int>,
        ): List<List<Word>> = wrapper.wrap(words, lineWidths)

        override fun clone(): WrapAlgorithm = Custom(wrapper)

        override fun equals(other: Any?): Boolean = this === other || (other is Custom && wrapper == other.wrapper)

        override fun hashCode(): Int = wrapper.hashCode()
    }

    companion object {
        /**
         * Create new wrap algorithm with optimal fit by default.
         */
        fun new(): WrapAlgorithm = newOptimalFit()

        /**
         * New [OptimalFit] with default penalties.
         */
        fun newOptimalFit(): WrapAlgorithm = OptimalFit(Penalties.new())

        /**
         * Default [WrapAlgorithm].
         */
        fun default(): WrapAlgorithm = new()
    }
}

/**
 * Wrap abstract fragments into lines with a first-fit algorithm.
 *
 * The [lineWidths] list gives the target line width for each line
 * (the last list element is repeated as necessary).
 */
fun <T : Fragment> wrapFirstFit(
    fragments: List<T>,
    lineWidths: List<Double>,
): List<List<T>> {
    val defaultLineWidth = lineWidths.lastOrNull() ?: 0.0
    val lines = mutableListOf<List<T>>()
    var start = 0
    var width = 0.0

    for (idx in fragments.indices) {
        val fragment = fragments[idx]
        val lineWidth = if (lines.size < lineWidths.size) lineWidths[lines.size] else defaultLineWidth
        if (width + fragment.width() + fragment.penaltyWidth() > lineWidth && idx > start) {
            lines.add(fragments.subList(start, idx))
            start = idx
            width = 0.0
        }
        width += fragment.width() + fragment.whitespaceWidth()
    }
    lines.add(fragments.subList(start, fragments.size))
    return lines
}

internal data class WrapWord(
    val w: Double,
) : Fragment {
    override fun width(): Double = w

    override fun whitespaceWidth(): Double = 1.0

    override fun penaltyWidth(): Double = 0.0
}
