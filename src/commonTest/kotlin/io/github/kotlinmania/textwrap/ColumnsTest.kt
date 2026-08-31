// port-lint: tests textwrap/src/columns.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ColumnsTest {
    @Test
    fun wrapColumnsEmptyText() {
        assertEquals(listOf("|        |"), wrapColumns("", 1, 10, "| ", "", " |"))
    }

    @Test
    fun wrapColumnsSingleColumn() {
        assertEquals(
            listOf("| Foo    |        |          |"),
            wrapColumns("Foo", 3, 30, "| ", " | ", " |"),
        )
    }

    @Test
    fun wrapColumnsUnevenColumns() {
        assertEquals(
            listOf("|Foo |Bar |Baz |Quux|"),
            wrapColumns("Foo Bar Baz Quux", 4, 21, "|", "|", "|"),
        )
        assertEquals(
            listOf("|Foo |Bar |Baz |Quux   |"),
            wrapColumns("Foo Bar Baz Quux", 4, 24, "|", "|", "|"),
        )
        assertEquals(
            listOf("|Foo  |Bar  |Baz  |Quux |"),
            wrapColumns("Foo Bar Baz Quux", 4, 25, "|", "|", "|"),
        )
    }

    @Test
    fun wrapColumnsWithEmojis() {
        assertEquals(
            listOf(
                "✨ Words      ⚽ wrapped in 👀",
                "✨ and a few  ⚽ ⓶ columns  👀",
                "✨ emojis 😍  ⚽            👀",
            ),
            wrapColumns(
                "Words and a few emojis 😍 wrapped in ⓶ columns",
                2,
                30,
                "✨ ",
                " ⚽ ",
                " 👀",
            ),
        )
    }

    @Test
    fun wrapColumnsBigGaps() {
        assertEquals(
            listOf(
                "----> x !!! z <----",
                "----> y !!!   <----",
            ),
            wrapColumns("xyz", 2, 10, "----> ", " !!! ", " <----"),
        )
    }

    @Test
    fun wrapColumnsPanicWithZeroColumns() {
        assertFailsWith<IllegalArgumentException> {
            wrapColumns("", 0, 10, "", "", "")
        }
    }
}
