// port-lint: source refill.rs
package io.github.kotlinmania.textwrap

private val PREFIX_CHARS = charArrayOf(' ', '-', '+', '*', '>', '#', '/')

/**
 * Unpack a paragraph of already-wrapped text.
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
