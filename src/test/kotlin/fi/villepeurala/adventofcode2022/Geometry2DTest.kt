package fi.villepeurala.adventofcode2022

import fi.villepeurala.adventofcode2022.Direction2D.Companion.compass8Wind
import kotlin.test.Test
import kotlin.test.assertEquals

class Geometry2DTest {
    @Test
    fun compass8WindHas8Directions() {
        assertEquals(8, compass8Wind().size)
        assertEquals(North, compass8Wind().first())
        assertEquals(NorthEast, compass8Wind()[1])
        assertEquals(NorthWest, compass8Wind().last())
    }
}