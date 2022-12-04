package fi.villepeurala.adventofcode2022

import fi.villepeurala.adventofcode2022.Util.groupedByBlanks
import fi.villepeurala.adventofcode2022.Util.inputLines

object Day1 {
    fun part1(): Int {
        val largestGroupBySum = elves().maxBy { it.sum() }
        return largestGroupBySum.sum()
    }

    fun part2(): Int {
        val top3Groups = elves().sortedBy { it.sum() }.takeLast(3)
        return top3Groups.sumOf { it.sum() }
    }

    private fun elves() = groupedByBlanks(inputLines("/day1.txt")) { it.toInt() }
}