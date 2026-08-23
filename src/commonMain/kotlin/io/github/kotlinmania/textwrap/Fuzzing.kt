// port-lint: source fuzzing.rs
package io.github.kotlinmania.textwrap

/**
 * Exposed for fuzzing to check slow paths.
 */
fun fuzzFillSlowPath(
    text: String,
    options: Options,
): String = fillSlowPath(text, options)

/**
 * Exposed for fuzzing to check slow paths.
 */
fun fuzzWrapSingleLine(
    line: String,
    options: Options,
    lines: MutableList<String>,
) {
    wrapSingleLine(line, options, lines)
}

/**
 * Exposed for fuzzing to check slow paths.
 */
fun fuzzWrapSingleLineSlowPath(
    line: String,
    options: Options,
    lines: MutableList<String>,
) {
    wrapSingleLineSlowPath(line, options, lines)
}
