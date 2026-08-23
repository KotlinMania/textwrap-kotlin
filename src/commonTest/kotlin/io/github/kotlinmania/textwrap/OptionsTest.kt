// port-lint: tests options.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OptionsTest {
    @Test
    fun optionsAgreeWithUsize() {
        val optInt = Options.from(42)
        val optOptions = Options.new(42)

        assertEquals(optInt.width, optOptions.width)
        assertEquals(optInt.initialIndent, optOptions.initialIndent)
        assertEquals(optInt.subsequentIndent, optOptions.subsequentIndent)
        assertEquals(optInt.breakWords, optOptions.breakWords)
        assertEquals(
            optInt.wordSplitter.splitPoints("hello-world"),
            optOptions.wordSplitter.splitPoints("hello-world"),
        )
    }

    @Test
    fun optionsBuilders() {
        val options =
            Options
                .new(20)
                .initialIndent("* ")
                .subsequentIndent("  ")
                .breakWords(false)
                .lineEnding(LineEnding.CRLF)
                .wordSeparator(WordSeparator.AsciiSpace)
                .wrapAlgorithm(WrapAlgorithm.FirstFit)
                .wordSplitter(WordSplitter.NoHyphenation)

        assertEquals(20, options.width)
        assertEquals("* ", options.initialIndent)
        assertEquals("  ", options.subsequentIndent)
        assertEquals(false, options.breakWords)
        assertEquals(LineEnding.CRLF, options.lineEnding)
        assertEquals(WordSeparator.AsciiSpace, options.wordSeparator)
        assertEquals(WrapAlgorithm.FirstFit, options.wrapAlgorithm)
        assertEquals(WordSplitter.NoHyphenation, options.wordSplitter)
    }

    @Test
    fun optionsWithTermwidth() {
        val options = Options.withTermwidth()
        assertTrue(options.width > 0)
    }
}
