// port-lint: tests wrap.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class WrapTest {
    @Test
    fun noWrap() {
        assertEquals(listOf("foo"), wrap("foo", 10))
    }

    @Test
    fun wrapSimple() {
        assertEquals(listOf("foo", "bar", "baz"), wrap("foo bar baz", 5))
    }

    @Test
    fun toBeOrNot() {
        assertEquals(
            listOf("To be, or", "not to be,", "that is", "the", "question."),
            wrap(
                "To be, or not to be, that is the question.",
                Options.new(10).wrapAlgorithm(WrapAlgorithm.FirstFit),
            ),
        )
    }

    @Test
    fun multipleWordsOnFirstLine() {
        assertEquals(listOf("foo bar", "baz"), wrap("foo bar baz", 10))
    }

    @Test
    fun longWord() {
        assertEquals(listOf("f", "o", "o"), wrap("foo", 0))
    }

    @Test
    fun longWords() {
        assertEquals(listOf("f", "o", "o", "b", "a", "r"), wrap("foo bar", 0))
    }

    @Test
    fun maxWidth() {
        assertEquals(listOf("foo bar"), wrap("foo bar", Int.MAX_VALUE))
        val text = "Hello there! This is some English text. It should not be wrapped given the extents below."
        assertEquals(listOf(text), wrap(text, Int.MAX_VALUE))
    }

    @Test
    fun leadingWhitespace() {
        assertEquals(listOf("  foo", "bar"), wrap("  foo bar", 6))
    }

    @Test
    fun leadingWhitespaceEmptyFirstLine() {
        assertEquals(listOf("", "foobar", "baz"), wrap(" foobar baz", 6))
    }

    @Test
    fun trailingWhitespace() {
        assertEquals(listOf("foo", "bar", "baz"), wrap("foo     bar     baz  ", 5))
    }

    @Test
    fun issue99() {
        assertEquals(
            listOf("aaabbbccc", "x", "yyyzzzwww"),
            wrap("aaabbbccc x yyyzzzwww", 9),
        )
    }

    @Test
    fun issue129() {
        val options = Options.new(1).wordSeparator(WordSeparator.AsciiSpace)
        assertEquals(listOf("x", "–", "x"), wrap("x – x", options))
    }

    @Test
    fun wideCharacterHandling() {
        assertEquals(listOf("Hello, World!"), wrap("Hello, World!", 15))
        assertEquals(
            listOf("Ｈｅｌｌｏ,", "Ｗｏｒｌｄ!"),
            wrap(
                "Ｈｅｌｌｏ, Ｗｏｒｌｄ!",
                Options.new(15).wordSeparator(WordSeparator.AsciiSpace),
            ),
        )
    }

    @Test
    fun indentEmptyLine() {
        val options = Options.new(10).initialIndent("!!!")
        assertEquals(listOf("!!!"), wrap("", options))
    }

    @Test
    fun indentSingleLine() {
        val options = Options.new(10).initialIndent(">>>")
        assertEquals(listOf(">>>foo"), wrap("foo", options))
    }

    @Test
    fun indentFirstEmoji() {
        val options = Options.new(10).initialIndent("👉👉")
        assertEquals(
            listOf("👉👉x x x", "x x x x x", "x x x x x"),
            wrap("x x x x x x x x x x x x x", options),
        )
    }

    @Test
    fun indentMultipleLines() {
        val options = Options.new(6).initialIndent("* ").subsequentIndent("  ")
        assertEquals(
            listOf("* foo", "  bar", "  baz"),
            wrap("foo bar baz", options),
        )
    }

    @Test
    fun onlyInitialIndentMultipleLines() {
        val options = Options.new(10).initialIndent("  ")
        assertEquals(listOf("  foo", "bar", "baz"), wrap("foo\nbar\nbaz", options))
    }

    @Test
    fun onlySubsequentIndentMultipleLines() {
        val options = Options.new(10).subsequentIndent("  ")
        assertEquals(
            listOf("foo", "  bar", "  baz"),
            wrap("foo\nbar\nbaz", options),
        )
    }

    @Test
    fun indentBreakWords() {
        val options = Options.new(5).initialIndent("* ").subsequentIndent("  ")
        assertEquals(listOf("* foo", "  bar", "  baz"), wrap("foobarbaz", options))
    }

    @Test
    fun initialIndentBreakWords() {
        val options = Options.new(5).initialIndent("-->")
        assertEquals(listOf("-->", "fooba", "rbaz"), wrap("foobarbaz", options))
    }

    @Test
    fun hyphens() {
        assertEquals(listOf("foo-", "bar"), wrap("foo-bar", 5))
    }

    @Test
    fun trailingHyphen() {
        val options = Options.new(5).breakWords(false)
        assertEquals(listOf("foobar-"), wrap("foobar-", options))
    }

    @Test
    fun multipleHyphens() {
        assertEquals(listOf("foo-", "bar-", "baz"), wrap("foo-bar-baz", 5))
    }

    @Test
    fun hyphensFlag() {
        val options = Options.new(5).breakWords(false)
        assertEquals(
            listOf("The", "--foo-", "bar", "flag."),
            wrap("The --foo-bar flag.", options),
        )
    }

    @Test
    fun repeatedHyphens() {
        val options = Options.new(4).breakWords(false)
        assertEquals(listOf("foo--bar"), wrap("foo--bar", options))
    }

    @Test
    fun hyphensAlphanumeric() {
        assertEquals(listOf("Na2-", "CH4"), wrap("Na2-CH4", 5))
    }

    @Test
    fun hyphensNonAlphanumeric() {
        val options = Options.new(5).breakWords(false)
        assertEquals(listOf("foo(-)bar"), wrap("foo(-)bar", options))
    }

    @Test
    fun multipleSplits() {
        assertEquals(listOf("foo-bar-", "baz"), wrap("foo-bar-baz", 9))
    }

    @Test
    fun forcedSplit() {
        val options = Options.new(5).breakWords(false)
        assertEquals(listOf("foobar-", "baz"), wrap("foobar-baz", options))
    }

    @Test
    fun multipleUnbrokenWordsIssue193() {
        val options = Options.new(3).breakWords(false)
        assertEquals(
            listOf("small", "large", "tiny"),
            wrap("small large tiny", options),
        )
        assertEquals(
            listOf("small", "large", "tiny"),
            wrap("small  large   tiny", options),
        )
    }

    @Test
    fun veryNarrowLinesIssue193() {
        val options = Options.new(1).breakWords(false)
        assertEquals(listOf("fooo", "x", "y"), wrap("fooo x y", options))
        assertEquals(listOf("fooo", "x", "y"), wrap("fooo   x     y", options))
    }

    @Test
    fun simpleHyphens() {
        val options = Options.new(8).wordSplitter(WordSplitter.HyphenSplitter)
        assertEquals(listOf("foo bar-", "baz"), wrap("foo bar-baz", options))
    }

    @Test
    fun noHyphenation() {
        val options = Options.new(8).wordSplitter(WordSplitter.NoHyphenation)
        assertEquals(listOf("foo", "bar-baz"), wrap("foo bar-baz", options))
    }

    @Test
    fun autoHyphenationDoubleHyphenation() {
        val options = Options.new(10)
        assertEquals(
            listOf("Internatio", "nalization"),
            wrap("Internationalization", options),
        )

        val customHyphenation =
            WordSplitter.Custom { word ->
                if (word == "Internationalization") listOf(7, 16) else emptyList()
            }
        val options2 = Options.new(10).wordSplitter(customHyphenation)
        assertEquals(
            listOf("Interna-", "tionaliza-", "tion"),
            wrap("Internationalization", options2),
        )
    }

    @Test
    fun autoHyphenationIssue158() {
        val options = Options.new(10)
        assertEquals(
            listOf("participat", "ion is", "the key to", "success"),
            wrap("participation is the key to success", options),
        )

        val customHyphenation =
            WordSplitter.Custom { word ->
                if (word == "participation") listOf(7) else emptyList()
            }
        val options2 = Options.new(10).wordSplitter(customHyphenation)
        assertEquals(
            listOf("partici-", "pation is", "the key to", "success"),
            wrap("participation is the key to success", options2),
        )
    }

    @Test
    fun splitLenHyphenation() {
        val customHyphenation =
            WordSplitter.Custom { word ->
                if (word == "collection") listOf(3) else emptyList()
            }
        val options = Options.new(15).wordSplitter(customHyphenation)
        assertEquals(
            listOf("garbage   col-", "lection"),
            wrap("garbage   collection", options),
        )
    }

    @Test
    fun autoHyphenationWithHyphen() {
        val options = Options.new(8).breakWords(false)
        assertEquals(
            listOf("over-", "caffinated"),
            wrap("over-caffinated", options),
        )

        val customHyphenation =
            WordSplitter.Custom { word ->
                if (word == "caffinated") {
                    listOf(5)
                } else if (word == "over-caffinated") {
                    listOf(5, 10)
                } else {
                    emptyList()
                }
            }
        val options2 = options.wordSplitter(customHyphenation)
        assertEquals(
            listOf("over-", "caffi-", "nated"),
            wrap("over-caffinated", options2),
        )
    }

    @Test
    fun borrowedLines() {
        val customHyphenation =
            WordSplitter.Custom { word ->
                if (word == "Internationalization") listOf(7, 16) else emptyList()
            }
        val options = Options.new(10).wordSplitter(customHyphenation)
        val lines = wrap("Internationalization", options)
        assertEquals(listOf("Interna-", "tionaliza-", "tion"), lines)
    }

    @Test
    fun breakWords() {
        assertEquals(listOf("foo", "bar", "baz"), wrap("foobarbaz", 3))
    }

    @Test
    fun breakWordsWideCharacters() {
        val options = Options.new(5).wordSeparator(WordSeparator.AsciiSpace)
        assertEquals(listOf("Ｈｅ", "ｌｌ", "ｏ"), wrap("Ｈｅｌｌｏ", options))
    }

    @Test
    fun breakWordsZeroWidth() {
        assertEquals(listOf("f", "o", "o", "b", "a", "r"), wrap("foobar", 0))
    }

    @Test
    fun breakLongFirstWord() {
        assertEquals(listOf("test", "x y"), wrap("testx y", 4))
    }

    @Test
    fun wrapPreservesLineBreaksTrimsWhitespace() {
        assertEquals(listOf(""), wrap("  ", 80))
        assertEquals(listOf("", ""), wrap("  \n  ", 80))
        assertEquals(listOf("", "", "", ""), wrap("  \n \n  \n ", 80))
    }

    @Test
    fun wrapColoredText() {
        val greenHello = "\u001b[0m\u001b[32mHello\u001b[0m"
        val blueWorld = "\u001b[0m\u001b[34mWorld!\u001b[0m"
        assertEquals(
            listOf(greenHello, blueWorld),
            wrap("$greenHello $blueWorld", 6),
        )
    }
}
