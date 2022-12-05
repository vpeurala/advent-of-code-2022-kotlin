package fi.villepeurala.adventofcode2022

import arrow.core.some
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun testParseColumns() {
        val original: String = """
                [B] [L]     [J]
            [B] [Q] [R]     [D] [T]
            [G] [H] [H] [M] [N] [F]
        [J] [N] [D] [F] [J] [H] [B]
    [Q] [F] [W] [S] [V] [N] [F] [N]
[W] [N] [H] [M] [L] [B] [R] [T] [Q]
[L] [T] [C] [R] [R] [J] [W] [Z] [L]
[S] [J] [S] [T] [T] [M] [D] [B] [H]
 1   2   3   4   5   6   7   8   9
        """.trimIndent()
        val stacks = Day5.parseStacks(original.lines())
        assertEquals(9, stacks.size())

        val stack1 = stacks[1]
        assertEquals(1, stack1.id)
        assertEquals(3, stack1.crates.size)

        val stack6 = stacks[6]
        assertEquals(6, stack6.id)
        assertEquals(8, stack6.crates.size)

        assertEquals('W'.some(), stack1.crates.pop().first)
        assertEquals('L'.some(), stack6.crates.pop().first)
    }

    @Test
    fun withExampleInput() {
        val example = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
        """.trimIndent()
        val original = Day5.parseStacks(example.lines())
        assertEquals("NDP", original.topCrates())
        val moves = Day5.parseMoves(example.lines())
        val after = moves.fold(original) { acc, cur ->
            acc.executeMove(cur)
        }
        assertEquals("CMZ", after.topCrates())
    }
}