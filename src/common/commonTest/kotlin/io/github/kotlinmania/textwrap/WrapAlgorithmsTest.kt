// port-lint: tests wrap_algorithms.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class WrapAlgorithmsTest {
    private data class Word(
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
                Word(1e307),
                Word(2e307),
                Word(3e307),
                Word(4e307),
                Word(5e307),
                Word(6e307),
            )
        val wrapped = wrapFirstFit(words, listOf(15e307))
        assertEquals(
            listOf(
                listOf(
                    Word(1e307),
                    Word(2e307),
                    Word(3e307),
                    Word(4e307),
                    Word(5e307),
                ),
                listOf(Word(6e307)),
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
}
