// port-lint: source indentation.rs
package io.github.kotlinmania.textwrap

internal fun rustLines(s: String): List<String> {
    if (s.isEmpty()) return emptyList()
    val raw = s.split("\r\n", "\n")
    return if (s.endsWith("\r\n") || s.endsWith("\n")) {
        raw.dropLast(1)
    } else {
        raw
    }
}

/**
 * Indent each line by the given [prefix].
 *
 * When indenting, trailing whitespace is stripped from the prefix for empty or whitespace-only lines.
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
        } else {
            val raw = s.split('\n')
            if (s.endsWith('\n')) raw.dropLast(1) else raw
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
 */
fun dedent(s: String): String {
    val lines = rustLines(s)
    if (lines.isEmpty()) return ""

    var prefix = ""
    var firstNonEmptyIdx = -1
    for (i in lines.indices) {
        val line = lines[i]
        val firstNonWs = line.indexOfFirst { !it.isWhitespace() }
        if (firstNonWs != -1) {
            prefix = line.substring(0, firstNonWs)
            firstNonEmptyIdx = i
            break
        }
    }

    if (firstNonEmptyIdx != -1) {
        for (i in (firstNonEmptyIdx + 1) until lines.size) {
            val line = lines[i]
            val firstNonWs = line.indexOfFirst { !it.isWhitespace() }
            if (firstNonWs != -1) {
                var commonLen = 0
                while (commonLen < firstNonWs && commonLen < prefix.length && line[commonLen] == prefix[commonLen]) {
                    commonLen++
                }
                if (commonLen < prefix.length) {
                    prefix = prefix.substring(0, commonLen)
                }
            }
        }
    }

    val result = StringBuilder()
    for (line in lines) {
        if (line.startsWith(prefix) && line.any { !it.isWhitespace() }) {
            result.append(line.substring(prefix.length))
        }
        result.append('\n')
    }

    if (result.endsWith('\n') && !s.endsWith('\n')) {
        result.setLength(result.length - 1)
    }

    return result.toString()
}
