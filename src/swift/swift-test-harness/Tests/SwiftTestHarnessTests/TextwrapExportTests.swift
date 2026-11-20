import Testing
import Textwrap

@Suite("Textwrap Swift Export Tests")
struct TextwrapExportTests {
    @Test("ANSI color escapes and non-BMP scalars report expected display width")
    func ansiAndUnicodeDisplayWidth() {
        #expect(displayWidth(text: "\u{1B}[31mred\u{1B}[0m") == 3)
        #expect(displayWidth(text: "") == 0)
        #expect(displayWidth(text: "\u{10400}") == 1)
        #expect(displayWidth(text: "A\u{E0100}") == 1)
    }

    @Test("wrap and fill apply Options, LineEnding, and sealed class strategies")
    func wrapAndFillWithOptions() {
        let filled = fill(text: "Memory safety without garbage collection.", width: 15)
        #expect(filled == "Memory safety\nwithout garbage\ncollection.")

        let options = Options.Companion.shared.new(width: 15)
            .initialIndent(initialIndent: "- ")
            .subsequentIndent(subsequentIndent: "  ")
            .lineEnding(lineEnding: LineEnding.CRLF)
            .wordSeparator(wordSeparator: WordSeparator.AsciiSpace.shared)
            .wrapAlgorithm(wrapAlgorithm: WrapAlgorithm.FirstFit.shared)
            .wordSplitter(wordSplitter: WordSplitter.NoHyphenation.shared)

        let lines = wrap(text: "Memory safety without garbage collection.", options: options)
        #expect(lines == ["- Memory safety", "  without", "  garbage", "  collection."])

        let crlf = fill(text: "Memory safety without garbage collection.", options: options)
        #expect(crlf == "- Memory safety\r\n  without\r\n  garbage\r\n  collection.")
    }

    @Test("indent, dedent, refill, unfill, and wrapColumns work end-to-end")
    func indentDedentRefillAndColumns() {
        let block = "    first\n      second\n"
        #expect(dedent(s: block) == "first\n  second\n")
        #expect(indent(s: "a\nb\n", prefix: ">> ") == ">> a\n>> b\n")

        let text = "> Memory\n> safety without garbage\n> collection.\n"
        #expect(refill(filledText: text, newWidth: 20) == "> Memory safety\n> without garbage\n> collection.\n")

        let unfilled = unfill(text: "* first line\n  second line")
        #expect(unfilled.first as? String == "first line second line")
        #expect((unfilled.second as? Options)?.initialIndent == "* ")
        #expect((unfilled.second as? Options)?.subsequentIndent == "  ")

        let cols = wrapColumns(
            text: "This is a short sample across columns.",
            columns: 2,
            totalWidth: 30,
            leftGap: "| ",
            middleGap: " | ",
            rightGap: " |"
        )
        #expect(cols == [
            "| This is     | across       |",
            "| a short     | columns.     |",
            "| sample      |              |",
        ])
    }
}
