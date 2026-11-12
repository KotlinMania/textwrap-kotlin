import Testing
import Textwrap

@Suite("Textwrap indentation behavior")
struct TextwrapIndentationTests {
    @Test("Indent preserves blank lines without trailing prefix whitespace")
    func indentBlankLines() {
        #expect(indent(s: "first\n\nsecond\n", prefix: "> ") == "> first\n>\n> second\n")
    }

    @Test("Dedent removes only common leading whitespace")
    func dedentCommonWhitespace() {
        #expect(dedent(s: "  first\n    second") == "first\n  second")
    }
}
