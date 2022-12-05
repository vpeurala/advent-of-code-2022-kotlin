package fi.villepeurala.adventofcode2022

object Day4 : Day<Int> {
    override val number: Int = 4

    override fun part1(inputResourcePath: String): Int {
        val assignments: List<Pair<IntRange, IntRange>> =
            Util.inputLines(inputResourcePath).map { parsePairAssignment(it) }
        return assignments.count {
            oneRangeFullyContainsOtherRange(it)
        }
    }

    override fun part2(inputResourcePath: String): Int {
        val assignments: List<Pair<IntRange, IntRange>> =
            Util.inputLines(inputResourcePath).map { parsePairAssignment(it) }
        return assignments.count {
            oneRangePartiallyContainsOtherRange(it)
        }
    }

    private fun oneRangeFullyContainsOtherRange(ranges: Pair<IntRange, IntRange>): Boolean {
        val (r1, r2) = ranges
        return r1.containsFully(r2) || r2.containsFully(r1)
    }

    private fun oneRangePartiallyContainsOtherRange(ranges: Pair<IntRange, IntRange>): Boolean {
        val (r1, r2) = ranges
        return r1.containsAny(r2) || r2.containsAny(r1)
    }

    private fun parsePairAssignment(it: String): Pair<IntRange, IntRange> {
        val ranges = it.split(",").map {
            val splitByDash = it.split("-")
            IntRange(splitByDash.first().toInt(), splitByDash.last().toInt())
        }
        return Pair(ranges[0], ranges[1])
    }

    private fun IntRange.containsFully(other: IntRange) =
        this.first <= other.first && this.last >= other.last

    private fun IntRange.containsAny(other: IntRange) =
        this.intersect(other).any()
}


