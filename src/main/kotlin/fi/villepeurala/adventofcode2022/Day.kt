package fi.villepeurala.adventofcode2022

interface Day<T> {
    val number: Int

    fun part1(inputResourcePath: String = "/day$number.txt"): T

    fun part2(inputResourcePath: String = "/day$number.txt"): T
}
