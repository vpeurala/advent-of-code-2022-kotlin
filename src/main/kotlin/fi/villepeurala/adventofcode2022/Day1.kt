package fi.villepeurala.adventofcode2022

import fi.villepeurala.adventofcode2022.Util.groupedByBlanks
import fi.villepeurala.adventofcode2022.Util.inputLines

object Day1 : Day<Int> {
    override val number: Int = 1

    override fun part1(inputResourcePath: String): Int {
        val largestGroupBySum = elves(inputResourcePath).maxBy { it.sum() }
        return largestGroupBySum.sum()
    }

    override fun part2(inputResourcePath: String): Int {
        val top3Groups = elves(inputResourcePath).sortedBy { it.sum() }.takeLast(3)
        return top3Groups.sumOf { it.sum() }
    }

    private fun elves(inputResourcePath: String) = groupedByBlanks(inputLines("/day1.txt")) { it.toInt() }
}