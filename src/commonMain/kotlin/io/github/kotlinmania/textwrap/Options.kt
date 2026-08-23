// port-lint: source options.rs
package io.github.kotlinmania.textwrap

/**
 * Holds configuration options for wrapping and filling text.
 */
data class Options(
    /** The width in columns at which the text will be wrapped. */
    var width: Int = 80,
    /** Line ending used for breaking lines. */
    var lineEnding: LineEnding = LineEnding.LF,
    /** Indentation used for the first line of output. */
    var initialIndent: String = "",
    /** Indentation used for subsequent lines of output. */
    var subsequentIndent: String = "",
    /** Allow long words to be broken if they cannot fit on a line. */
    var breakWords: Boolean = true,
    /** Wrapping algorithm to use. */
    var wrapAlgorithm: WrapAlgorithm = WrapAlgorithm.new(),
    /** The line breaking algorithm to use. */
    var wordSeparator: WordSeparator = WordSeparator.new(),
    /** The method for splitting words. */
    var wordSplitter: WordSplitter = WordSplitter.HyphenSplitter,
) {
    /** Change [lineEnding]. */
    fun lineEnding(lineEnding: LineEnding): Options = copy(lineEnding = lineEnding)

    /** Set [width] to the given value. */
    fun width(width: Int): Options = copy(width = width)

    /** Change [initialIndent]. */
    fun initialIndent(initialIndent: String): Options = copy(initialIndent = initialIndent)

    /** Change [subsequentIndent]. */
    fun subsequentIndent(subsequentIndent: String): Options = copy(subsequentIndent = subsequentIndent)

    /** Change [breakWords]. */
    fun breakWords(breakWords: Boolean): Options = copy(breakWords = breakWords)

    /** Change [wordSeparator]. */
    fun wordSeparator(wordSeparator: WordSeparator): Options = copy(wordSeparator = wordSeparator)

    /** Change [wrapAlgorithm]. */
    fun wrapAlgorithm(wrapAlgorithm: WrapAlgorithm): Options = copy(wrapAlgorithm = wrapAlgorithm)

    /** Change [wordSplitter]. */
    fun wordSplitter(wordSplitter: WordSplitter): Options = copy(wordSplitter = wordSplitter)

    companion object {
        /** Creates a new [Options] with the specified width. */
        fun new(width: Int): Options = Options(width = width)

        /** Creates a new [Options] with width set to the current terminal width. */
        fun withTermwidth(): Options = Options(width = termwidth())
    }
}
