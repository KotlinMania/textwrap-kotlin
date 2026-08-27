// port-lint: source termwidth.rs
package io.github.kotlinmania.textwrap

/**
 * Return the current terminal width.
 *
 * If the terminal width cannot be determined, a default width of 80 characters is returned.
 *
 * Example:
 * ```kotlin
 * val width = termwidth() - 4 // Two columns margin on each side
 * val options = Options.new(width)
 *     .initialIndent("  ")
 *     .subsequentIndent("  ")
 * ```
 */
fun termwidth(): Int = 80

/**
 * Creates a new [Options] with `width` set to the current terminal width.
 *
 * Equivalent to `Options.new(termwidth())`.
 */
fun Options.Companion.withTermwidth(): Options = Options.new(termwidth())
