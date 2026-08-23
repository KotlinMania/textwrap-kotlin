// port-lint: tests core.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CoreTest {
    @Test
    fun skipAnsiEscapeSequenceWorks() {
        val blueText = "\u001b[34mHello\u001b[0m"
        val chars = blueText.iterator()
        val ch = chars.next()
        assertTrue(skipAnsiEscapeSequence(ch, chars))
        assertEquals('H', chars.next())
    }

    @Test
    fun displayWidthWorks() {
        assertEquals(10, "Café Plain".length)
        assertEquals(10, displayWidth("Café Plain"))
        assertEquals(10, displayWidth("\u001b[31mCafé Rouge\u001b[0m"))
        assertEquals(
            14,
            displayWidth("\u001b]8;;http://example.com\u001b\\This is a link\u001b]8;;\u001b\\"),
        )
    }

    @Test
    fun displayWidthNarrowEmojis() {
        assertEquals(1, displayWidth("⁉"))
    }

    @Test
    fun displayWidthNarrowEmojisVariantSelector() {
        assertEquals(1, displayWidth("⁉\ufe0f"))
    }

    @Test
    fun displayWidthEmojis() {
        assertEquals(20, displayWidth("😂😭🥺🤣✨😍🙏🥰😊🔥"))
    }

    @Test
    fun wordFromAndProperties() {
        val word = Word.from("Hello!  ")
        assertEquals("Hello!", word.word)
        assertEquals("  ", word.whitespace)
        assertEquals("", word.penalty)
        assertEquals(6.0, word.width())
        assertEquals(2.0, word.whitespaceWidth())
        assertEquals(0.0, word.penaltyWidth())
    }

    @Test
    fun wordBreakApart() {
        val word = Word.from("Hello!  ")
        val pieces = word.breakApart(3).toList()
        assertEquals(
            listOf(
                Word(word = "Hel", width = 3, whitespace = "", penalty = ""),
                Word(word = "lo!", width = 3, whitespace = "  ", penalty = ""),
            ),
            pieces,
        )
    }

    @Test
    fun breakWordsTest() {
        val words = listOf(Word.from("Hello!  "), Word.from("World"))
        val broken = breakWords(words, 3)
        assertEquals(
            listOf(
                Word(word = "Hel", width = 3, whitespace = "", penalty = ""),
                Word(word = "lo!", width = 3, whitespace = "  ", penalty = ""),
                Word(word = "Wor", width = 3, whitespace = "", penalty = ""),
                Word(word = "ld", width = 2, whitespace = "", penalty = ""),
            ),
            broken,
        )
    }
}
