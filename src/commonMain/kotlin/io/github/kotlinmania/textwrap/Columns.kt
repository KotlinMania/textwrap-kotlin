// port-lint: source columns.rs
package io.github.kotlinmania.textwrap

/**
 * Wrap text into columns with a given total width.
 */
fun wrapColumns(
    text: String,
    columns: Int,
    totalWidth: Int,
    leftGap: String = "",
    middleGap: String = "",
    rightGap: String = "",
): List<String> = wrapColumns(text, columns, Options.new(totalWidth), leftGap, middleGap, rightGap)

/**
 * Wrap text into columns with given [totalWidthOrOptions].
 */
fun wrapColumns(
    text: String,
    columns: Int,
    totalWidthOrOptions: Options,
    leftGap: String = "",
    middleGap: String = "",
    rightGap: String = "",
): List<String> {
    require(columns > 0) { "columns must be greater than zero" }

    val options = totalWidthOrOptions.copy()

    val innerWidth =
        (
            options.width -
                displayWidth(leftGap) -
                displayWidth(rightGap) -
                displayWidth(middleGap) * (columns - 1)
        ).coerceAtLeast(0)

    val columnWidth = maxOf(innerWidth / columns, 1)
    options.width = columnWidth
    val lastColumnPadding = " ".repeat(innerWidth % columnWidth)
    val wrappedLines = wrap(text, options)
    val linesPerColumn = wrappedLines.size / columns + if (wrappedLines.size % columns > 0) 1 else 0
    val lines = mutableListOf<String>()

    for (lineNo in 0 until linesPerColumn) {
        val line = StringBuilder(leftGap)
        for (columnNo in 0 until columns) {
            val idx = lineNo + columnNo * linesPerColumn
            if (idx < wrappedLines.size) {
                val columnLine = wrappedLines[idx]
                line.append(columnLine)
                val padding = (columnWidth - displayWidth(columnLine)).coerceAtLeast(0)
                line.append(" ".repeat(padding))
            } else {
                line.append(" ".repeat(columnWidth))
            }
            if (columnNo == columns - 1) {
                line.append(lastColumnPadding)
            } else {
                line.append(middleGap)
            }
        }
        line.append(rightGap)
        lines.add(line.toString())
    }

    return lines
}
