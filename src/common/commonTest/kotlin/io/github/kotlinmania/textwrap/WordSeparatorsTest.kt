// port-lint: tests word_separators.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class WordSeparatorsTest {
    internal fun toWords(words: List<String>): List<Word> = words.map { Word.from(it) }

    @Test
    fun asciiSpaceEmpty() {
        assertEquals(emptyList(), WordSeparator.AsciiSpace.findWords("").toList())
    }

    @Test
    fun unicodeEmpty() {
        assertEquals(emptyList(), WordSeparator.UnicodeBreakProperties.findWords("").toList())
    }

    @Test
    fun asciiSingleWord() {
        assertEquals(toWords(listOf("foo")), WordSeparator.AsciiSpace.findWords("foo").toList())
    }

    @Test
    fun unicodeSingleWord() {
        assertEquals(toWords(listOf("foo")), WordSeparator.UnicodeBreakProperties.findWords("foo").toList())
    }

    @Test
    fun asciiTwoWords() {
        assertEquals(toWords(listOf("foo ", "bar")), WordSeparator.AsciiSpace.findWords("foo bar").toList())
    }

    @Test
    fun unicodeTwoWords() {
        assertEquals(toWords(listOf("foo ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo bar").toList())
    }

    @Test
    fun asciiMultipleWords() {
        assertEquals(toWords(listOf("foo ", "bar")), WordSeparator.AsciiSpace.findWords("foo bar").toList())
        assertEquals(toWords(listOf("x ", "y ", "z")), WordSeparator.AsciiSpace.findWords("x y z").toList())
    }

    @Test
    fun unicodeMultipleWords() {
        assertEquals(toWords(listOf("foo ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo bar").toList())
        assertEquals(toWords(listOf("x ", "y ", "z")), WordSeparator.UnicodeBreakProperties.findWords("x y z").toList())
    }

    @Test
    fun asciiOnlyWhitespace() {
        assertEquals(toWords(listOf(" ")), WordSeparator.AsciiSpace.findWords(" ").toList())
        assertEquals(toWords(listOf("    ")), WordSeparator.AsciiSpace.findWords("    ").toList())
    }

    @Test
    fun unicodeOnlyWhitespace() {
        assertEquals(toWords(listOf(" ")), WordSeparator.UnicodeBreakProperties.findWords(" ").toList())
        assertEquals(toWords(listOf("    ")), WordSeparator.UnicodeBreakProperties.findWords("    ").toList())
    }

    @Test
    fun asciiInterWordWhitespace() {
        assertEquals(toWords(listOf("foo   ", "bar")), WordSeparator.AsciiSpace.findWords("foo   bar").toList())
    }

    @Test
    fun unicodeInterWordWhitespace() {
        assertEquals(toWords(listOf("foo   ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo   bar").toList())
    }

    @Test
    fun asciiTrailingWhitespace() {
        assertEquals(toWords(listOf("foo   ")), WordSeparator.AsciiSpace.findWords("foo   ").toList())
    }

    @Test
    fun unicodeTrailingWhitespace() {
        assertEquals(toWords(listOf("foo   ")), WordSeparator.UnicodeBreakProperties.findWords("foo   ").toList())
    }

    @Test
    fun asciiLeadingWhitespace() {
        assertEquals(toWords(listOf("   ", "foo")), WordSeparator.AsciiSpace.findWords("   foo").toList())
    }

    @Test
    fun unicodeLeadingWhitespace() {
        assertEquals(toWords(listOf("   ", "foo")), WordSeparator.UnicodeBreakProperties.findWords("   foo").toList())
    }

    @Test
    fun asciiMultiColumnChar() {
        assertEquals(toWords(listOf("\uD83E\uDD20")), WordSeparator.AsciiSpace.findWords("\uD83E\uDD20").toList())
    }

    @Test
    fun unicodeMultiColumnChar() {
        assertEquals(toWords(listOf("\uD83E\uDD20")), WordSeparator.UnicodeBreakProperties.findWords("\uD83E\uDD20").toList())
    }

    @Test
    fun asciiHyphens() {
        assertEquals(toWords(listOf("foo-bar")), WordSeparator.AsciiSpace.findWords("foo-bar").toList())
        assertEquals(toWords(listOf("foo- ", "bar")), WordSeparator.AsciiSpace.findWords("foo- bar").toList())
        assertEquals(toWords(listOf("foo ", "- ", "bar")), WordSeparator.AsciiSpace.findWords("foo - bar").toList())
        assertEquals(toWords(listOf("foo ", "-bar")), WordSeparator.AsciiSpace.findWords("foo -bar").toList())
    }

    @Test
    fun unicodeHyphens() {
        assertEquals(toWords(listOf("foo-bar")), WordSeparator.UnicodeBreakProperties.findWords("foo-bar").toList())
        assertEquals(toWords(listOf("foo- ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo- bar").toList())
        assertEquals(toWords(listOf("foo ", "- ", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo - bar").toList())
        assertEquals(toWords(listOf("foo ", "-bar")), WordSeparator.UnicodeBreakProperties.findWords("foo -bar").toList())
    }

    @Test
    fun asciiNewline() {
        assertEquals(toWords(listOf("foo\nbar")), WordSeparator.AsciiSpace.findWords("foo\nbar").toList())
    }

    @Test
    fun unicodeNewline() {
        assertEquals(toWords(listOf("foo\n", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo\nbar").toList())
    }

    @Test
    fun asciiTab() {
        assertEquals(toWords(listOf("foo\tbar")), WordSeparator.AsciiSpace.findWords("foo\tbar").toList())
    }

    @Test
    fun unicodeTab() {
        assertEquals(toWords(listOf("foo\t", "bar")), WordSeparator.UnicodeBreakProperties.findWords("foo\tbar").toList())
    }

    @Test
    fun asciiNonBreakingSpace() {
        assertEquals(toWords(listOf("foo\u00a0bar")), WordSeparator.AsciiSpace.findWords("foo\u00a0bar").toList())
    }

    @Test
    fun unicodeNonBreakingSpace() {
        assertEquals(toWords(listOf("foo\u00a0bar")), WordSeparator.UnicodeBreakProperties.findWords("foo\u00a0bar").toList())
    }

    @Test
    fun findWordsColoredText() {
        val greenHello = "\u001b[32mHello\u001b[0m "
        val blueWorld = "\u001b[34mWorld!\u001b[0m"
        val full = "$greenHello$blueWorld"
        assertEquals(
            listOf(Word.from(greenHello), Word.from(blueWorld)),
            WordSeparator.AsciiSpace.findWords(full).toList(),
        )
        assertEquals(
            listOf(Word.from(greenHello), Word.from(blueWorld)),
            WordSeparator.UnicodeBreakProperties.findWords(full).toList(),
        )
    }

    @Test
    fun findWordsColorInsideWord() {
        val text = "foo\u001b[0m\u001b[32mbar\u001b[0mbaz"
        assertEquals(listOf(Word.from(text)), WordSeparator.AsciiSpace.findWords(text).toList())
        assertEquals(listOf(Word.from(text)), WordSeparator.UnicodeBreakProperties.findWords(text).toList())
    }

    @Test
    fun wordSeparatorNew() {
        assertEquals(WordSeparator.UnicodeBreakProperties, WordSeparator.new())
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
    fun separatorEquality() {
        assertEquals(WordSeparator.AsciiSpace, WordSeparator.AsciiSpace)
        assertEquals(WordSeparator.UnicodeBreakProperties, WordSeparator.UnicodeBreakProperties)
        val custom1 = WordSeparator.Custom { listOf(Word.from(it)) }
        val custom2 = WordSeparator.Custom { listOf(Word.from(it)) }
        assertNotEquals(custom1, custom2)
    }
}
