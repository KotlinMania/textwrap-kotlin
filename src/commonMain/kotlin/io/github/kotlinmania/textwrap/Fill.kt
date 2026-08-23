// port-lint: source fill.rs
package io.github.kotlinmania.textwrap

/**
 * Fill a line of text at a given width.
 */
fun fill(
    text: String,
    width: Int,
): String = fill(text, Options.new(width))

/**
 * Fill a line of text with the given [options].
 */
fun fill(
    text: String,
    options: Options,
): String {
    if (displayWidth(text) <= options.width && !text.contains('\n') && options.initialIndent.isEmpty()) {
        return text.trimEnd(' ')
    }
    return fillSlowPath(text, options)
}

internal fun fillSlowPath(
    text: String,
    options: Options,
): String {
    val result = StringBuilder(text.length)
    val lineEndingStr = options.lineEnding.asStr()
    val lines = wrap(text, options)
    for (i in lines.indices) {
        if (i > 0) {
            result.append(lineEndingStr)
        }
        result.append(lines[i])
    }
    return result.toString()
}

/**
 * Fill [text] in-place without reallocating where possible.
 */
fun fillInplace(
    text: StringBuilder,
    width: Int,
) {
    val textStr = text.toString()
    val indices = mutableListOf<Int>()

    var offset = 0
    for (line in textStr.split('\n')) {
        val words = WordSeparator.AsciiSpace.findWords(line).toList()
        val wrappedWords = wrapFirstFit(words, listOf(width.toDouble()))

        var lineOffset = offset
        for (group in wrappedWords.dropLast(1)) {
            val lineLen = group.sumOf { it.word.length + it.whitespace.length }
            lineOffset += lineLen
            indices.add(lineOffset - 1)
        }
        offset += line.length + 1
    }

    for (idx in indices) {
        if (idx in 0 until text.length) {
            text[idx] = '\n'
        }
    }
}
