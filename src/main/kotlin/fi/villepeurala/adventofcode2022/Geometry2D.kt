package fi.villepeurala.adventofcode2022

import fi.villepeurala.adventofcode2022.CardinalDirection2D.Companion.cardinalDirections
import fi.villepeurala.adventofcode2022.OrdinalDirection2D.Companion.ordinalDirections

sealed interface Direction2D {
    fun bearing(): Double

    companion object {
        fun compass8Wind(): List<Direction2D> =
            cardinalDirections().zip(ordinalDirections()).flatMap { listOf(it.first, it.second) }
    }
}

sealed interface CardinalDirection2D : Direction2D {
    companion object {
        fun cardinalDirections(): List<CardinalDirection2D> = listOf(North, East, South, West)
    }
}

object North : CardinalDirection2D {
    override fun bearing(): Double = 0.0
}

object East : CardinalDirection2D {
    override fun bearing(): Double = 90.0
}

object South : CardinalDirection2D {
    override fun bearing(): Double = 180.0
}

object West : CardinalDirection2D {
    override fun bearing(): Double = 270.0
}

sealed interface OrdinalDirection2D : Direction2D {
    companion object {
        fun ordinalDirections(): List<OrdinalDirection2D> = listOf(NorthEast, SouthEast, SouthWest, NorthWest)
    }
}

object NorthEast : OrdinalDirection2D {
    override fun bearing(): Double = 45.0
}

object SouthEast : OrdinalDirection2D {
    override fun bearing(): Double = 135.0
}

object SouthWest : OrdinalDirection2D {
    override fun bearing(): Double = 225.0
}

object NorthWest : OrdinalDirection2D {
    override fun bearing(): Double = 315.0
}

data class Bearing2D(val degrees: Double) : Direction2D {
    override fun bearing(): Double = degrees
}

data class Coordinates2D(val x: Double, val y: Double) {
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    fun toVector(): Vector2D = Vector2D(x, y)

    operator fun plus(other: Vector2D): Coordinates2D =
        Coordinates2D(this.x + other.x, this.y + other.y)

    companion object {
        val Origin = Coordinates2D(0.0, 0.0)
    }
}

data class Vector2D(val x: Double, val y: Double) {
    fun toCoordinates(): Coordinates2D = Coordinates2D(x, y)

    operator fun plus(other: Vector2D): Vector2D =
        Vector2D(this.x + other.x, this.y + other.y)

    companion object {
        val Empty = Vector2D(0.0, 0.0)
    }
}
