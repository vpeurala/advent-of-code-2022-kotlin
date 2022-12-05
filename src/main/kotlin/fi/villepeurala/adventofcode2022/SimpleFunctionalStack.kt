package fi.villepeurala.adventofcode2022

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

interface FunctionalStack<T> : Collection<T> {
    fun peek(): Option<T>

    fun pop(): Pair<Option<T>, FunctionalStack<T>>

    fun push(item: T): FunctionalStack<T>

    fun extract(size: Int): Pair<List<T>, FunctionalStack<T>>

    fun load(xs: List<T>): FunctionalStack<T>
}

class SimpleFunctionalStack<T> private constructor(override val size: Int, private val items: List<T>) :
    FunctionalStack<T> {
    companion object {
        fun <I> emptyStack(): SimpleFunctionalStack<I> {
            return SimpleFunctionalStack(0, emptyList())
        }
    }

    override fun contains(element: T): Boolean = items.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = items.containsAll(elements)

    override fun isEmpty(): Boolean = items.isEmpty()

    override fun iterator(): Iterator<T> = items.iterator()

    override fun push(item: T): SimpleFunctionalStack<T> = SimpleFunctionalStack(size + 1, items.plus(item))

    override fun pop(): Pair<Option<T>, SimpleFunctionalStack<T>> = when (size) {
        0 -> None to this
        else -> Some(items.last()) to SimpleFunctionalStack(size - 1, items.dropLast(1))
    }

    override fun peek(): Option<T> = when (size) {
        0 -> None
        else -> Some(items.last())
    }

    override fun extract(n: Int): Pair<List<T>, FunctionalStack<T>> =
        items.takeLast(n) to SimpleFunctionalStack(this.size - n, items.dropLast(n))

    override fun load(xs: List<T>): FunctionalStack<T> = SimpleFunctionalStack(this.size + xs.size, items.plus(xs))

    override fun equals(other: Any?): Boolean {
        return other is SimpleFunctionalStack<*> && other.size == this.size && other.items == this.items
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }

    override fun toString(): String {
        return items.toString()
    }
}
