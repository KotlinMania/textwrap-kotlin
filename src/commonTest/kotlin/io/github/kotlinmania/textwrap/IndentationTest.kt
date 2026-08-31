// port-lint: tests textwrap/src/indentation.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class IndentationTest {
    @Test
    fun indentEmpty() {
        assertEquals("\n", indent("\n", "  "))
    }

    @Test
    fun indentNonempty() {
        val text = listOf("  foo\n", "bar\n", "  baz\n").joinToString("")
        val expected = listOf("//   foo\n", "// bar\n", "//   baz\n").joinToString("")
        assertEquals(expected, indent(text, "// "))
    }

    @Test
    fun indentEmptyLine() {
        val text = listOf("  foo", "bar", "", "  baz").joinToString("\n")
        val expected = listOf("//   foo", "// bar", "//", "//   baz").joinToString("\n")
        assertEquals(expected, indent(text, "// "))
    }

    @Test
    fun dedentEmpty() {
        assertEquals("", dedent(""))
    }

    @Test
    fun dedentMultiLine() {
        val x = listOf("    foo", "  bar", "    baz").joinToString("\n")
        val y = listOf("  foo", "bar", "  baz").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentEmptyLine() {
        val x = listOf("    foo", "  bar", "   ", "    baz").joinToString("\n")
        val y = listOf("  foo", "bar", "", "  baz").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentBlankLine() {
        val x = listOf("      foo", "", "        bar", "          foo", "          bar", "          baz").joinToString("\n")
        val y = listOf("foo", "", "  bar", "    foo", "    bar", "    baz").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentWhitespaceLine() {
        val x = listOf("      foo", " ", "        bar", "          foo", "          bar", "          baz").joinToString("\n")
        val y = listOf("foo", "", "  bar", "    foo", "    bar", "    baz").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentMixedWhitespace() {
        val x = listOf("\tfoo", "  bar").joinToString("\n")
        val y = listOf("\tfoo", "  bar").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentTabbedWhitespace() {
        val x = listOf("\t\tfoo", "\t\t\tbar").joinToString("\n")
        val y = listOf("foo", "\tbar").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentMixedTabbedWhitespace() {
        val x = listOf("\t  \tfoo", "\t  \t\tbar").joinToString("\n")
        val y = listOf("foo", "\tbar").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentMixedTabbedWhitespace2() {
        val x = listOf("\t  \tfoo", "\t    \tbar").joinToString("\n")
        val y = listOf("\tfoo", "  \tbar").joinToString("\n")
        assertEquals(y, dedent(x))
    }

    @Test
    fun dedentPreserveNoTerminatingNewline() {
        val x = listOf("  foo", "    bar").joinToString("\n")
        val y = listOf("foo", "  bar").joinToString("\n")
        assertEquals(y, dedent(x))
    }
}
