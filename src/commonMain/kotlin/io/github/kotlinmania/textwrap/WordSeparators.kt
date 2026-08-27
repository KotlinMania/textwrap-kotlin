// port-lint: source word_separators.rs
package io.github.kotlinmania.textwrap

private const val SHY: Char = '\u00ad'
private const val WORD_JOINER: Char = '\u2060'
private const val ZERO_WIDTH_JOINER: Char = '\u200d'
private const val NON_BREAKING_SPACE: Char = '\u00a0'

/**
 * Custom word separator function.
 */
fun interface WordSeparatorFunction {
    fun findWords(line: String): List<Word>
}

/**
 * Describes where words occur in a line of text.
 *
 * The simplest approach is to say that words are separated by one or more
 * ASCII spaces (`' '`). This works for Western languages without emojis. A more
 * complex approach is to use the Unicode line breaking algorithm.
 */
sealed interface WordSeparator {
    /**
     * Find all words in [line].
     */
    fun findWords(line: String): Sequence<Word>

    /**
     * Compare two word separators for equality.
     */
    fun eq(other: WordSeparator): Boolean = this == other

    /**
     * Clone this word separator.
     */
    fun clone(): WordSeparator = this

    /**
     * Find words by splitting on runs of `' '` characters.
     */
    data object AsciiSpace : WordSeparator {
        override fun findWords(line: String): Sequence<Word> = findWordsAsciiSpace(line)
    }

    /**
     * Split `line` into words using Unicode break properties.
     */
    data object UnicodeBreakProperties : WordSeparator {
        override fun findWords(line: String): Sequence<Word> = findWordsUnicodeBreakProperties(line)
    }

    /**
     * Find words using a custom word separator function.
     */
    class Custom(
        val separator: WordSeparatorFunction,
    ) : WordSeparator {
        override fun findWords(line: String): Sequence<Word> = separator.findWords(line).asSequence()

        override fun clone(): WordSeparator = Custom(separator)

        override fun equals(other: Any?): Boolean = this === other || (other is Custom && separator == other.separator)

        override fun hashCode(): Int = separator.hashCode()
    }





    companion object {
        /**
         * Create a new word separator using the default algorithm.
         */
        fun new(): WordSeparator = UnicodeBreakProperties
    }
}

internal fun findWordsAsciiSpace(line: String): Sequence<Word> =
    sequence {
        if (line.isEmpty()) return@sequence
        var start = 0
        var inWhitespace = false
        for (idx in line.indices) {
            val ch = line[idx]
            if (inWhitespace && ch != ' ') {
                yield(Word.from(line.substring(start, idx)))
                start = idx
                inWhitespace = ch == ' '
                continue
            }
            inWhitespace = ch == ' '
        }
        if (start < line.length) {
            yield(Word.from(line.substring(start)))
        }
    }

internal fun stripAnsiEscapeSequences(text: String): String {
    val chars = text.iterator()
    val sb = StringBuilder()
    while (chars.hasNext()) {
        val ch = chars.next()
        if (skipAnsiEscapeSequence(ch, chars)) {
            continue
        }
        sb.append(ch)
    }
    return sb.toString()
}

internal fun findWordsUnicodeBreakProperties(line: String): Sequence<Word> =
    sequence {
        if (line.isEmpty()) return@sequence

        val breaks = findUnicodeLineBreaks(line)
        var start = 0
        for (brk in breaks) {
            if (brk > start && brk <= line.length) {
                yield(Word.from(line.substring(start, brk)))
                start = brk
            }
        }
        if (start < line.length) {
            yield(Word.from(line.substring(start)))
        }
    }

internal fun toWords(words: List<String>): List<Word> = words.map { Word.from(it) }


private fun isCjk(ch: Char): Boolean =
    ch in '\u4e00'..'\u9fff' ||
        ch in '\u3400'..'\u4dbf' ||
        ch in '\u3040'..'\u309f' ||
        ch in '\u30a0'..'\u30ff' ||
        ch in '\uac00'..'\ud7af'

private fun isEmojiHigh(ch: Char): Boolean = ch.code in 0xd800..0xdbff

private fun findUnicodeLineBreaks(line: String): List<Int> {
    val breaks = mutableListOf<Int>()
    val n = line.length
    var i = 0

    while (i < n) {
        val ch = line[i]

        if (ch == '\u001b') {
            val subChars = line.substring(i + 1).iterator()
            if (skipAnsiEscapeSequence(ch, subChars)) {
                val consumed = (n - (i + 1)) - subChars.asSequence().count()
                i += 1 + consumed
                continue
            }
        }

        if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
            var j = i
            while (j < n && (line[j] == ' ' || line[j] == '\t' || line[j] == '\n' || line[j] == '\r')) {
                j++
            }
            if (i == 0) {
                breaks.add(j)
                i = j
                continue
            }
            if (j < n) {
                val nextCh = line[j]
                if (nextCh == '!' ||
                    nextCh == '?' ||
                    nextCh == ']' ||
                    nextCh == ')' ||
                    nextCh == '}' ||
                    nextCh == ':' ||
                    nextCh == ';' ||
                    nextCh == ',' ||
                    nextCh == '.'
                ) {
                    var k = j
                    while (k < n &&
                        (
                            line[k] == '!' ||
                                line[k] == '?' ||
                                line[k] == ']' ||
                                line[k] == ')' ||
                                line[k] == '}' ||
                                line[k] == ':' ||
                                line[k] == ';' ||
                                line[k] == ',' ||
                                line[k] == '.'
                        )
                    ) {
                        k++
                    }
                    breaks.add(k)
                    i = k
                    continue
                }
            }
            breaks.add(j)
            i = j
            continue
        }

        if (ch == NON_BREAKING_SPACE || ch == '-' || ch == SHY) {
            i++
            continue
        }

        if (isCjk(ch)) {
            if (i + 1 < n) {
                val nextCh = line[i + 1]
                if (nextCh != WORD_JOINER &&
                    nextCh != ZERO_WIDTH_JOINER &&
                    nextCh != ' ' &&
                    nextCh != '\t' &&
                    nextCh != '\n' &&
                    nextCh != '\r'
                ) {
                    breaks.add(i + 1)
                }
            }
            i++
            continue
        }

        if (isEmojiHigh(ch) && i + 1 < n && line[i + 1].isLowSurrogate()) {
            val emojiEnd = i + 2
            if (emojiEnd < n) {
                val nextCh = line[emojiEnd]
                if (nextCh != WORD_JOINER &&
                    nextCh != ZERO_WIDTH_JOINER &&
                    nextCh != ' ' &&
                    nextCh != '\t' &&
                    nextCh != '\n' &&
                    nextCh != '\r'
                ) {
                    breaks.add(emojiEnd)
                }
            }
            i = emojiEnd
            continue
        }

        if (ch == '[') {
            val closeIdx = line.indexOf(']', i)
            if (closeIdx != -1) {
                var afterClose = closeIdx + 1
                while (afterClose < n && (line[afterClose] == ' ' || line[afterClose] == '\t')) {
                    afterClose++
                }
                breaks.add(afterClose)
                i = afterClose
                continue
            }
        }

        i++
    }

    return breaks
}
