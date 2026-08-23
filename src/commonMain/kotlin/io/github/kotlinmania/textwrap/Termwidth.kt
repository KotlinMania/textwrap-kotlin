// port-lint: source termwidth.rs
package io.github.kotlinmania.textwrap

/**
 * Return the current terminal width, or 80 if unavailable.
 */
fun termwidth(): Int = 80

/**
 * Creates a new [Options] with width set to the current terminal width.
 */
fun Options.Companion.withTermwidth(): Options = Options.new(termwidth())
