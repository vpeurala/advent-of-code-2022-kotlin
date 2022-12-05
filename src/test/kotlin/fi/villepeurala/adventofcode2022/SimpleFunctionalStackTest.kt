package fi.villepeurala.adventofcode2022

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import fi.villepeurala.adventofcode2022.SimpleFunctionalStack.Companion.emptyStack
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SimpleFunctionalStackTest {
    @Test
    fun isCreatedAsEmpty() {
        assertTrue { emptyStack<Int>().isEmpty() }
    }

    @Test
    fun canBePushedTo() {
        val s: SimpleFunctionalStack<Int> = emptyStack()
        val t: SimpleFunctionalStack<Int> = s.push(1)
        assertEquals(1, t.size)
    }

    @Test
    fun canBePoppedFrom() {
        val x = "foo"
        val s: SimpleFunctionalStack<String> = emptyStack()
        val t: SimpleFunctionalStack<String> = s.push(x)
        assertEquals(1, t.size)
        val (popped, u) = t.pop()
        assertEquals(Some(x), popped)
        assertTrue { u.isEmpty() }
        assertEquals(0, u.size)
        assertEquals(None, u.pop().first)
    }

    @Test
    fun canBePeeked() {
        val x = "foo"
        val s: SimpleFunctionalStack<String> = emptyStack()
        val t: SimpleFunctionalStack<String> = s.push(x)
        assertEquals(1, t.size)
        val peeked: Option<String> = t.peek()
        assertEquals(Some(x), peeked)
        assertEquals(1, t.size)
    }
}