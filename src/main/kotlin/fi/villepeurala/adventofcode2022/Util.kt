package fi.villepeurala.adventofcode2022

import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.toOption
import arrow.typeclasses.Monoid
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

object Util {
    fun inputLines(resourcePath: String): List<String> {
        val inputStream = javaClass.getResourceAsStream(resourcePath)
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")
        return inputStream.reader(Charsets.UTF_8).readLines()
    }

    fun <T> groupedByBlanks(inputLines: List<String>, transform: (String) -> T): List<List<T>> {
        val iterator = inputLines.iterator()
        val groups = mutableListOf<List<T>>()
        val currentGroup: MutableList<T> = mutableListOf()
        while (iterator.hasNext()) {
            val line = iterator.next()
            if (line.isBlank()) {
                groups.add(currentGroup.toList())
                currentGroup.clear()
            } else {
                currentGroup.add(transform(line))
            }
        }
        groups.add(currentGroup)
        return groups.toList()
    }

    fun <T> T?.orDie(): T =
        this.toOption().getOrElse { throw IllegalStateException("Function orDie() called on null.") }

    fun <T> Option<T>.orDie(): T =
        this.getOrElse { throw IllegalStateException("Function orDie() called on Option.None.") }
}

/*
data class Coordinates2D(val x: Int, val y: Int) {
    override fun toString(): String = "($x, $y)"

    fun move(move: Move2D): Coordinates2D =
        Coordinates2D(x + move.asVector2D().x, y + move.asVector2D().y)

    fun distance(other: Coordinates2D): Double =
        sqrt(
            ((other.x - this.x).toDouble().pow(2.0) + (other.y - this.y).toDouble().pow(2.0))
        )

    fun angle(other: Coordinates2D): AngleInDegrees = asVector2D().angle()

    fun asVector2D(): Vector2D = Vector2D(x, y)

    companion object {
        val ORIGIN = Coordinates2D(0, 0)
    }
}

sealed interface Direction2D {
    companion object {
        fun clockwise() = listOf(Up, Right, Down, Left)
    }
}

object Up : Direction2D
object Right : Direction2D
object Down : Direction2D
object Left : Direction2D

data class Vector2D(val x: Int, val y: Int) {
    fun angle(): AngleInDegrees = if (x == 0) {
        AngleInDegrees(0.0)
    } else {
        AngleInDegrees(atan2(y.toDouble(), x.toDouble()))
    }
}

data class Move2D(val direction: Direction2D, val length: Int) {
    fun asVector2D(): Vector2D = when (direction) {
        Up -> Vector2D(0, -length)
        Right -> Vector2D(length, 0)
        Down -> Vector2D(0, length)
        Left -> Vector2D(-length, 0)
    }
}

object Vector2DMonoid : Monoid<Vector2D> {
    override fun empty(): Vector2D = Vector2D(0, 0)

    override fun Vector2D.combine(b: Vector2D): Vector2D = Vector2D(this.x + b.x, this.y + b.y)
}

data class AngleInDegrees(val degrees: Double) {
    init {
        require(degrees in 0.0..360.0)
    }
}

 */