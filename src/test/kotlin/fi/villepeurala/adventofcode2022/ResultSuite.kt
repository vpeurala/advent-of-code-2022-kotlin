package fi.villepeurala.adventofcode2022

import kotlin.test.Test
import kotlin.test.assertEquals

class ResultSuite {
    @Test
    fun allDayResults() {
        Days.days().entries.sortedBy { it.key }.forEach { entry ->
            assertEquals(
                entry.value.getValue(1),
                entry.key.part1(),
                "Test for part 1 of day ${entry.key.number} failed. Expected ${entry.value.getValue(1)} but was ${entry.key.part1()}."
            )
            assertEquals(
                entry.value.getValue(2),
                entry.key.part2(),
                "Test for part 2 of day ${entry.key.number} failed. Expected ${entry.value.getValue(2)} but was ${entry.key.part2()}."
            )
        }
    }
}
