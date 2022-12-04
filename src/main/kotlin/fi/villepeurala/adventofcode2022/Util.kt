package fi.villepeurala.adventofcode2022

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
}