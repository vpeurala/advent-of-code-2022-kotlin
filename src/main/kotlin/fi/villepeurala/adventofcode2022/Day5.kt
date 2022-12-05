package fi.villepeurala.adventofcode2022

import arrow.core.getOrElse
import arrow.core.toOption
import fi.villepeurala.adventofcode2022.Util.inputLines
import fi.villepeurala.adventofcode2022.Util.orDie

data class Move(val amount: Int, val from: Int, val to: Int) {
    override fun toString(): String {
        return "move $amount from $from to $to"
    }
}

sealed class GiantCargoCrane {
    abstract fun executeMoveOnCrateStacks(move: Move, stacks: CrateStacks): CrateStacks
}

object CrateMover9000 : GiantCargoCrane() {
    override fun executeMoveOnCrateStacks(move: Move, stacks: CrateStacks): CrateStacks {
        var fromStack = stacks[move.from]
        var toStack = stacks[move.to]

        val tempStacks: MutableList<CrateStack> = stacks.items.toMutableList()

        repeat(move.amount) {
            val (popped, newFromStack) = fromStack.pop()
            val newToStack = toStack.push(popped.orDie())
            tempStacks.replaceAll { stack ->
                if (fromStack.id == stack.id) {
                    CrateStack(fromStack.id, newFromStack)
                } else if (toStack.id == stack.id) {
                    CrateStack(toStack.id, newToStack)
                } else {
                    stack
                }
            }
            fromStack = CrateStack(fromStack.id, newFromStack)
            toStack = CrateStack(toStack.id, newToStack)
        }

        return CrateStacks(tempStacks.toList())
    }
}

object CrateMover9001 : GiantCargoCrane() {
    override fun executeMoveOnCrateStacks(move: Move, stacks: CrateStacks): CrateStacks {
        val fromStack = stacks[move.from]
        val toStack = stacks[move.to]
        val (craneful, newFromStack) = fromStack.extract(move.amount)
        val newToStack = toStack.load(craneful)
        return CrateStacks(
            stacks.map { stack ->
                when (stack.id) {
                    fromStack.id -> CrateStack(fromStack.id, newFromStack)
                    toStack.id -> CrateStack(toStack.id, newToStack)
                    else -> stack
                }
            }
        )
    }
}

object Day5 : Day<String> {
    override val number: Int = 5

    override fun part1(inputResourcePath: String): String {
        val stacksBeforeMoves = parseStacks(inputLines(inputResourcePath))
        val moves = parseMoves(inputLines(inputResourcePath))
        val stacksAfterMoves: CrateStacks = moves.fold(stacksBeforeMoves) { acc, cur ->
            CrateMover9000.executeMoveOnCrateStacks(cur, acc)
        }
        return stacksAfterMoves.topCrates()
    }

    override fun part2(inputResourcePath: String): String {
        val stacksBeforeMoves = parseStacks(inputLines(inputResourcePath))
        val moves = parseMoves(inputLines(inputResourcePath))
        val stacksAfterMoves: CrateStacks = moves.fold(stacksBeforeMoves) { acc, cur ->
            CrateMover9001.executeMoveOnCrateStacks(cur, acc)
        }
        return stacksAfterMoves.topCrates()
    }

    fun parseMoves(lines: List<String>): List<Move> {
        val movesSection = lines.dropWhile { it.isNotBlank() }.dropWhile { it.isBlank() }
        return movesSection.map { parseMove(it) }
    }

    private fun parseMove(input: String): Move {
        val moveRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
        val match: MatchResult = moveRegex.matchEntire(input).orDie()
        val moveAttributes = match.groupValues.drop(1).map { it.toInt() }
        return Move(
            moveAttributes[0],
            moveAttributes[1],
            moveAttributes[2]
        )
    }

    fun parseStacks(lines: List<String>): CrateStacks =
        CrateStacks(parseColumns(emptyList(), lines.takeWhile { it.isNotBlank() }.reversed()))

    private tailrec fun parseColumns(found: List<CrateStack>, remaining: List<String>): List<CrateStack> {
        return if (remaining.isEmpty() || remaining.all { it.isBlank() }) {
            found
        } else {
            val (col: CrateStack, rest: List<String>) = parseColumn(remaining)
            val found1: List<CrateStack> = found.plus<CrateStack>(col)
            parseColumns(found1, rest)
        }
    }

    /**
     * Returns Pair(column, rest of lines).
     */
    private fun parseColumn(rev: List<String>): Pair<CrateStack, List<String>> =
        Pair(
            toCrateStack(rev.map { it.take(4) }.map { it.trim() }.map { it.removeSurrounding("[", "]") }),
            rev.map { it.drop(4) }
        )

    private fun toCrateStack(column: List<String>): CrateStack {
        val id = column.first()
        val crates = column.drop(1)
        val stack = SimpleFunctionalStack.emptyStack<Char>()
            .load(crates.filter { it.isNotBlank() }.map { it.first() })
        return CrateStack(id.toInt(), stack)
    }
}

data class CrateStack(val id: Int, val crates: FunctionalStack<Char>) : FunctionalStack<Char> by crates

data class CrateStacks(val items: List<CrateStack>) : List<CrateStack> by items {
    fun topCrates(): String = items.map { it.crates.peek().getOrElse { " " } }.joinToString("")

    override operator fun get(id: Int): CrateStack =
        items.find { it.id == id }.toOption().getOrElse { throw NoSuchElementException("Stack with id $id not found.") }
}
