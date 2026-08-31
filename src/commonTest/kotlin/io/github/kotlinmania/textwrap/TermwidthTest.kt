// port-lint: tests textwrap/src/termwidth.rs
package io.github.kotlinmania.textwrap

import kotlin.test.Test
import kotlin.test.assertEquals

class TermwidthTest {
    @Test
    fun testTermwidth() {
        assertEquals(80, termwidth())
        val options = Options.withTermwidth()
        assertEquals(80, options.width)
    }
}
