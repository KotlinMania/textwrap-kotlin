// port-lint: source fuzzing.rs
package io.github.kotlinmania.textwrap

/**
 * Exposed for fuzzing so we can check the slow path is correct.
 */
fun fuzzFillSlowPath(
    text: String,
    options: Options,
): String = fillSlowPath(text, options)

/**
 * Exposed for fuzzing so we can check the slow path is correct.
 */
fun fuzzWrapSingleLine(
    line: String,
    options: Options,
    lines: MutableList<String>,
) {
    wrapSingleLine(line, options, lines)
}

/**
 * Exposed for fuzzing so we can check the slow path is correct.
 */
fun fuzzWrapSingleLineSlowPath(
    line: String,
    options: Options,
    lines: MutableList<String>,
) {
    wrapSingleLineSlowPath(line, options, lines)
}

/**
 * Fuzzing helpers matching upstream fuzzing module.
 */
object Fuzzing {
    /** Exposed for fuzzing so we can check the slow path is correct. */
    fun fillSlowPath(text: String, options: Options): String = io.github.kotlinmania.textwrap.fillSlowPath(text, options)

    /** Exposed for fuzzing so we can check the slow path is correct. */
    fun wrapSingleLine(line: String, options: Options, lines: MutableList<String>) {
        io.github.kotlinmania.textwrap.wrapSingleLine(line, options, lines)
    }

    /** Exposed for fuzzing so we can check the slow path is correct. */
    fun wrapSingleLineSlowPath(line: String, options: Options, lines: MutableList<String>) {
        io.github.kotlinmania.textwrap.wrapSingleLineSlowPath(line, options, lines)
    }
}

