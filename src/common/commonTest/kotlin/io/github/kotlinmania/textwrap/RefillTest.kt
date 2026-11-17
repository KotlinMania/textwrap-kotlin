// port-lint: tests refill.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class RefillTest {
    @Test
    fun unfillSimple() {
        val (text, options) = unfill("foo\nbar")
        assertEquals("foo bar", text)
        assertEquals(3, options.width)
        assertEquals(LineEnding.LF, options.lineEnding)
    }

    @Test
    fun unfillNoNewLine() {
        val (text, options) = unfill("foo bar")
        assertEquals("foo bar", text)
        assertEquals(7, options.width)
        assertEquals(LineEnding.LF, options.lineEnding)
    }

    @Test
    fun unfillSimpleCrlf() {
        val (text, options) = unfill("foo\r\nbar")
        assertEquals("foo bar", text)
        assertEquals(3, options.width)
        assertEquals(LineEnding.CRLF, options.lineEnding)
    }

    @Test
    fun unfillMixedNewLines() {
        val (text, options) = unfill("foo\r\nbar\nbaz")
        assertEquals("foo bar baz", text)
        assertEquals(3, options.width)
        assertEquals(LineEnding.LF, options.lineEnding)
    }

    @Test
    fun testUnfillConsecutiveDifferentPrefix() {
        val (text, options) = unfill("foo\n*\n/")
        assertEquals("foo * /", text)
        assertEquals(3, options.width)
        assertEquals(LineEnding.LF, options.lineEnding)
    }

    @Test
    fun unfillTrailingNewlines() {
        val (text, options) = unfill("foo\nbar\n\n\n")
        assertEquals("foo bar\n", text)
        assertEquals(3, options.width)
    }

    @Test
    fun unfillMixedTrailingNewlines() {
        val (text, options) = unfill("foo\r\nbar\n\r\n\n")
        assertEquals("foo bar\n", text)
        assertEquals(3, options.width)
        assertEquals(LineEnding.LF, options.lineEnding)
    }

    @Test
    fun unfillTrailingCrlf() {
        val (text, options) = unfill("foo bar\r\n")
        assertEquals("foo bar\r\n", text)
        assertEquals(7, options.width)
        assertEquals(LineEnding.CRLF, options.lineEnding)
    }

    @Test
    fun unfillInitialIndent() {
        val (text, options) = unfill("  foo\nbar\nbaz")
        assertEquals("foo bar baz", text)
        assertEquals(5, options.width)
        assertEquals("  ", options.initialIndent)
    }

    @Test
    fun unfillDifferingIndents() {
        val (text, options) = unfill("  foo\n    bar\n  baz")
        assertEquals("foo   bar baz", text)
        assertEquals(7, options.width)
        assertEquals("  ", options.initialIndent)
        assertEquals("  ", options.subsequentIndent)
    }

    @Test
    fun unfillListItem() {
        val (text, options) = unfill("* foo\n  bar\n  baz")
        assertEquals("foo bar baz", text)
        assertEquals(5, options.width)
        assertEquals("* ", options.initialIndent)
        assertEquals("  ", options.subsequentIndent)
    }

    @Test
    fun unfillMultipleCharPrefix() {
        val (text, options) = unfill("    // foo bar\n    // baz\n    // quux")
        assertEquals("foo bar baz quux", text)
        assertEquals(14, options.width)
        assertEquals("    // ", options.initialIndent)
        assertEquals("    // ", options.subsequentIndent)
    }

    @Test
    fun unfillBlockQuote() {
        val (text, options) = unfill("> foo\n> bar\n> baz")
        assertEquals("foo bar baz", text)
        assertEquals(5, options.width)
        assertEquals("> ", options.initialIndent)
        assertEquals("> ", options.subsequentIndent)
    }

    @Test
    fun unfillOnlyPrefixesIssue466() {
        val (text, options) = unfill("######\nfoo")
        assertEquals(" foo", text)
        assertEquals(6, options.width)
        assertEquals("######", options.initialIndent)
        assertEquals("", options.subsequentIndent)
    }

    @Test
    fun unfillTrailingNewlinesIssue466() {
        val (text, options) = unfill("foo\n##\n\n\r")
        assertEquals("foo ## \r", text)
        assertEquals(3, options.width)
        assertEquals("", options.initialIndent)
        assertEquals("", options.subsequentIndent)
    }

    @Test
    fun unfillWhitespace() {
        assertEquals("foo   bar", unfill("foo   bar").first)
    }

    @Test
    fun refillConvertLfToCrlf() {
        val options = Options.new(5).lineEnding(LineEnding.CRLF)
        assertEquals("foo\r\nbar\r\n", refill("foo\nbar\n", options))
    }

    @Test
    fun refillConvertCrlfToLf() {
        val options = Options.new(5).lineEnding(LineEnding.LF)
        assertEquals("foo\nbar\n", refill("foo\r\nbar\r\n", options))
    }

    @Test
    fun refillConvertMixedNewlines() {
        val options = Options.new(5).lineEnding(LineEnding.CRLF)
        assertEquals("foo\r\nbar\r\n", refill("foo\r\nbar\n", options))
    }

    @Test
    fun refillDefaultsToLf() {
        assertEquals("foo\nbar\nbaz", refill("foo bar baz", 5))
    }
}
