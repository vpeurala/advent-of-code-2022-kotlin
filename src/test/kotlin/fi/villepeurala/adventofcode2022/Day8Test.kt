package fi.villepeurala.adventofcode2022

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day8Test {
    val example1 =
        """30373
25512
65332
33549
35390"""
    val treeGrid1 = TreeGrid(example1)

    @Test
    fun treeGridTests() {
        assertEquals(3, treeGrid1.get(Coordinates2D(0, 0))) // top left
        assertEquals(3, treeGrid1.get(Coordinates2D(4, 0))) // top right
        assertEquals(3, treeGrid1.get(Coordinates2D(0, 4))) // bottom left
        assertEquals(0, treeGrid1.get(Coordinates2D(4, 4))) // bottom right

        assertEquals(listOf(3, 2, 6, 3, 3), treeGrid1.column(0))
        assertEquals(listOf(7, 1, 3, 4, 9), treeGrid1.column(3))
        assertEquals(listOf(3, 3, 5, 4, 9), treeGrid1.row(3))
    }

    @Test
    fun allTreesOnTheBorderAreVisible() {
        (0..4).forEach { x ->
            (0..4).forEach { y ->
                if (x == 0 || y == 0 || x == treeGrid1.width - 1 || y == treeGrid1.height - 1) {
                    assertTrue(
                        treeGrid1.isVisible(Coordinates2D(x, y)),
                        "($x,$y) = ${treeGrid1.get(Coordinates2D(x, y))} was not visible."
                    )
                }
            }
        }
    }

    @Test
    fun onlySomeInnerTreesAreVisible() {
        assertTrue(treeGrid1.isVisible(Coordinates2D(1, 1)))
        assertTrue(treeGrid1.isVisible(Coordinates2D(2, 1)))
        assertFalse(treeGrid1.isVisible(Coordinates2D(3, 1)))
    }

    @Test
    fun linesInDirections() {
        val centralTree = Coordinates2D(2, 1)
        assertEquals(listOf(5, 3), treeGrid1.lineInDirection(centralTree, Up))
        assertEquals(listOf(5, 3, 5, 3), treeGrid1.lineInDirection(centralTree, Down))
        assertEquals(listOf(5, 1, 2), treeGrid1.lineInDirection(centralTree, Right))
        assertEquals(listOf(5, 5, 2), treeGrid1.lineInDirection(centralTree, Left))
    }

    @Test
    fun scenesInDirections() {
        val centralTree = Coordinates2D(2, 1)
        assertEquals(1, treeGrid1.sceneInDirection(centralTree, Up))
        assertEquals(1, treeGrid1.sceneInDirection(centralTree, Left))
        assertEquals(2, treeGrid1.sceneInDirection(centralTree, Right))
        assertEquals(2, treeGrid1.sceneInDirection(centralTree, Down))
    }
}