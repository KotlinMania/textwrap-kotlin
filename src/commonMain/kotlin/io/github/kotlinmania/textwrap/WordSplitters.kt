// port-lint: source textwrap/src/word_splitters.rs
package io.github.kotlinmania.textwrap

/**
 * Custom word splitter function.
 */
fun interface WordSplitterFunction {
    fun splitPoints(word: String): List<Int>
}

/**
 * The [WordSplitter] enum describes where words can be split.
 */
sealed interface WordSplitter {
    /**
     * Return all possible indices where [word] can be split.
     *
     * The indices are in the range `0..word.length`. They point to the index after
     * the split point, i.e., after `-` if splitting on hyphens.
     */
    fun splitPoints(word: String): List<Int>

    /**
     * Compare two word splitters for equality.
     */
    fun eq(other: WordSplitter): Boolean = this == other

    /**
     * Clone this word splitter.
     */
    fun clone(): WordSplitter = this

    /**
     * Avoid any kind of hyphenation.
     */
    data object NoHyphenation : WordSplitter {
        override fun splitPoints(word: String): List<Int> = emptyList()
    }

    /**
     * Default [WordSplitter]. Will split words on existing hyphens in the word
     * surrounded by alphanumeric characters.
     */
    data object HyphenSplitter : WordSplitter {
        override fun splitPoints(word: String): List<Int> {
            val splits = mutableListOf<Int>()
            for (idx in word.indices) {
                if (word[idx] == '-') {
                    val prev = if (idx > 0) word[idx - 1] else null
                    val next = if (idx + 1 < word.length) word[idx + 1] else null
                    if (prev != null && prev.isLetterOrDigit() && next != null && next.isLetterOrDigit()) {
                        splits.add(idx + 1)
                    }
                }
            }
            return splits
        }
    }

    /**
     * Use a custom function as the word splitter.
     */
    class Custom(
        val splitter: WordSplitterFunction,
    ) : WordSplitter {
        override fun splitPoints(word: String): List<Int> = splitter.splitPoints(word)

        override fun clone(): WordSplitter = Custom(splitter)

        override fun equals(other: Any?): Boolean = this === other || (other is Custom && splitter == other.splitter)

        override fun hashCode(): Int = splitter.hashCode()
    }


}

/**
 * Split words into smaller words according to the split points given by [wordSplitter].
 */
fun splitWords(
    words: Iterable<Word>,
    wordSplitter: WordSplitter,
): Sequence<Word> =
    sequence {
        for (word in words) {
            val splitPoints = wordSplitter.splitPoints(word.word)
            var prev = 0
            for (idx in splitPoints) {
                val pieceStr = word.word.substring(prev, idx)
                val needHyphen = !word.word.substring(0, idx).endsWith('-')
                yield(
                    Word(
                        word = pieceStr,
                        width = displayWidth(pieceStr),
                        whitespace = "",
                        penalty = if (needHyphen) "-" else "",
                    ),
                )
                prev = idx
            }
            if (prev < word.word.length || prev == 0) {
                val pieceStr = word.word.substring(prev)
                yield(
                    Word(
                        word = pieceStr,
                        width = displayWidth(pieceStr),
                        whitespace = word.whitespace,
                        penalty = word.penalty,
                    ),
                )
            }
        }
    }
