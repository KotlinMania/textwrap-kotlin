// port-lint: tests fill.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class FillTest {
    @Test
    fun fillSimple() {
        assertEquals("foo bar\nbaz", fill("foo bar baz", 10))
    }

    @Test
    fun fillUnicodeBoundary() {
        fill("\u001b!Ͽ", 10)
    }

    @Test
    fun nonBreakingSpace() {
        val options = Options.new(5).breakWords(false)
        assertEquals("foo\u00a0bar\u00a0baz", fill("foo\u00a0bar\u00a0baz", options))
    }

    @Test
    fun nonBreakingHyphen() {
        val options = Options.new(5).breakWords(false)
        assertEquals("foo\u2011bar\u2011baz", fill("foo\u2011bar\u2011baz", options))
    }

    @Test
    fun fillPreservesLineBreaksTrimsWhitespace() {
        assertEquals("", fill("  ", 80))
        assertEquals("\n", fill("  \n  ", 80))
        assertEquals("\n\n\n", fill("  \n \n  \n ", 80))
    }

    @Test
    fun preserveLineBreaks() {
        assertEquals("", fill("", 80))
        assertEquals("\n", fill("\n", 80))
        assertEquals("\n\n\n", fill("\n\n\n", 80))
        assertEquals("test\n", fill("test\n", 80))
        assertEquals("test\n\na\n\n", fill("test\n\na\n\n", 80))
        assertEquals(
            "1 3 5 7\n1 3 5 7",
            fill(
                "1 3 5 7\n1 3 5 7",
                Options.new(7).wrapAlgorithm(WrapAlgorithm.FirstFit),
            ),
        )
        assertEquals(
            "1 3 5\n7\n1 3 5\n7",
            fill(
                "1 3 5 7\n1 3 5 7",
                Options.new(5).wrapAlgorithm(WrapAlgorithm.FirstFit),
            ),
        )
    }

    @Test
    fun breakWordsLineBreaks() {
        assertEquals("ab\ncdefg\nhijkl", fill("ab\ncdefghijkl", 5))
        assertEquals("abcde\nfgh\nijkl", fill("abcdefgh\nijkl", 5))
    }

    @Test
    fun breakWordsEmptyLines() {
        assertEquals("foo\nbar", fill("foo\nbar", Options.new(2).breakWords(false)))
    }

    @Test
    fun fillInplaceEmpty() {
        val text = StringBuilder("")
        fillInplace(text, 80)
        assertEquals("", text.toString())
    }

    @Test
    fun fillInplaceSimple() {
        val text = StringBuilder("foo bar baz")
        fillInplace(text, 10)
        assertEquals("foo bar\nbaz", text.toString())
    }

    @Test
    fun fillInplaceMultipleLines() {
        val text = StringBuilder("Some text to wrap over multiple lines")
        fillInplace(text, 12)
        assertEquals("Some text to\nwrap over\nmultiple\nlines", text.toString())
    }

    @Test
    fun fillInplaceLongWord() {
        val text = StringBuilder("Internationalization is hard")
        fillInplace(text, 10)
        assertEquals("Internationalization\nis hard", text.toString())
    }

    @Test
    fun fillInplaceNoHyphenSplitting() {
        val text = StringBuilder("A well-chosen example")
        fillInplace(text, 10)
        assertEquals("A\nwell-chosen\nexample", text.toString())
    }

    @Test
    fun fillInplaceNewlines() {
        val text = StringBuilder("foo bar\n\nbaz\n\n\n")
        fillInplace(text, 10)
        assertEquals("foo bar\n\nbaz\n\n\n", text.toString())
    }

    @Test
    fun fillInplaceNewlinesResetLineWidth() {
        val text = StringBuilder("1 3 5\n1 3 5 7 9\n1 3 5 7 9 1 3")
        fillInplace(text, 10)
        assertEquals("1 3 5\n1 3 5 7 9\n1 3 5 7 9\n1 3", text.toString())
    }

    @Test
    fun fillInplaceLeadingWhitespace() {
        val text = StringBuilder("  foo bar baz")
        fillInplace(text, 10)
        assertEquals("  foo bar\nbaz", text.toString())
    }

    @Test
    fun fillInplaceTrailingWhitespace() {
        val text = StringBuilder("foo bar baz  ")
        fillInplace(text, 10)
        assertEquals("foo bar\nbaz  ", text.toString())
    }

    @Test
    fun fillInplaceInteriorWhitespace() {
        val text = StringBuilder("foo  bar    baz")
        fillInplace(text, 10)
        assertEquals("foo  bar   \nbaz", text.toString())
    }
}
