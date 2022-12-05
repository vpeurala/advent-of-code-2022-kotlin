package fi.villepeurala.adventofcode2022

import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.toOption

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
