package fi.villepeurala.adventofcode2022

interface Grid2D<T> {
    fun get(coords: Coordinates2D): T
    fun row(y: Int): List<T>
    fun column(x: Int): List<T>
}

class TreeGrid(private val input: String) : Grid2D<Int> {
    val width = input.lines().maxBy { it.length }.length
    val height = input.lines().size

    override fun get(coords: Coordinates2D): Int {
        return input.lines()[coords.y.toInt()][coords.x.toInt()].digitToInt()
    }

    fun positions(): List<Pair<Coordinates2D, Int>> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y ->
                val coords = Coordinates2D(x.toDouble(), y.toDouble())
                val value = get(coords)
                coords to value
            }
        }
    }

    override fun row(y: Int): List<Int> = input.lines()[y].map { it.digitToInt() }
    override fun column(x: Int): List<Int> = input.lines().map { it.drop(x).first() }.map { it.digitToInt() }

    fun isVisible(coords: Coordinates2D): Boolean {
        val viewFromTop: List<Int> = viewFromTop(coords)
        val viewFromBottom: List<Int> = viewFromBottom(coords)
        val viewFromLeft: List<Int> = viewFromLeft(coords)
        val viewFromRight: List<Int> = viewFromRight(coords)

        val maxHeight: Int = get(coords)

        return viewFromTop.all { it < maxHeight }.or(viewFromBottom.all { it < maxHeight })
            .or(viewFromLeft.all { it < maxHeight }).or(viewFromRight.all { it < maxHeight })
    }

    private fun viewFromRight(coords: Coordinates2D) =
        row(coords.y.toInt()).subList((coords.x + 1).toInt(), this.width)

    private fun viewFromLeft(coords: Coordinates2D) = row(coords.y.toInt()).subList(0, coords.x.toInt())

    private fun viewFromBottom(coords: Coordinates2D) =
        column(coords.x.toInt()).subList(coords.y.toInt() + 1, this.height)

    private fun viewFromTop(coords: Coordinates2D): List<Int> {
        return column(coords.x.toInt()).subList(0, coords.y.toInt())
    }

    fun lineInDirection(centralTreeCoords: Coordinates2D, direction: CardinalDirection2D): List<Int> =
        when (direction) {
            North -> column(centralTreeCoords.x.toInt()).take(centralTreeCoords.y.toInt() + 1).reversed()
            East -> row(centralTreeCoords.y.toInt()).drop(centralTreeCoords.x.toInt())
            South -> column(centralTreeCoords.x.toInt()).drop(centralTreeCoords.y.toInt())
            West -> row(centralTreeCoords.y.toInt()).take(centralTreeCoords.x.toInt() + 1).reversed()
        }

    fun sceneInDirection(centralTreeCoords: Coordinates2D, direction: CardinalDirection2D): Int {
        val centralTreeHeight = get(centralTreeCoords)
        val lineWithoutCentralTree = lineInDirection(centralTreeCoords, direction).drop(1)
        val shorterTrees = lineWithoutCentralTree.takeWhile { it < centralTreeHeight }
        val otherTrees = lineWithoutCentralTree.dropWhile { it < centralTreeHeight }
        return if (otherTrees.isEmpty()) {
            shorterTrees.size
        } else {
            shorterTrees.size + 1
        }
    }

    private fun scenicScore(coords: Coordinates2D): Int =
        CardinalDirection2D.cardinalDirections().map { sceneInDirection(coords, it) }.fold(1) { acc, cur ->
            acc * cur
        }

    fun countVisibles(): Int =
        positions().count { this.isVisible(it.first) }

    fun highestScenicScore(): Int = positions().maxBy { scenicScore(it.first) }.first.let { scenicScore(it) }
}
