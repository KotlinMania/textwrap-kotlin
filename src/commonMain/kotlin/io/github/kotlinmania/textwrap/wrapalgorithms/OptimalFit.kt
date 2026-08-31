// port-lint: source textwrap/src/wrap_algorithms/optimal_fit.rs
package io.github.kotlinmania.textwrap

/**
 * Overflow error during the [wrapOptimalFit] computation.
 */
class OverflowError(
    message: String = "wrap_optimal_fit cost computation overflowed",
) : RuntimeException(message) {
    /** Format the error message. */
    fun fmt(): String = message ?: "wrap_optimal_fit cost computation overflowed"
}

/**
 * Overflow error alias during the [wrapOptimalFit] computation.
 */
typealias OverflowException = OverflowError

internal data class OptimalFitWord(
    val w: Double,
) : Fragment {
    override fun width(): Double = w

    override fun whitespaceWidth(): Double = 1.0

    override fun penaltyWidth(): Double = 0.0
}

/**
 * Penalties for [WrapAlgorithm.OptimalFit] and [wrapOptimalFit].
 *
 * This wrapping algorithm in [wrapOptimalFit] considers the entire paragraph
 * to find optimal line breaks. When wrapping text, penalties are assigned to
 * line breaks based on the gaps left at the end of lines.
 */
data class Penalties(
    /**
     * Per-line penalty. This is added for every line, which makes it expensive
     * to output more lines than the minimum required.
     */
    var nlinePenalty: Int = 1000,
    /**
     * Per-character cost for lines that overflow the target line width.
     */
    var overflowPenalty: Int = 50 * 50,
    /**
     * When should a single word on the last line be considered "too short"?
     */
    var shortLastLineFraction: Int = 4,
    /**
     * Penalty for a last line with a single short word.
     */
    var shortLastLinePenalty: Int = 25,
    /**
     * Penalty for lines ending with a hyphen.
     */
    var hyphenPenalty: Int = 25,
) {
    /** Change [nlinePenalty]. */
    fun nlinePenalty(nlinePenalty: Int): Penalties = copy(nlinePenalty = nlinePenalty)

    /** Change [overflowPenalty]. */
    fun overflowPenalty(overflowPenalty: Int): Penalties = copy(overflowPenalty = overflowPenalty)

    /** Change [shortLastLineFraction]. */
    fun shortLastLineFraction(shortLastLineFraction: Int): Penalties = copy(shortLastLineFraction = shortLastLineFraction)

    /** Change [shortLastLinePenalty]. */
    fun shortLastLinePenalty(shortLastLinePenalty: Int): Penalties = copy(shortLastLinePenalty = shortLastLinePenalty)

    /** Change [hyphenPenalty]. */
    fun hyphenPenalty(hyphenPenalty: Int): Penalties = copy(hyphenPenalty = hyphenPenalty)

    /** Clone penalties. */
    fun clone(): Penalties = copy()

    companion object {
        /** Default penalties for monospace text. */
        fun new(): Penalties = Penalties()

        /** Default penalties for monospace text. */
        fun default(): Penalties = Penalties()
    }
}

private class LineNumbers {
    private val numbers = mutableListOf(0)

    fun <T> get(
        i: Int,
        minima: List<Pair<Int, T>>,
    ): Int {
        while (numbers.size < i + 1) {
            val pos = numbers.size
            val lineNumber = 1 + get(minima[pos].first, minima)
            numbers.add(lineNumber)
        }
        return numbers[i]
    }
}

private data class MinimaEntry(
    val row: Int,
    var start: Int,
    var end: Int,
)

