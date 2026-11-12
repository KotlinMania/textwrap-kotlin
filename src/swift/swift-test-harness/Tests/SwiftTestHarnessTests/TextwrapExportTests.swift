import Testing
import Textwrap

@Suite("Textwrap display width behavior")
struct TextwrapExportTests {
    @Test("ANSI color escapes do not contribute to display width")
    func ansiDisplayWidth() {
        #expect(displayWidth(text: "\u{1B}[31mred\u{1B}[0m") == 3)
        #expect(displayWidth(text: "") == 0)
    }
}
