package fi.villepeurala.adventofcode2022

object Day3 {
    fun part1(): Int {
        val lines = Util.inputLines("/day3.txt")
        val rucksacks = lines.map { Rucksack(it) }
        return rucksacks.sumOf { priority(it.itemWhichExistsInBothCompartments) }
    }

    fun part2(): Int {
        val lines = Util.inputLines("/day3.txt")
        val elfGroups = lines.windowed(3, 3)
        return elfGroups.sumOf {
            priority(commonLetter(it))
        }
    }

    private fun commonLetter(it: List<String>): Char =
        it.fold(it.joinToString(separator = "").toSet()) { acc: Set<Char>, cur: String ->
            acc.intersect(cur.toSet())
        }.first()
}

fun halveString(input: String): Pair<String, String> {
    val len = input.length
    if (len % 2 != 0) {
        throw IllegalArgumentException("Input string cannot be halved: \"$input\" (length $len is an odd number).")
    } else {
        return Pair(input.take(len / 2), input.takeLast(len / 2))
    }
}

fun priority(input: Char): Int {
    return if (!input.isLetter()) {
        throw IllegalArgumentException("Character '${input}' cannot be prioritized. It must be a letter.")
    } else {
        if (input.isLowerCase()) {
            input.code - 96
        } else {
            input.code - 38
        }
    }
}

data class Rucksack(val input: String) {
    val compartments: Pair<String, String> = halveString(input)
    val itemsInCompartment1: Set<Char> = compartments.first.toSet()
    val itemsInCompartment2: Set<Char> = compartments.second.toSet()
    val itemWhichExistsInBothCompartments: Char = itemsInCompartment1.intersect(itemsInCompartment2).first()
}