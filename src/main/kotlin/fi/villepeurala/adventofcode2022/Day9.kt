package fi.villepeurala.adventofcode2022

object Day9 : Day<Int> {
    override val number = 9

    override fun part1(inputResourcePath: String): Int {
        val moves: List<Move2D> = Util.inputLines(inputResourcePath).map { parseMove(it) }
        val headPositions = moves.fold(listOf(Coordinates2D.Origin)) { acc, cur ->
            acc.plus(acc.last() + cur.toVector())
        }
        /*
        val tailPositions = headPositions.fold(listOf(Coordinates2D.ORIGIN)) { acc, headPosition ->
            val currentTailCoordinates = acc.last()
            acc.plus(currentTailCoordinates.tailMove(headPosition))
        }

         */
        println(headPositions)
        // println(tailPositions)
        return 13
    }

    override fun part2(inputResourcePath: String): Int {
        return 2
    }

    private fun parseMove(it: String): Move2D {
        val parts = it.split(" ")
        val direction: CardinalDirection2D = when (val dirLetter: String = parts[0]) {
            "U" -> North
            "R" -> East
            "D" -> South
            "L" -> West
            else -> throw IllegalArgumentException("Unparseable direction: $dirLetter")
        }
        val length = parts[1].toInt()
        return Move2D(direction, length)
    }
}

/*
private fun Coordinates2D.tailMove(headPosition: Coordinates2D): Coordinates2D {
    if (this.isOnSameRowOrColumn(headPosition) && this.distance(headPosition) == 2.0 && this.angle(headPosition)
            .toBasicDirection()
    ) {
        moveToward(headPosition)
    }
}
 */

data class Move2D(val direction: CardinalDirection2D, val length: Int) {
    fun toVector(): Vector2D =
        when (direction) {
            North -> Vector2D(0.0, -length.toDouble())
            East -> Vector2D(length.toDouble(), 0.0)
            South -> Vector2D(0.0, length.toDouble())
            West -> Vector2D(-length.toDouble(), 0.0)
        }
}

private fun Coordinates2D.isOnSameRowOrColumn(otherPosition: Coordinates2D): Boolean =
    this.x == otherPosition.x || this.y == otherPosition.y
