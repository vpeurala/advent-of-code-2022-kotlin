package fi.villepeurala.adventofcode2022

object Day8 : Day<Int> {
    override val number: Int = 8

    override fun part1(inputResourcePath: String): Int {
        val block = Util.inputLines(inputResourcePath).joinToString(separator = "\n")
        val treeGrid = TreeGrid(block)
        return treeGrid.countVisibles()
    }

    override fun part2(inputResourcePath: String): Int {
        val block = Util.inputLines(inputResourcePath).joinToString(separator = "\n")
        val treeGrid = TreeGrid(block)
        return treeGrid.highestScenicScore()
    }
}