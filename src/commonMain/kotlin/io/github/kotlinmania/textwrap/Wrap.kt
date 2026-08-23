// port-lint: source wrap.rs
package io.github.kotlinmania.textwrap

/**
 * Wrap a line of text at a given width.
 */
fun wrap(
    text: String,
    width: Int,
): List<String> = wrap(text, Options.new(width))

/**
 * Wrap a line of text with the given [options].
 */
fun wrap(
    text: String,
    options: Options,
): List<String> {
    val lineEndingStr = options.lineEnding.asStr()
    val lines = mutableListOf<String>()
    for (line in text.split(lineEndingStr)) {
        wrapSingleLine(line, options, lines)
    }
    return lines
}

internal fun wrapSingleLine(
    line: String,
    options: Options,
    lines: MutableList<String>,
) {
    val indent = if (lines.isEmpty()) options.initialIndent else options.subsequentIndent
    if (displayWidth(line) <= options.width && indent.isEmpty()) {
        lines.add(line.trimEnd(' '))
    } else {
        wrapSingleLineSlowPath(line, options, lines)
    }
}

internal fun wrapSingleLineSlowPath(
    line: String,
    options: Options,
    lines: MutableList<String>,
) {
    val initialWidth = (options.width - displayWidth(options.initialIndent)).coerceAtLeast(0)
    val subsequentWidth = (options.width - displayWidth(options.subsequentIndent)).coerceAtLeast(0)
    val lineWidths = listOf(initialWidth, subsequentWidth)

    val words = options.wordSeparator.findWords(line).toList()
    val split = splitWords(words, options.wordSplitter).toList()
    val brokenWords =
        if (options.breakWords) {
            val broken = breakWords(split, lineWidths[1]).toMutableList()
            if (options.initialIndent.isNotEmpty()) {
                broken.add(0, Word.from(""))
            }
            broken
        } else {
            split
        }

    val wrappedWords = options.wrapAlgorithm.wrap(brokenWords, lineWidths)

    var idx = 0
    for (group in wrappedWords) {
        val lastWord = group.lastOrNull()
        if (lastWord == null) {
            lines.add("")
            continue
        }

        val len = group.sumOf { it.word.length + it.whitespace.length } - lastWord.whitespace.length

        val result = StringBuilder()
        if (lines.isEmpty() && options.initialIndent.isNotEmpty()) {
            result.append(options.initialIndent)
        } else if (lines.isNotEmpty() && options.subsequentIndent.isNotEmpty()) {
            result.append(options.subsequentIndent)
        }

        result.append(line.substring(idx, idx + len))

        if (lastWord.penalty.isNotEmpty()) {
            result.append(lastWord.penalty)
        }

        lines.add(result.toString())

        idx += len + lastWord.whitespace.length
    }
}
