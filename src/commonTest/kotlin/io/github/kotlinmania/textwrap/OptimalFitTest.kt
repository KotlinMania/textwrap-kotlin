// port-lint: tests wrap_algorithms/optimal_fit.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OptimalFitTest {
    private data class Word(
        val w: Double,
    ) : Fragment {
        override fun width(): Double = w
        override fun whitespaceWidth(): Double = 1.0
        override fun penaltyWidth(): Double = 0.0
    }

    @Test
    fun wrapFragmentsWithInfiniteWidths() {
        val words = listOf(Word(Double.POSITIVE_INFINITY))
        assertFailsWith<OverflowError> {
            wrapOptimalFit(words, listOf(0.0), Penalties.default())
        }
    }

    @Test
    fun wrapFragmentsWithHugeWidths() {
        val words = listOf(Word(1e200), Word(1e250), Word(1e300))
        assertFailsWith<OverflowError> {
            wrapOptimalFit(words, listOf(1e300), Penalties.default())
        }
    }

    @Test
    fun wrapFragmentsWithLargeWidths() {
        val words = listOf(Word(1e25), Word(1e50), Word(1e75))
        val result = wrapOptimalFit(words, listOf(1e100), Penalties.default())
        assertEquals(listOf(words), result)
    }
}
