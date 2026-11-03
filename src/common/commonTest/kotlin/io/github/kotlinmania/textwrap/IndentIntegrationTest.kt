// port-lint: tests ../tests/indent.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class IndentIntegrationTest {
    private val roundtripCases =
        listOf(
            "Hi.\nThis is a test.\nTesting.",
            "Hi.\nThis is a test.\n\nTesting.",
            "\nHi.\nThis is a test.\nTesting.\n",
        )

    private val windowsCases =
        listOf(
            "Hi.\r\nThis is a test.\r\nTesting.",
            "Hi.\r\nThis is a test.\n\r\nTesting.\r\n\n",
        )

    @Test
    fun testIndentNomarginDefault() {
        for (text in roundtripCases) {
            assertEquals(text, indent(text, ""))
        }
        for (text in windowsCases) {
            assertEquals(text, indent(text, ""))
        }
    }

    @Test
    fun testRoundtripSpaces() {
        for (text in roundtripCases) {
            assertEquals(text, dedent(indent(text, "    ")))
        }
    }

    @Test
    fun testRoundtripTabs() {
        for (text in roundtripCases) {
            assertEquals(text, dedent(indent(text, "\t\t")))
        }
    }

    @Test
    fun testRoundtripMixed() {
        for (text in roundtripCases) {
            assertEquals(text, dedent(indent(text, " \t  \t ")))
        }
    }

    @Test
    fun testIndentDefault() {
        val prefix = "  "
        val expectedRoundtrip =
            listOf(
                "  Hi.\n  This is a test.\n  Testing.",
                "  Hi.\n  This is a test.\n\n  Testing.",
                "\n  Hi.\n  This is a test.\n  Testing.\n",
            )
        for (i in roundtripCases.indices) {
            assertEquals(expectedRoundtrip[i], indent(roundtripCases[i], prefix))
        }

        val expectedWindows =
            listOf(
                "  Hi.\r\n  This is a test.\r\n  Testing.",
                "  Hi.\r\n  This is a test.\n\r\n  Testing.\r\n\n",
            )
        for (i in windowsCases.indices) {
            assertEquals(expectedWindows[i], indent(windowsCases[i], prefix))
        }
    }

    @Test
    fun indentedTextShouldHaveTheSameNumberOfLinesAsTheOriginalText() {
        val texts = listOf("foo\nbar", "foo\nbar\n", "foo\nbar\nbaz")
        for (original in texts) {
            assertEquals(original, indent(original, ""))
        }
    }
}
