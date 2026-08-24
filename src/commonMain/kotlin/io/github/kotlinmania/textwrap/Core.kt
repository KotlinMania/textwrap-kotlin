// port-lint: source core.rs
package io.github.kotlinmania.textwrap

private const val CSI_FIRST = '\u001b'
private const val CSI_SECOND = '['
private val ANSI_FINAL_BYTE_RANGE = '\u0040'..'\u007e'

/**
 * Skip ANSI escape sequences.
 *
 * [ch] is the current character, and [chars] provides following characters.
 * [chars] will be consumed if [ch] is the start of an ANSI escape sequence.
 *
 * Returns `true` if one or more characters were skipped.
 */
internal fun skipAnsiEscapeSequence(
    ch: Char,
    chars: Iterator<Char>,
): Boolean {
    if (ch != CSI_FIRST) {
        return false
    }

    if (!chars.hasNext()) {
        return false
    }

    val next = chars.next()
    if (next == CSI_SECOND) {
        while (chars.hasNext()) {
            val c = chars.next()
            if (c in ANSI_FINAL_BYTE_RANGE) {
                break
            }
        }
    } else if (next == ']') {
        var last = ']'
        while (chars.hasNext()) {
            val newChar = chars.next()
            if (newChar == '\u0007' || (newChar == '\\' && last == CSI_FIRST)) {
                break
            }
            last = newChar
        }
    }

    return true
}

/**
 * Returns the column display width of a single character.
 */
internal fun chWidth(ch: Char): Int {
    if (ch.isLowSurrogate()) {
        return 0
    }
    if (ch.isHighSurrogate()) {
        return 2
    }
    val code = ch.code
    if (code < 0x20 || (code in 0x7f..0x9f)) {
        return 0
    }
    if (code in 0x200b..0x200f || code in 0x2028..0x202e || code == 0xfeff || code == 0xfe0f) {
        return 0
    }
    if (code in 0x1100..0x115f ||
        code in 0x2329..0x232a ||
        code in 0x2600..0x27bf ||
        code in 0x2e80..0x303e ||
        code in 0x3040..0x4dbf ||
        code in 0x4e00..0xa4cf ||
        code in 0xac00..0xd7a3 ||
        code in 0xf900..0xfaff ||
        code in 0xfe10..0xfe19 ||
        code in 0xfe30..0xfe6f ||
        code in 0xff01..0xff60 ||
        code in 0xffe0..0xffe6
    ) {
        return 2
    }
    return 1
}

/**
 * Compute the display width of [text] while skipping over ANSI escape sequences.
 */
fun displayWidth(text: String): Int {
    val chars = text.iterator()
    var width = 0
    while (chars.hasNext()) {
        val ch = chars.next()
        if (skipAnsiEscapeSequence(ch, chars)) {
            continue
        }
        width += chWidth(ch)
    }
    return width
}

/**
 * A (text) fragment denotes the unit which we wrap into lines.
 *
 * Fragments represent an abstract word plus the whitespace following the word.
 * In case the word falls at the end of the line, the whitespace is dropped and
 * a so-called penalty is inserted instead (typically `"-"` if the word was hyphenated).
 */
interface Fragment {
    /** Displayed width of word represented by this fragment. */
    fun width(): Double

    /**
     * Displayed width of the whitespace that must follow the word when the word is
     * not at the end of a line.
     */
    fun whitespaceWidth(): Double

    /**
     * Displayed width of the penalty that must be inserted if the word falls at the
     * end of a line.
     */
    fun penaltyWidth(): Double
}

/**
 * A piece of wrappable text, including any trailing whitespace.
 *
 * A [Word] is an example of a [Fragment], so it has a width, trailing whitespace,
 * and potentially a penalty item.
 */
class Word(
    val word: String,
    internal val width: Int = displayWidth(word),
    val whitespace: String = "",
    val penalty: String = "",
) : Fragment,
    CharSequence by word {
    override fun width(): Double = width.toDouble()

    override fun whitespaceWidth(): Double = whitespace.length.toDouble()

    override fun penaltyWidth(): Double = penalty.length.toDouble()

    /**
     * Break this word into smaller words with a width of at most [lineWidth].
     * The whitespace and penalty from this [Word] is added to the last piece.
     */
    fun breakApart(lineWidth: Int): Sequence<Word> =
        sequence {
            val charList = word.toList()
            var charIdx = 0
            var offset = 0
            var currentWidth = 0

            while (charIdx < charList.size) {
                val startCharIdx = charIdx
                val ch = charList[charIdx++]

                if (ch == CSI_FIRST) {
                    val subIterator = charList.subList(charIdx, charList.size).iterator()
                    if (skipAnsiEscapeSequence(ch, subIterator)) {
                        val consumed = (charList.size - charIdx) - subIterator.asSequence().count()
                        charIdx += consumed
                        continue
                    }
                }

                val cw = chWidth(ch)
                if (currentWidth > 0 && currentWidth + cw > lineWidth) {
                    val pieceWord = word.substring(offset, startCharIdx)
                    yield(
                        Word(
                            word = pieceWord,
                            width = currentWidth,
                            whitespace = "",
                            penalty = "",
                        ),
                    )
                    offset = startCharIdx
                    currentWidth = cw
                } else {
                    currentWidth += cw
                }
            }

            if (offset < word.length) {
                val pieceWord = word.substring(offset)
                yield(
                    Word(
                        word = pieceWord,
                        width = currentWidth,
                        whitespace = whitespace,
                        penalty = penalty,
                    ),
                )
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Word) return false
        return word == other.word &&
            width == other.width &&
            whitespace == other.whitespace &&
            penalty == other.penalty
    }

    override fun hashCode(): Int {
        var result = word.hashCode()
        result = 31 * result + width
        result = 31 * result + whitespace.hashCode()
        result = 31 * result + penalty.hashCode()
        return result
    }

    override fun toString(): String =
        "Word(word='$word', width=$width, whitespace='$whitespace', penalty='$penalty')"

    companion object {
        /**
         * Construct a [Word] from a string.
         *
         * A trailing stretch of `' '` is automatically taken to be the whitespace part
         * of the word.
         */
        fun from(word: String): Word {
            var whitespaceIdx = word.length
            while (whitespaceIdx > 0 && word[whitespaceIdx - 1] == ' ') {
                whitespaceIdx--
            }
            val trimmed = word.substring(0, whitespaceIdx)
            val whitespace = word.substring(whitespaceIdx)
            return Word(
                word = trimmed,
                width = displayWidth(trimmed),
                whitespace = whitespace,
                penalty = "",
            )
        }
    }
}

/**
 * Forcibly break words wider than [lineWidth] into smaller words.
 */
fun breakWords(
    words: Iterable<Word>,
    lineWidth: Int,
): List<Word> {
    val shortenedWords = mutableListOf<Word>()
    for (word in words) {
        if (word.width > lineWidth) {
            shortenedWords.addAll(word.breakApart(lineWidth))
        } else {
            shortenedWords.add(word)
        }
    }
    return shortenedWords
}
