package fi.villepeurala.adventofcode2022

import fi.villepeurala.adventofcode2022.Day6.findMarker
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun examples() {
        assertEquals(7, findMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(5, findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"))
        assertEquals(6, findMarker("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(10, findMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(11, findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }
}