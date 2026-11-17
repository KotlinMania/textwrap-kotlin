// port-lint: source indentation.rs
package io.github.kotlinmania.textwrap

/**
 * Indent each line by the given [prefix].
 *
 * Example:
 *
 * ```kotlin
 * indent("First line.\nSecond line.\n", "  ")
 * // "  First line.\n  Second line.\n"
 * ```
 *
 * When indenting, trailing whitespace is stripped from the prefix.
 * This means that empty lines remain empty afterwards:
 *
 * ```kotlin
 * indent("First line.\n\n\nSecond line.\n", "  ")
 * // "  First line.\n\n\n  Second line.\n"
 * ```
 *
 * Notice how `"\n\n\n"` remained as `"\n\n\n"`.
 *
 * This feature is useful when you want to indent text and have a
 * space between your prefix and the text. In this case, you do not
 * want a trailing space on empty lines:
 *
 * ```kotlin
 * indent("foo = 123\n\nprint(foo)\n", "# ")
 * // "# foo = 123\n#\n# print(foo)\n"
 * ```
 *
 * Notice how `"\n\n"` became `"\n#\n"` instead of `"\n# \n"` which
 * would have trailing whitespace.
 *
 * Leading and trailing whitespace coming from the text itself is
 * kept unchanged:
 *
 * ```kotlin
 * indent(" \t  Foo   ", "->")
 * // "-> \t  Foo   "
 * ```
 */
fun indent(
    s: String,
    prefix: String,
): String {
    val result = StringBuilder(2 * s.length)
    val trimmedPrefix = prefix.trimEnd()
    val lines =
        if (s.isEmpty()) {
            emptyList()
        } else if (s.endsWith('\n')) {
            s.substring(0, s.length - 1).split('\n')
        } else {
            s.split('\n')
        }

    for (idx in lines.indices) {
        val line = lines[idx]
        if (idx > 0) {
            result.append('\n')
        }
        if (line.trim().isEmpty()) {
            result.append(trimmedPrefix)
        } else {
            result.append(prefix)
        }
        result.append(line)
    }

    if (s.endsWith('\n')) {
        result.append('\n')
    }

    return result.toString()
}

/**
 * Removes common leading whitespace from each line.
 *
 * This function will look at each non-empty line and determine the
 * maximum amount of whitespace that can be removed from all lines:
 *
 * ```kotlin
 * dedent("""
 *     1st line
 *       2nd line
 *     3rd line
 * """)
 * // "\n1st line\n  2nd line\n3rd line\n"
 * ```
 */
fun dedent(s: String): String {
    if (s.isEmpty()) return ""
    val raw = s.split("\r\n", "\n")
    val lines = if (s.endsWith("\r\n") || s.endsWith("\n")) raw.dropLast(1) else raw

    var prefix = ""
    var lineIndex = 0

    // Search for a non-empty line to find a prefix.
    while (lineIndex < lines.size) {
        val line = lines[lineIndex++]
        var whitespaceIdx = line.length
        for (idx in line.indices) {
            if (!line[idx].isWhitespace()) {
                whitespaceIdx = idx
                break
            }
        }

        // Check if the line had anything but whitespace
        if (whitespaceIdx < line.length) {
            prefix = line.substring(0, whitespaceIdx)
            break
        }
    }

    // Continue looking through the remaining lines to possibly shorten the prefix.
    while (lineIndex < lines.size) {
        val line = lines[lineIndex++]
        var whitespaceIdx = line.length
        val limit = minOf(line.length, prefix.length)
        for (idx in 0 until limit) {
            if (line[idx] != prefix[idx]) {
                whitespaceIdx = idx
                break
            }
        }

        // Check if the line had anything but whitespace and if we have found a shorter prefix
        val hasNonWs = line.any { !it.isWhitespace() }
        if (hasNonWs && whitespaceIdx < prefix.length) {
            prefix = line.substring(0, whitespaceIdx)
        }
    }

    // Build the result
    val result = StringBuilder()
    for (line in lines) {
        if (line.startsWith(prefix) && line.any { !it.isWhitespace() }) {
            result.append(line.substring(prefix.length))
        }
        result.append('\n')
    }

    if (result.isNotEmpty() && result.last() == '\n' && !s.endsWith('\n')) {
        result.setLength(result.length - 1)
    }

    return result.toString()
}
