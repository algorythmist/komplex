package com.tecacet.komplex

import com.tecacet.komplex.Complex.Companion.ONE
import com.tecacet.komplex.Complex.Companion.ZERO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.PI

internal class ComplexTest {

    @Test
    fun testEquals() {
        val c1 = Complex(2.0, -3.1)
        var c2 = Complex(2.0, -3.1)
        assertEquals(c1, c2)
        assertTrue(c1 == c2)

        c2 = i * PI
        assertEquals(c2, Complex(0.0, PI))

        c2 = 4 + 5 * i
        assertEquals(Complex(4.0, 5.0), c2)

        c2 = 3 - 2 * i
        assertEquals(Complex(3.0, -2.0), c2)

        assertFalse(c1.equals(null))
    }

    @Test
    fun testDestructuring() {
        val (x, y) = Complex(1, 2)
        assertEquals(1.0, x)
        assertEquals(2.0, y)
    }

    @Test
    fun testConjugate() {
        val c = Complex(1, 2)
        assertEquals(Complex(1, -2), c.conjugate())
    }

    @Test
    fun testToString() {
        var c1 = Complex(2.0, -3.1)
        assertEquals("2.0-3.1i", c1.toString())

        c1 = Complex(2.0, 0.0)
        assertEquals("2.0", c1.toString())

        c1 = Complex(0.0, -3.1)
        assertEquals("-3.1i", c1.toString())

        c1 = Complex(0.0, 2.5)
        assertEquals("2.5i", c1.toString())

        c1 = Complex(0.0, 0.0)
        assertEquals("0.0", c1.toString())
    }

    @Test
    fun testAdditionSubtraction() {
        val c1 = Complex(2.0, -3.1)
        assertEquals("2.0-3.1i", c1.toString())
        val c2 = -c1
        assertEquals("-2.0+3.1i", c2.toString())

        val c3 = Complex(-5.0, 2.0)
        var c4 = c1 + c3
        assertEquals(-3.0, c4.real)
        assertEquals(-1.1, c4.img)

        c4 = c1 * c3
        assertEquals(-3.8, c4.real)
        assertEquals(19.5, c4.img)

    }

    @Test
    fun testMultiplication() {
        val c1 = Complex(2.0, -3.1)

        var c2 = c1 * 2
        assertEquals(4.0, c2.real)
        assertEquals(-6.2, c2.img)

        c2 = c1 * 2.5
        assertEquals(5.0, c2.real)
        assertEquals(-7.75, c2.img)

        c2 = c1 * 0
        assertEquals(0.0, c2.real)
        assertEquals(0.0, c2.img, 0.001)

        assertEquals(ONE, i * (-i))
    }

    @Test
    fun testMultiplyByZero() {
        val c = Complex(1, -1)
        assertEquals(ZERO, 0 * c)
        assertEquals(ZERO, c * 0.0)
        assertEquals(ZERO, c * ZERO)
    }

    @Test
    fun testDivision() {
        val c1 = Complex(3.0, -2.5)
        var c2 = c1 / 2.0
        assertEquals(1.5, c2.real)
        assertEquals(-1.25, c2.img)

        c2 = c1 / Complex(-1.0, 1.0)
        assertEquals(-2.75, c2.real)
        assertEquals(-0.25, c2.img)

    }

    @Test
    fun testDivideByZero() {
        val c1 = Complex(3.0, -2.5)
        val div1 = c1 / 0
        assertEquals(Double.POSITIVE_INFINITY, div1.real)
        assertEquals(Double.NEGATIVE_INFINITY, div1.img)

        val c2 = Complex(-3.0, 2.5)
        val div2 = c2 / ZERO
        assertEquals(Double.NEGATIVE_INFINITY, div2.real)
        assertEquals(Double.POSITIVE_INFINITY, div2.img)
    }

    @Test
    fun testPolarCoordinates() {
        val c1 = Complex(3.0, -4.0)
        assertEquals(5.0, c1.abs())
        assertEquals(5.0, abs(c1))
        assertEquals(-0.9273, c1.phase(), 0.001)

        val c2 = Complex.fromPolar(5.0, -0.9273)
        assertEquals(c1.real, c2.real, 0.0001)
        assertEquals(c1.img, c2.img, 0.0001)
    }

    @Test
    fun testSpecialFunctions() {
        val c1 = Complex(0.0, PI)
        val c2 = exp(c1)
        assertEquals("-1.0", c2.toString())

        val c3 = Complex(2.0, -1.0)
        var z = exp(c3)
        assertEquals(3.99232, z.real, 0.0001)
        assertEquals(-6.217676, z.img, 0.0001)

        z = sinh(c3)
        assertEquals(1.95960, z.real, 0.0001)
        assertEquals(-3.165778, z.img, 0.0001)

        z = cosh(c3)
        assertEquals(2.0327230, z.real, 0.0001)
        assertEquals(-3.0518977, z.img, 0.0001)

        z = tanh(c3)
        assertEquals(1.014793, z.real, 0.0001)
        assertEquals(-0.0338128, z.img, 0.0001)

        z = coth(c3)
        assertEquals(0.98432922, z.real, 0.0001)
        assertEquals(0.0327977, z.img, 0.0001)

        z = sin(c3)
        assertEquals(1.40311, z.real, 0.0001)
        assertEquals(0.48905, z.img, 0.0001)

        z = cos(c3)
        assertEquals(-0.6421481, z.real, 0.0001)
        assertEquals(1.068607, z.img, 0.0001)

        assertEquals(sin(c3) / cos(c3), tan(c3))
        assertEquals(cos(c3) / sin(c3), cot(c3))
        assertEquals(1 / cos(c3), sec(c3))

        z = c3.pow(2.3)
        assertEquals(3.07625065, z.real, 0.0001)
        assertEquals(-5.5725305, z.img, 0.0001)

        z = c3.pow(i)
        assertEquals(1.1022736, z.real, 0.0001)
        assertEquals(1.1457119, z.img, 0.0001)

        z = c3.pow(2)
        assertEquals(3.0000, z.real, 0.0001)
        assertEquals(-4.000, z.img, 0.0001)
    }

    @Test
    fun testPower() {
        val c = 1.0 + 0.5 * i
        val c10 = c to 10
        assertEquals("-0.2314453125-3.04296875i", c10.toString())

        val c9 = c to 9
        assertEquals("-1.40234375-2.341796875i", c9.toString())

        assertEquals(-0.004219778905924383 + 0.10914868894792414 * i, (1 + 2 * i) to (2 * i))
        assertEquals(-0.3002831060007772+3.3301906767855614*i, (1 + 2 * i) to 1.5)
    }


    @Test
    fun testRoots() {
        val roots3 = roots(3)
        assertEquals(3, roots3.size)
        roots3.map { it to 3 }.forEach { assertEquals(1.0, it.abs(), 000.1) }
        val roots5 = roots(5)
        roots5.map { it to 5 }.forEach { assertEquals(1.0, it.abs(), 000.1) }
    }
}
