// port-lint: source lib.rs
package io.github.kotlinmania.textwrap

/**
 * Version of the upstream textwrap library ported here.
 */
public const val TEXTWRAP_VERSION: String = "0.16.2"

/**
 * The textwrap library provides functions for word wrapping and indenting text.
 *
 * ## Wrapping Text
 *
 * Wrapping text can be very useful in command-line programs where you want to format
 * dynamic output nicely so it looks good in a terminal.
 *
 * ```kotlin
 * val text = "textwrap: a small library for wrapping text."
 * assertEquals(
 *     listOf("textwrap: a", "small library for", "wrapping text."),
 *     wrap(text, 18),
 * )
 * ```
 *
 * The [wrap] function returns the individual lines; use [fill] if you want the lines
 * joined with `\n` to form a [String].
 *
 * See also the [unfill] and [refill] functions which allow you to manipulate already
 * wrapped text.
 *
 * ## Displayed Width vs Byte Size
 *
 * To word wrap text, one must know the width of each word so one can know when to break
 * lines. This library measures the width of text using the *displayed width*, taking into
 * account multi-column Unicode characters and ANSI escape sequences.
 *
 * ## Indentation and Dedentation
 *
 * The textwrap library also offers functions for adding a prefix to every line of a string
 * and to remove leading whitespace. As an example, [indent] allows you to turn lines of
 * text into a bullet list:
 *
 * ```kotlin
 * val before = "foo\nbar\nbaz\n"
 * val after = "* foo\n* bar\n* baz\n"
 * assertEquals(after, indent(before, "* "))
 * ```
 *
 * Removing leading whitespace is done with [dedent]:
 *
 * ```kotlin
 * val before = "    Some\n      indented\n        text\n"
 * val after = "Some\n  indented\n    text\n"
 * assertEquals(after, dedent(before))
 * ```
 */
