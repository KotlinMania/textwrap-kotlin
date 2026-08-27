// port-lint: tests textwrap/src/word_splitters.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class WordSplittersTest {
    @Test
    fun splitPointsTest() {
        assertEquals(emptyList(), WordSplitter.NoHyphenation.splitPoints("cannot-be-split"))
        assertEquals(listOf(4, 7), WordSplitter.HyphenSplitter.splitPoints("can-be-split"))
        assertEquals(listOf(6), WordSplitter.HyphenSplitter.splitPoints("--foo-bar"))
        val custom = WordSplitter.Custom { word -> listOf(word.length / 2) }
        assertEquals(listOf(3), custom.splitPoints("middle"))
    }

    @Test
    fun splitWordsNoWords() {
        assertEquals(emptyList(), splitWords(emptyList(), WordSplitter.HyphenSplitter).toList())
    }

    @Test
    fun splitWordsEmptyWord() {
        assertEquals(
            listOf(Word.from("   ")),
            splitWords(listOf(Word.from("   ")), WordSplitter.HyphenSplitter).toList(),
        )
    }

    @Test
    fun splitWordsSingleWord() {
        assertEquals(
            listOf(Word.from("foobar")),
            splitWords(listOf(Word.from("foobar")), WordSplitter.HyphenSplitter).toList(),
        )
    }

    @Test
    fun splitWordsHyphenSplitter() {
        assertEquals(
            listOf(Word.from("foo-"), Word.from("bar")),
            splitWords(listOf(Word.from("foo-bar")), WordSplitter.HyphenSplitter).toList(),
        )
    }

    @Test
    fun splitWordsNoHyphenation() {
        assertEquals(
            listOf(Word.from("foo-bar")),
            splitWords(listOf(Word.from("foo-bar")), WordSplitter.NoHyphenation).toList(),
        )
    }

    @Test
    fun splitWordsAddsPenalty() {
        val fixedSplitPoint = WordSplitter.Custom { listOf(3) }
        assertEquals(
            listOf(
                Word(word = "foo", width = 3, whitespace = "", penalty = "-"),
                Word(word = "bar", width = 3, whitespace = "", penalty = ""),
            ),
            splitWords(listOf(Word.from("foobar")), fixedSplitPoint).toList(),
        )

        assertEquals(
            listOf(
                Word(word = "fo-", width = 3, whitespace = "", penalty = ""),
                Word(word = "bar", width = 3, whitespace = "", penalty = ""),
            ),
            splitWords(listOf(Word.from("fo-bar")), fixedSplitPoint).toList(),
        )
    }

    @Test
    fun wordSplittersEquality() {
        assertEquals(WordSplitter.HyphenSplitter, WordSplitter.HyphenSplitter)
        assertEquals(WordSplitter.NoHyphenation, WordSplitter.NoHyphenation)
        val custom1 = WordSplitter.Custom { emptyList() }
        val custom2 = WordSplitter.Custom { emptyList() }
        kotlin.test.assertNotEquals(custom1, custom2)
    }
}
