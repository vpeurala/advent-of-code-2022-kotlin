package fi.villepeurala.adventofcode2022

import fi.villepeurala.adventofcode2022.Day6.findMarker
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun examples() {
        assertEquals(7, findMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4))
        assertEquals(5, findMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 4))
        assertEquals(6, findMarker("nppdvjthqldpwncqszvftbrmjlhg", 4))
        assertEquals(10, findMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4))
        assertEquals(11, findMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4))
    }
}