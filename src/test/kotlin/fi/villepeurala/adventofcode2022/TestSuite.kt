package fi.villepeurala.adventofcode2022

import kotlin.test.Test
import kotlin.test.assertEquals

class TestSuite {
    @Test
    fun day1Part1() {
        assertEquals(68292, Day1.part1())
    }

    @Test
    fun day1Part2() {
        assertEquals(203203, Day1.part2())
    }

    @Test
    fun day2Part1() {
        assertEquals(10994, Day2.part1())
    }

    @Test
    fun day2Part2() {
        assertEquals(12526, Day2.part2())
    }

    @Test
    fun day3Part1() {
        assertEquals(7817, Day3.part1())
    }

    @Test
    fun day3Part2() {
        assertEquals(2444, Day3.part2())
    }
}