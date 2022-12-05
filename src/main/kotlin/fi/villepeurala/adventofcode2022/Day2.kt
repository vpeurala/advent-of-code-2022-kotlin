package fi.villepeurala.adventofcode2022

import fi.villepeurala.adventofcode2022.Util.inputLines

object Day2 : Day<Int> {
    override val number: Int = 2

    override fun part1(inputResourcePath: String): Int {
        val lines = inputLines(inputResourcePath)
        val rounds = lines.map { parseRoundDay1(it) }
        return rounds.sumOf { it.score() }

    }

    override fun part2(inputResourcePath: String): Int {
        val lines = inputLines(inputResourcePath)
        val rounds = lines.map { parseRoundDay2(it) }
        return rounds.sumOf { it.score() }
    }

    private fun parseRoundDay1(it: String) = Round(
        RPS.fromABC(it.take(1)), RPS.fromXYZ(it.takeLast(1))
    )

    private fun parseRoundDay2(it: String) = roundFromExpectedResult(
        RPS.fromABC(it.take(1)), GameResult.fromXYZ(it.takeLast(1))
    )

    private fun roundFromExpectedResult(opponent: RPS, expectedResult: GameResult): Round =
        Round(opponent = opponent, my = rpsForExpectedResultAgainstOpponent(expectedResult, opponent))

    private fun rpsForExpectedResultAgainstOpponent(expectedResult: GameResult, opponent: RPS): RPS =
        when (expectedResult) {
            Loss -> {
                when (opponent) {
                    Rock -> Scissors
                    Paper -> Rock
                    Scissors -> Paper
                }
            }

            Draw -> {
                opponent
            }

            Win -> {
                when (opponent) {
                    Rock -> Paper
                    Paper -> Scissors
                    Scissors -> Rock
                }
            }
        }

    private fun RPS.Companion.fromABC(input: String): RPS = when (input) {
        "A" -> Rock
        "B" -> Paper
        "C" -> Scissors
        else -> throw IllegalArgumentException("Not a legal value for fromABC: $input")
    }

    private fun RPS.Companion.fromXYZ(input: String): RPS = when (input) {
        "X" -> Rock
        "Y" -> Paper
        "Z" -> Scissors
        else -> throw IllegalArgumentException("Not a legal value for RPS.fromXYZ: $input")
    }

    private fun GameResult.Companion.fromXYZ(input: String): GameResult = when (input) {
        "X" -> Loss
        "Y" -> Draw
        "Z" -> Win
        else -> throw IllegalArgumentException("Not a legal value for GameResult.fromXYZ: $input")
    }
}

data class Round(val opponent: RPS, val my: RPS) {
    fun result(): GameResult = my.resultAgainst(opponent)

    fun score(): Int = my.points() + result().points()

    private fun RPS.points(): Int = when (this) {
        Rock -> 1
        Paper -> 2
        Scissors -> 3
    }

    private fun GameResult.points(): Int = when (this) {
        Loss -> 0
        Draw -> 3
        Win -> 6
    }
}

sealed interface GameResult {
    companion object
}

object Win : GameResult
object Draw : GameResult
object Loss : GameResult

sealed interface RPS {
    fun resultAgainst(other: RPS): GameResult

    companion object
}

object Rock : RPS {
    override fun resultAgainst(other: RPS) = when (other) {
        Rock -> Draw
        Paper -> Loss
        Scissors -> Win
    }
}

object Paper : RPS {
    override fun resultAgainst(other: RPS) = when (other) {
        Rock -> Win
        Paper -> Draw
        Scissors -> Loss
    }
}

object Scissors : RPS {
    override fun resultAgainst(other: RPS) = when (other) {
        Rock -> Loss
        Paper -> Win
        Scissors -> Draw
    }
}