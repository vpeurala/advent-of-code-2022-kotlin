package fi.villepeurala.adventofcode2022

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun priorities() {
        assertEquals(1, priority('a'))
        assertEquals(26, priority('z'))
        assertEquals(27, priority('A'))
        assertEquals(52, priority('Z'))
    }
}
