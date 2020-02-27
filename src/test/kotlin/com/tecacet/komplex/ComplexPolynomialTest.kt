package com.tecacet.komplex

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ComplexPolynomialTest {


    @Test
    fun testZeroCoefficients() {
        val p = ComplexPolynomial(ONE, ZERO, i, Complex(0.0, 0.0))
        assertEquals(2, p.degree)
        assertEquals("(1.0)+(1.0i)z^2", p.toString())
    }

    @Test
    fun testInvoke() {
        val p = ComplexPolynomial(1 + i, i, Complex(1.0, 0.0))
        assertEquals(2, p.degree)
        val z = p(Complex(0.0, -1.0))
        assertEquals(1.0, z.real)
        assertEquals(1.0, z.img)
    }

    @Test
    fun testUnaryMinus() {
        val p = -ComplexPolynomial(i, i + 1, i * 2)
        assertEquals(-i, p[0])
        assertEquals(-i - 1, p[1])
        assertEquals(-i * 2, p[2])
    }

    @Test
    fun testTimesConstant() {
        val p = ComplexPolynomial(i, i + 1, i * 2) * -2
        assertEquals(-i * 2, p[0])
        assertEquals(-i * 2 - 2, p[1])
        assertEquals(-i * 4, p[2])

        val p2 = p * i
        assertEquals(Complex(2.0, 0.0), p2[0])
        assertEquals(-i * 2 + 2, p2[1])
        assertEquals(Complex(4.0, 0.0), p2[2])

    }

    @Test
    fun testAddSubtract() {
        val p1 = ComplexPolynomial(1 + i, i, 1 + 0 * i)
        val p2 = ComplexPolynomial(ONE, i * 2 + 1, i, i - 2)
        assertEquals(2, p1.degree)
        assertEquals(3, p2.degree)
        val p3 = p1 + p2
        assertEquals(3, p3.degree)
        assertEquals("(2.0+1.0i)+(1.0+3.0i)z+(1.0+1.0i)z^2+(-2.0+1.0i)z^3",
                p3.toString())

        val p4 = p2 - p1
        assertEquals(3, p4.degree)
        val expected = listOf<Complex>(-i, 1 + i, -1 + i, -2 + i)
        verifyCoefficients(expected, p4)
    }

    @Test
    fun testMultiply() {
        val p1 = ComplexPolynomial.of(doubleArrayOf(1.0, 2.0, -1.0))
        val p2 = ComplexPolynomial(Complex.fromNumber(2), i)
        val p = p1 * p2
        verifyCoefficients(listOf(2 + 0 * i, 4 + i, -2 + 2 * i, -i), p)
    }

    @Test
    fun testConstant() {
        val zero = ComplexPolynomial.ZERO
        assertEquals("0", zero.toString())

        val p1 = ComplexPolynomial.constant(2)
        assertEquals("(2.0)", p1.toString())

        val p2 = ComplexPolynomial.constant(2 + i)
        assertEquals("(2.0+1.0i)", p2.toString())
    }

    @Test
    fun testDivide() {
        val p1 = ComplexPolynomial(ONE, ZERO, ONE)
        val p2 = ComplexPolynomial(i, ONE)
        val (q, r) = p1 / p2
        assertEquals("(-1.0i)+(1.0)z", q.toString())
        assertEquals(ComplexPolynomial.ZERO, r)

        val p3 = p2 / i
        assertEquals("(1.0)+(-1.0i)z", p3.toString())
    }

    @Test
    fun testEquals() {
        val p1 = ComplexPolynomial(ONE, ZERO, ONE)
        val p2 = ComplexPolynomial(i, ONE)
        assertFalse(p1.equals(null))
        assertFalse(p1 == p2)
        assertTrue(p1 == ComplexPolynomial(ONE, ZERO, ONE))
    }

    @Test
    fun testDerivative() {
        val p = ComplexPolynomial.of(doubleArrayOf(2.0, 1.0, 3.0, 4.0))
        assertEquals(3, p.degree)
        val dp = p.derivative()
        assertEquals(2, dp.degree)
        assertEquals("(1.0)+(6.0)z+(12.0)z^2", dp.toString())
    }

    @Test
    fun testAddConstant() {
        val p1 = ComplexPolynomial(1 + i, i, 1 + 0 * i)
        assertEquals("(1.0+1.0i)+(1.0i)z+(1.0)z^2", p1.toString())
        val p2 = p1 + 2
        assertEquals("(3.0+1.0i)+(1.0i)z+(1.0)z^2", p2.toString())
        val p3 = p1 + Complex(1.0, 1.0)
        assertEquals("(2.0+2.0i)+(1.0i)z+(1.0)z^2", p3.toString())
    }

    @Test
    fun testZ() {
        assertEquals("(1.0)z", Z.toString())
        val p = (1.0+1.0*i)*Z*Z + 2.0 * Z + 1.0
        assertEquals("(1.0)+(2.0)z+(1.0+1.0i)z^2", p.toString())
    }

    private fun verifyCoefficients(expected: List<Complex>, p: ComplexPolynomial) {
        (0..p.degree).forEach { assertEquals(expected[it], p[it]) }
    }
}
