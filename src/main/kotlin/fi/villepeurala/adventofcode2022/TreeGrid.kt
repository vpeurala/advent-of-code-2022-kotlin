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
        return input.lines()[coords.y][coords.x].digitToInt()
    }

    fun positions(): List<Pair<Coordinates2D, Int>> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y ->
                val coords = Coordinates2D(x, y)
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
        row(coords.y).subList(coords.x + 1, this.width)

    private fun viewFromLeft(coords: Coordinates2D) = row(coords.y).subList(0, coords.x)

    private fun viewFromBottom(coords: Coordinates2D) =
        column(coords.x).subList(coords.y + 1, this.height)

    private fun viewFromTop(coords: Coordinates2D): List<Int> {
        return column(coords.x).subList(0, coords.y)
    }

    fun lineInDirection(centralTreeCoords: Coordinates2D, direction2D: Direction2D): List<Int> =
        when (direction2D) {
            Up -> column(centralTreeCoords.x).take(centralTreeCoords.y + 1).reversed()
            Right -> row(centralTreeCoords.y).drop(centralTreeCoords.x)
            Down -> column(centralTreeCoords.x).drop(centralTreeCoords.y)
            Left -> row(centralTreeCoords.y).take(centralTreeCoords.x + 1).reversed()
        }

    fun sceneInDirection(centralTreeCoords: Coordinates2D, direction2D: Direction2D): Int {
        val centralTreeHeight = get(centralTreeCoords)
        val lineWithoutCentralTree = lineInDirection(centralTreeCoords, direction2D).drop(1)
        val shorterTrees = lineWithoutCentralTree.takeWhile { it < centralTreeHeight }
        val otherTrees = lineWithoutCentralTree.dropWhile { it < centralTreeHeight }
        return if (otherTrees.isEmpty()) {
            shorterTrees.size
        } else {
            shorterTrees.size + 1
        }
    }

    private fun scenicScore(coords: Coordinates2D): Int =
        Direction2D.clockwise().map { sceneInDirection(coords, it) }.fold(1) { acc, cur ->
            acc * cur
        }

    fun countVisibles(): Int =
        positions().count { this.isVisible(it.first) }

    fun highestScenicScore(): Int = positions().maxBy { scenicScore(it.first) }.first.let { scenicScore(it) }
}
