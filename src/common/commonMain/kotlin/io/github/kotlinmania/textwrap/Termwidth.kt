// port-lint: source termwidth.rs
package io.github.kotlinmania.textwrap

/**
 * Return the current terminal width.
 *
 * If the terminal width cannot be determined (typically because the
 * standard output is not connected to a terminal), a default width
 * of 80 characters will be used.
 *
 * Example:
 *
 * Create an [Options] for wrapping at the current terminal width
 * with a two column margin to the left and the right:
 *
 * ```kotlin
 * val width = termwidth() - 4 // Two columns on each side.
 * val options = Options.new(width)
 *     .initialIndent("  ")
 *     .subsequentIndent("  ")
 * ```
 */
fun termwidth(): Int = 80

/**
 * Creates a new [Options] with `width` set to the current
 * terminal width. If the terminal width cannot be determined
 * (typically because the standard input and output is not
 * connected to a terminal), a width of 80 characters will be
 * used. Other settings use the same defaults as
 * [Options.new].
 *
 * Equivalent to:
 *
 * ```kotlin
 * val options = Options.new(termwidth())
 * ```
 */
fun Options.Companion.withTermwidth(): Options = Options.new(termwidth())
