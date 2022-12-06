package fi.villepeurala.adventofcode2022

object Day6 : Day<Int> {
    override val number: Int = 6

    override fun part1(inputResourcePath: String): Int {
        val signal = Util.inputLines(inputResourcePath).first()
        return findMarker(signal)
    }

    // TODO: Implement
    override fun part2(inputResourcePath: String): Int = 456

    fun findMarker(input: String): Int {
        val windowSize = 4
        val marker = input.windowed(windowSize, 1).withIndex().find {
            // If the window size stays the same after being converted to a set,
            // it means that all characters in the window are different.
            it.value.toSet().size == windowSize
        }
        if (marker == null) {
            throw IllegalArgumentException("No marker found in input! Input was: \"$input\".")
        } else {
            return marker.index + windowSize
        }
    }
}
