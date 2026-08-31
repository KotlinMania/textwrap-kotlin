// port-lint: source textwrap/src/refill.rs
package io.github.kotlinmania.textwrap

private val PREFIX_CHARS = charArrayOf(' ', '-', '+', '*', '>', '#', '/')

/**
 * Unpack a paragraph of already-wrapped text.
 *
 * This function attempts to recover the original text from a single
 * paragraph of wrapped text, such as what [fill] would produce.
 *
 * In addition, it recognizes a common prefix and a common line ending among the lines.
 * The prefix of the first line is returned in [Options.initialIndent] and the prefix
 * of other lines is returned in [Options.subsequentIndent].
 *
 * Example:
 * ```kotlin
 * val (text, options) = unfill("* This is an\n  example of\n  a list item.\n")
 * // text == "This is an example of a list item.\n"
 * // options.initialIndent == "* "
 * // options.subsequentIndent == "  "
 * ```
 */
fun unfill(text: String): Pair<String, Options> {
    val options = Options.new(0)
    val lines = rustLines(text)
    for (idx in lines.indices) {
        val line = lines[idx]
        options.width = maxOf(options.width, displayWidth(line))
        var startIdx = 0
        while (startIdx < line.length && line[startIdx] in PREFIX_CHARS) {
            startIdx++
        }
        val prefix = line.substring(0, startIdx)

        if (idx == 0) {
            options.initialIndent = prefix
        } else if (idx == 1) {
            options.subsequentIndent = prefix
        } else if (idx > 1) {
            var commonLen = 0
            while (commonLen < prefix.length &&
                commonLen < options.subsequentIndent.length &&
                prefix[commonLen] == options.subsequentIndent[commonLen]
            ) {
                commonLen++
            }
            if (commonLen < options.subsequentIndent.length) {
                options.subsequentIndent = prefix.substring(0, commonLen)
            }
        }
    }

    val unfilled = StringBuilder(text.length)
    var detectedLineEnding: LineEnding? = null

    val linesIter = NonEmptyLines(text)
    var idx = 0
    while (linesIter.hasNext()) {
        val (line, ending) = linesIter.next()
        if (idx == 0) {
            unfilled.append(line.substring(options.initialIndent.length))
        } else {
            unfilled.append(' ')
            unfilled.append(line.substring(options.subsequentIndent.length))
        }
        if (detectedLineEnding == null && ending != null) {
            detectedLineEnding = ending
        } else if (detectedLineEnding == LineEnding.CRLF && ending == LineEnding.LF) {
            detectedLineEnding = ending
        }
        idx++
    }

    if (detectedLineEnding != null) {
        if (text.endsWith(detectedLineEnding.asStr())) {
            unfilled.append(detectedLineEnding.asStr())
        }
    }

    options.lineEnding = detectedLineEnding ?: LineEnding.LF
    return unfilled.toString() to options
}

/**
 * Refill a paragraph of wrapped text with a new width.
 *
 * This function will first use [unfill] to remove newlines from the text.
 * Afterwards the text is filled again using [fill].
 *
 * Example:
 * ```kotlin
 * val text = "> Memory\n> safety without garbage\n> collection.\n"
 * val refilled = refill(text, 20)
 * // "> Memory safety\n> without garbage\n> collection.\n"
 * ```
 */
fun refill(
    filledText: String,
    newWidth: Int,
): String = refill(filledText, Options.new(newWidth))

/**
 * Refill a paragraph of wrapped text with [options].
 */
fun refill(
    filledText: String,
    options: Options,
): String {
    val newOptions = options.copy()
    val (text, detectedOptions) = unfill(filledText)
    val endingStr = detectedOptions.lineEnding.asStr()
    val stripped = if (text.endsWith(endingStr)) text.substring(0, text.length - endingStr.length) else null
    val newEndingStr = newOptions.lineEnding.asStr()

    newOptions.initialIndent = detectedOptions.initialIndent
    newOptions.subsequentIndent = detectedOptions.subsequentIndent
    val refilled = StringBuilder(fill(stripped ?: text, newOptions))

    if (stripped != null) {
        refilled.append(newEndingStr)
    }
    return refilled.toString()
}
