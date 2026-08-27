// port-lint: source options.rs
package io.github.kotlinmania.textwrap

/**
 * Holds configuration options for wrapping and filling text.
 *
 * Example:
 * ```kotlin
 * val options = Options.new(16)
 *     .initialIndent("    ")
 *     .subsequentIndent("  ")
 * val wrapped = wrap("This is a little example.", options)
 * ```
 */
data class Options(
    /**
     * The width in columns at which the text will be wrapped.
     */
    var width: Int = 80,
    /**
     * Line ending used for breaking lines.
     */
    var lineEnding: LineEnding = LineEnding.LF,
    /**
     * Indentation used for the first line of output. See the [initialIndent] method.
     */
    var initialIndent: String = "",
    /**
     * Indentation used for subsequent lines of output. See the [subsequentIndent] method.
     */
    var subsequentIndent: String = "",
    /**
     * Allow long words to be broken if they cannot fit on a line.
     * When set to `false`, some lines may be longer than [width]. See [breakWords].
     */
    var breakWords: Boolean = true,
    /**
     * Wrapping algorithm to use, see [WrapAlgorithm] for details.
     */
    var wrapAlgorithm: WrapAlgorithm = WrapAlgorithm.new(),
    /**
     * The line breaking algorithm to use, see [WordSeparator] for details.
     */
    var wordSeparator: WordSeparator = WordSeparator.new(),
    /**
     * The method for splitting words. This can be used to prohibit splitting words
     * on hyphens, or to implement language-aware machine hyphenation.
     */
    var wordSplitter: WordSplitter = WordSplitter.HyphenSplitter,
) {
    /**
     * Change [lineEnding]. This specifies which of the supported line endings
     * should be used to break the lines of the input text.
     *
     * Example:
     * ```kotlin
     * val options = Options.new(15).lineEnding(LineEnding.CRLF)
     * val result = refill("This is a little example.", options)
     * ```
     */
    fun lineEnding(lineEnding: LineEnding): Options = copy(lineEnding = lineEnding)

    /**
     * Set [width] in columns at which the text will be wrapped.
     */
    fun width(width: Int): Options = copy(width = width)

    /**
     * Change [initialIndent]. The initial indentation is used on the very first line of output.
     *
     * Classic paragraph indentation can be achieved by specifying an initial indentation
     * and wrapping each paragraph by itself:
     * ```kotlin
     * val options = Options.new(16).initialIndent("    ")
     * val lines = wrap("This is a little example.", options)
     * ```
     */
    fun initialIndent(initialIndent: String): Options = copy(initialIndent = initialIndent)

    /**
     * Change [subsequentIndent]. The subsequent indentation is used on lines following the first line.
     *
     * Combining such indentation with [initialIndent] allows you to produce hanging indentation:
     * ```kotlin
     * val options = Options.new(15)
     *     .initialIndent("* ")
     *     .subsequentIndent("  ")
     * val lines = wrap("This is a little example.", options)
     * ```
     */
    fun subsequentIndent(subsequentIndent: String): Options = copy(subsequentIndent = subsequentIndent)

    /**
     * Control if words longer than [width] can be broken into multiple pieces.
     *
     * If `breakWords` is `false`, words will be kept intact even if they exceed the line width:
     * ```kotlin
     * val options = Options.new(5).breakWords(false)
     * val lines = wrap("foobarbaz", options)
     * ```
     */
    fun breakWords(breakWords: Boolean): Options = copy(breakWords = breakWords)

    /**
     * Change [wordSeparator]. The word separator determines how input lines are split into words.
     */
    fun wordSeparator(wordSeparator: WordSeparator): Options = copy(wordSeparator = wordSeparator)

    /**
     * Change [wrapAlgorithm]. Controls whether first-fit or optimal-fit wrapping is used.
     */
    fun wrapAlgorithm(wrapAlgorithm: WrapAlgorithm): Options = copy(wrapAlgorithm = wrapAlgorithm)

    /**
     * Change [wordSplitter]. Controls hyphenation and word breaking.
     */
    fun wordSplitter(wordSplitter: WordSplitter): Options = copy(wordSplitter = wordSplitter)

    companion object {
        /**
         * Creates a new [Options] with the specified width.
         *
         * Default values:
         * - `lineEnding`: [LineEnding.LF]
         * - `initialIndent`: `""`
         * - `subsequentIndent`: `""`
         * - `breakWords`: `true`
         * - `wordSeparator`: [WordSeparator.UnicodeBreakProperties]
         * - `wrapAlgorithm`: [WrapAlgorithm.OptimalFit]
         * - `wordSplitter`: [WordSplitter.HyphenSplitter]
         */
        fun new(width: Int): Options = Options(width = width)

        /**
         * Creates a new [Options] from integer width.
         */
        fun from(width: Int): Options = Options(width = width)
    }
}
