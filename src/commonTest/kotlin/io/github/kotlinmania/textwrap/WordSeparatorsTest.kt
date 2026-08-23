// port-lint: tests word_separators.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class WordSeparatorsTest {
    private fun toWords(words: List<String>): List<Word> = words.map { Word.from(it) }

    @Test
    fun asciiSpaceEmpty() {
        assertEquals(emptyList(), WordSeparator.AsciiSpace.findWords("").toList())
        assertEquals(emptyList(), WordSeparator.UnicodeBreakProperties.findWords("").toList())
    }

    @Test
    fun singleWord() {
        assertEquals(toWords(listOf("foo")), WordSeparator.AsciiSpace.findWords("foo").toList())
        assertEquals(toWords(listOf("foo")), WordSeparator.UnicodeBreakProperties.findWords("foo").toList())
    }

    @Test
    fun twoWords() {
        assertEquals(toWords(listOf("foo ", "bar")), WordSeparator.AsciiSpace.findWords("foo bar").toList())
        assertEquals(toWords(listOf("foo ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo bar").toList())
    }

    @Test
    fun multipleWords() {
        assertEquals(toWords(listOf("x ", "y ", "z")), WordSeparator.AsciiSpace.findWords("x y z").toList())
        assertEquals(toWords(listOf("x ", "y ", "z")), WordSeparator.UnicodeBreakProperties.findWords("x y z").toList())
    }

    @Test
    fun onlyWhitespace() {
        assertEquals(toWords(listOf(" ")), WordSeparator.AsciiSpace.findWords(" ").toList())
        assertEquals(toWords(listOf(" ")), WordSeparator.UnicodeBreakProperties.findWords(" ").toList())
        assertEquals(toWords(listOf("    ")), WordSeparator.AsciiSpace.findWords("    ").toList())
        assertEquals(toWords(listOf("    ")), WordSeparator.UnicodeBreakProperties.findWords("    ").toList())
    }

    @Test
    fun interWordWhitespace() {
        assertEquals(toWords(listOf("foo   ", "bar")), WordSeparator.AsciiSpace.findWords("foo   bar").toList())
        assertEquals(toWords(listOf("foo   ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo   bar").toList())
    }

    @Test
    fun trailingWhitespace() {
        assertEquals(toWords(listOf("foo   ")), WordSeparator.AsciiSpace.findWords("foo   ").toList())
        assertEquals(toWords(listOf("foo   ")), WordSeparator.UnicodeBreakProperties.findWords("foo   ").toList())
    }

    @Test
    fun leadingWhitespace() {
        assertEquals(toWords(listOf("   ", "foo")), WordSeparator.AsciiSpace.findWords("   foo").toList())
        assertEquals(toWords(listOf("   ", "foo")), WordSeparator.UnicodeBreakProperties.findWords("   foo").toList())
    }

    @Test
    fun hyphens() {
        assertEquals(toWords(listOf("foo-bar")), WordSeparator.AsciiSpace.findWords("foo-bar").toList())
        assertEquals(toWords(listOf("foo-bar")), WordSeparator.UnicodeBreakProperties.findWords("foo-bar").toList())

        assertEquals(toWords(listOf("foo- ", "bar")), WordSeparator.AsciiSpace.findWords("foo- bar").toList())
        assertEquals(toWords(listOf("foo- ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo- bar").toList())

        assertEquals(toWords(listOf("foo ", "- ", "bar")), WordSeparator.AsciiSpace.findWords("foo - bar").toList())
        assertEquals(toWords(listOf("foo ", "- ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo - bar").toList())

        assertEquals(toWords(listOf("foo ", "-bar")), WordSeparator.AsciiSpace.findWords("foo -bar").toList())
        assertEquals(toWords(listOf("foo ", "-bar")), WordSeparator.UnicodeBreakProperties.findWords("foo -bar").toList())
    }

    @Test
    fun newlineAndTab() {
        assertEquals(toWords(listOf("foo\nbar")), WordSeparator.AsciiSpace.findWords("foo\nbar").toList())
        assertEquals(toWords(listOf("foo\n", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo\nbar").toList())

        assertEquals(toWords(listOf("foo\tbar")), WordSeparator.AsciiSpace.findWords("foo\tbar").toList())
        assertEquals(toWords(listOf("foo\t", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo\tbar").toList())
    }

    @Test
    fun nonBreakingSpace() {
        assertEquals(toWords(listOf("foo\u00a0bar")), WordSeparator.AsciiSpace.findWords("foo\u00a0bar").toList())
        assertEquals(toWords(listOf("foo\u00a0bar")), WordSeparator.UnicodeBreakProperties.findWords("foo\u00a0bar").toList())
    }

    @Test
    fun unicodeEmojisAndCjk() {
        assertEquals(
            toWords(listOf("Emojis: ", "😂", "😍")),
            WordSeparator.UnicodeBreakProperties.findWords("Emojis: 😂😍").toList(),
        )
        assertEquals(
            toWords(listOf("CJK: ", "你", "好")),
            WordSeparator.UnicodeBreakProperties.findWords("CJK: 你好").toList(),
        )
        assertEquals(
            toWords(listOf("Emojis: ", "😂\u2060😍")),
            WordSeparator.UnicodeBreakProperties.findWords("Emojis: 😂\u2060😍").toList(),
        )
        assertEquals(
            toWords(listOf("[ foo ] ", "bar !")),
            WordSeparator.UnicodeBreakProperties.findWords("[ foo ] bar !").toList(),
        )
    }

    @Test
    fun coloredText() {
        val text = "foo\u001b[0m\u001b[32mbar\u001b[0mbaz"
        assertEquals(toWords(listOf(text)), WordSeparator.AsciiSpace.findWords(text).toList())
        assertEquals(toWords(listOf(text)), WordSeparator.UnicodeBreakProperties.findWords(text).toList())
    }

    @Test
    fun separatorEquality() {
        assertEquals(WordSeparator.AsciiSpace, WordSeparator.AsciiSpace)
        assertEquals(WordSeparator.UnicodeBreakProperties, WordSeparator.UnicodeBreakProperties)
        val custom1 = WordSeparator.Custom { sequenceOf(Word.from(it)) }
        val custom2 = WordSeparator.Custom { sequenceOf(Word.from(it)) }
        assertNotEquals(custom1, custom2)
    }
}
