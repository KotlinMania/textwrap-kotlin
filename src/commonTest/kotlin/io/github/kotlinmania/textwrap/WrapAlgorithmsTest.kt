// port-lint: tests wrap_algorithms.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class WrapAlgorithmsTest {
    private data class TestWord(
        val w: Double,
    ) : Fragment {
        override fun width(): Double = w

        override fun whitespaceWidth(): Double = 1.0

        override fun penaltyWidth(): Double = 0.0
    }

    private data class Task(
        val name: String,
        val hours: Double,
        val sweep: Double,
        val cleanup: Double,
    ) : Fragment {
        override fun width(): Double = hours

        override fun whitespaceWidth(): Double = sweep

        override fun penaltyWidth(): Double = cleanup
    }

    @Test
    fun wrapStringLongerThanF64() {
        val words =
            listOf(
                TestWord(1e307),
                TestWord(2e307),
                TestWord(3e307),
                TestWord(4e307),
                TestWord(5e307),
                TestWord(6e307),
            )
        val wrapped = wrapFirstFit(words, listOf(15e307))
        assertEquals(
            listOf(
                listOf(
                    TestWord(1e307),
                    TestWord(2e307),
                    TestWord(3e307),
                    TestWord(4e307),
                    TestWord(5e307),
                ),
                listOf(TestWord(6e307)),
            ),
            wrapped,
        )
    }

    @Test
    fun assignDaysExample() {
        val tasks =
            listOf(
                Task("Foundation", 4.0, 2.0, 3.0),
                Task("Framing", 3.0, 1.0, 2.0),
                Task("Plumbing", 2.0, 2.0, 2.0),
                Task("Electrical", 2.0, 1.0, 2.0),
                Task("Insulation", 2.0, 1.0, 2.0),
                Task("Drywall", 3.0, 1.0, 2.0),
                Task("Floors", 3.0, 1.0, 2.0),
                Task("Countertops", 1.0, 1.0, 2.0),
                Task("Bathrooms", 2.0, 1.0, 2.0),
            )

        fun assignDays(
            tasksList: List<Task>,
            dayLength: Double,
        ): List<Pair<Double, List<String>>> {
            val assigned = wrapFirstFit(tasksList, listOf(dayLength))
            return assigned.map { day ->
                val last = day.last()
                val workHours: Double = day.sumOf { it.hours + it.sweep }
                val names = day.map { it.name }
                (workHours - last.sweep + last.cleanup) to names
            }
        }

        assertEquals(
            listOf(
                7.0 to listOf("Foundation"),
                8.0 to listOf("Framing", "Plumbing"),
                7.0 to listOf("Electrical", "Insulation"),
                5.0 to listOf("Drywall"),
                7.0 to listOf("Floors", "Countertops"),
                4.0 to listOf("Bathrooms"),
            ),
            assignDays(tasks, 8.0),
        )

        assertEquals(
            listOf(
                14.0 to listOf("Foundation", "Framing", "Plumbing"),
                15.0 to listOf("Electrical", "Insulation", "Drywall", "Floors"),
                6.0 to listOf("Countertops", "Bathrooms"),
            ),
            assignDays(tasks, 16.0),
        )
    }

    @Test
    fun wrapFragmentsWithInfiniteWidths() {
        val words = listOf(TestWord(Double.POSITIVE_INFINITY))
        assertFailsWith<OverflowException> {
            wrapOptimalFit(words, listOf(0.0), Penalties.new())
        }
    }

    @Test
    fun wrapFragmentsWithHugeWidths() {
        val words = listOf(TestWord(1e200), TestWord(1e250), TestWord(1e300))
        assertFailsWith<OverflowException> {
            wrapOptimalFit(words, listOf(1e300), Penalties.new())
        }
    }

    @Test
    fun wrapFragmentsWithLargeWidths() {
        val words = listOf(TestWord(1e25), TestWord(1e50), TestWord(1e75))
        val result = wrapOptimalFit(words, listOf(1e100), Penalties.new())
        assertEquals(listOf(words), result)
    }
}
