// port-lint: source line_ending.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class LineEndingTest {
    @Test
    fun nonEmptyLinesFullCase() {
        assertEquals(
            listOf(
                "LF" to LineEnding.LF,
                "CRLF" to LineEnding.CRLF,
                "unterminated" to null,
            ),
            NonEmptyLines("LF\nCRLF\r\n\r\n\nunterminated").asSequence().toList(),
        )
    }

    @Test
    fun nonEmptyLinesNewLinesOnly() {
        assertFalse(NonEmptyLines("\r\n\n\n\r\n").hasNext())
    }

    @Test
    fun nonEmptyLinesNoInput() {
        assertFalse(NonEmptyLines("").hasNext())
    }
}
