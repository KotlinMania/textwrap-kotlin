// port-lint: source line_ending.rs
package io.github.kotlinmania.textwrap

/**
 * Line-ending detection and conversion.
 *
 * Supported line endings. Like in the Kotlin standard library, two line
 * endings are supported: `\r\n` and `\n`.
 */
enum class LineEnding(private val ascii: String) {
    /**
     * Carriage return and line feed, a line-ending sequence historically used in
     * Windows. Corresponds to the sequence of ASCII control characters
     * `0x0D 0x0A` or `\r\n`.
     */
    CRLF("\r\n"),

    /**
     * Line feed, a line ending historically used in Unix. Corresponds to the
     * ASCII control character `0x0A` or `\n`.
     */
    LF("\n"),
    ;

    /** Turns this [LineEnding] value into its ASCII representation. */
    fun asStr(): String = ascii
}

/** A non-empty line and the line ending that terminated it, if any. */
internal typealias NonEmptyLine = Pair<String, LineEnding?>

/**
 * An iterator over the lines of a string, as [NonEmptyLine] values; it only
 * emits non-empty lines, meaning lines with some content before the terminating
 * `\r\n` or `\n`.
 *
 * This class is used internally by the library.
 */
internal class NonEmptyLines(text: String) : Iterator<NonEmptyLine> {
    private var remaining = text
    private var nextValue: NonEmptyLine? = null
    private var nextValueReady = false

    override fun hasNext(): Boolean {
        if (!nextValueReady) {
            nextValue = advance()
            nextValueReady = true
        }
        return nextValue != null
    }

    override fun next(): NonEmptyLine {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        val value = nextValue ?: throw NoSuchElementException()
        nextValue = null
        nextValueReady = false
        return value
    }

    private fun advance(): NonEmptyLine? {
        while (true) {
            val lf = remaining.indexOf('\n')
            if (lf < 0) {
                if (remaining.isEmpty()) {
                    return null
                }
                val line = remaining
                remaining = ""
                return line to null
            }

            if (lf == 0 || (lf == 1 && remaining[lf - 1] == '\r')) {
                remaining = remaining.substring(lf + 1)
                continue
            }

            val trimmed =
                if (remaining[lf - 1] == '\r') {
                    remaining.substring(0, lf - 1) to LineEnding.CRLF
                } else {
                    remaining.substring(0, lf) to LineEnding.LF
                }
            remaining = remaining.substring(lf + 1)
            return trimmed
        }
    }
}