internal fun onlineColumnMinima(
    initialValue: Double,
    size: Int,
    costFn: (minima: List<Pair<Int, Double>>, i: Int, j: Int) -> Double,
): List<Pair<Int, Double>> {
    if (size == 0) return emptyList()
    val minima = ArrayList<Pair<Int, Double>>(size)
    minima.add(0 to initialValue)
    if (size == 1) return minima

    val deque = ArrayDeque<MinimaEntry>()
    deque.add(MinimaEntry(0, 1, size - 1))

    for (j in 1 until size) {
        while (deque.isNotEmpty() && deque.first().end < j) {
            deque.removeFirst()
        }
        val front = deque.first()
        val bestI = front.row
        val bestCost = costFn(minima, bestI, j)
        minima.add(bestI to bestCost)

        if (j == size - 1) break

        var nextCol = j + 1
        while (deque.isNotEmpty()) {
            val back = deque.last()
            val costBackAtStart = costFn(minima, back.row, back.start)
            val costJAtStart = costFn(minima, j, back.start)
            if (costJAtStart < costBackAtStart) {
                nextCol = back.start
                deque.removeLast()
            } else {
                val costBackAtEnd = costFn(minima, back.row, back.end)
                val costJAtEnd = costFn(minima, j, back.end)
                if (costJAtEnd < costBackAtEnd) {
                    var low = back.start + 1
                    var high = back.end
                    var split = back.end
                    while (low <= high) {
                        val mid = (low + high) ushr 1
                        val cBack = costFn(minima, back.row, mid)
                        val cJ = costFn(minima, j, mid)
                        if (cJ < cBack) {
                            split = mid
                            high = mid - 1
                        } else {
                            low = mid + 1
                        }
                    }
                    back.end = split - 1
                    nextCol = split
                } else {
                    nextCol = back.end + 1
                }
                break
            }
        }
        if (nextCol <= size - 1) {
            deque.addLast(MinimaEntry(j, nextCol, size - 1))
        }
    }
    return minima
}

/**
 * Wrap abstract fragments into lines with an optimal-fit algorithm.
 *
 * The [lineWidths] list gives the target line width for each line
 * (the last list element is repeated as necessary).
 */
fun <T : Fragment> wrapOptimalFit(
    fragments: List<T>,
    lineWidths: List<Double>,
    penalties: Penalties = Penalties.new(),
): List<List<T>> {
    val defaultLineWidth = lineWidths.lastOrNull() ?: 0.0
    val widths = ArrayList<Double>(fragments.size + 1)
    var width = 0.0
    widths.add(width)
    for (fragment in fragments) {
        width += fragment.width() + fragment.whitespaceWidth()
        widths.add(width)
    }

    val lineNumbers = LineNumbers()

    val minima =
        onlineColumnMinima(0.0, widths.size) { minimaSoFar, i, j ->
            val lineNumber = lineNumbers.get(i, minimaSoFar)
            val lineW = if (lineNumber < lineWidths.size) lineWidths[lineNumber] else defaultLineWidth
            val targetWidth = lineW.coerceAtLeast(1.0)

            val lineSpanWidth =
                widths[j] - widths[i] -
                    fragments[j - 1].whitespaceWidth() +
                    fragments[j - 1].penaltyWidth()

            var cost = minimaSoFar[i].second + penalties.nlinePenalty.toDouble()

            if (lineSpanWidth > targetWidth) {
                val overflow = lineSpanWidth - targetWidth
                cost += overflow * penalties.overflowPenalty.toDouble()
            } else if (j < fragments.size) {
                val gap = targetWidth - lineSpanWidth
                cost += gap * gap
            } else if (i + 1 == j && lineSpanWidth < targetWidth / penalties.shortLastLineFraction.toDouble()) {
                cost += penalties.shortLastLinePenalty.toDouble()
            }

            if (fragments[j - 1].penaltyWidth() > 0.0) {
                cost += penalties.hyphenPenalty.toDouble()
            }

            cost
        }

    for ((_, cost) in minima) {
        if (cost.isInfinite() || cost.isNaN()) {
            throw OverflowException()
        }
    }

    val lines = mutableListOf<List<T>>()
    var pos = fragments.size
    while (pos > 0) {
        val prev = minima[pos].first
        lines.add(fragments.subList(prev, pos))
        pos = prev
    }
    lines.reverse()
    return lines
}
