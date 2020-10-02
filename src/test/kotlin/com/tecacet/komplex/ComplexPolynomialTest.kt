package com.tecacet.komplex

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ComplexPolynomialTest {


    @Test
    fun testZeroCoefficients() {
        val p = ComplexPolynomial(Complex.ONE, Complex.ZERO, i, Complex(0.0, 0.0))
        assertEquals(2, p.degree)
        assertEquals("(1.0)+(1.0i)z^2", p.toString())
    }

    @Test
    fun testInvoke() {
        val p = 1 + i + (i * Z) + (Z to 2)
        assertEquals(2, p.degree)
        val z = p(Complex(0.0, -1.0))
        assertEquals(1.0, z.real)
        assertEquals(1.0, z.img)

        val (real, img) = p(1.1 - 2 * i)
        assertEquals(0.21, real, 0.00001)
        assertEquals(-2.3, img, 0.00001)

        val (r2, i2) = p(2)
        assertEquals(5.0, r2, 0.00001)
        assertEquals(3.0, i2, 0.00001)
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
        val p2 = ComplexPolynomial(Complex.ONE, i * 2 + 1, i, i - 2)
        assertEquals(2, p1.degree)
        assertEquals(3, p2.degree)
        val p3 = p1 + p2
        assertEquals(3, p3.degree)
        assertEquals("(2.0+1.0i)+(1.0+3.0i)z+(1.0+1.0i)z^2+(-2.0+1.0i)z^3",
                p3.toString())

        val p4 = p2 - p1
        assertEquals(3, p4.degree)
        val expected = listOf(-i, 1 + i, -1 + i, -2 + i)
        verifyCoefficients(expected, p4)
    }

    @Test
    fun testMultiply() {
        val p1 = ComplexPolynomial.of(1.0, 2.0, -1.0)
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
        val p1 = ComplexPolynomial(Complex.ONE, Complex.ZERO, Complex.ONE)
        val p2 = ComplexPolynomial(i, Complex.ONE)
        val (q, r) = p1 / p2
        assertEquals("(-1.0i)+(1.0)z", q.toString())
        assertEquals(ComplexPolynomial.ZERO, r)

        val p3 = p2 / i
        assertEquals("(1.0)+(-1.0i)z", p3.toString())

        //now divide p1 into p2
        val (a, b) = p2 / p1
        assertEquals(ComplexPolynomial.ZERO, a)
        assertEquals(p2, b)

        //try something harder
        val dividend = ComplexPolynomial(Complex.ONE, Complex.ZERO, -3 * i, 2 + i)
        val divisor = ComplexPolynomial(Complex.ONE, -1 + i)
        val (quotient, remainder) = dividend / divisor
        assertEquals(dividend, quotient * divisor + remainder)
    }

    @Test
    fun testEquals() {
        val p1 = ComplexPolynomial(Complex.ONE, Complex.ZERO, Complex.ONE)
        val p2 = ComplexPolynomial(i, Complex.ONE)
        assertFalse(p1.equals(null))
        assertFalse(p1 == p2)
        assertTrue(p1 == ComplexPolynomial(Complex.ONE, Complex.ZERO, Complex.ONE))
    }

    @Test
    fun testDerivative() {
        val p = 2 + Z + 3 * (Z to 2) + 4 * (Z to 3)
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
        val p = (1.0 + 1.0 * i) * Z * Z + 2.0 * Z + 1.0
        assertEquals("(1.0)+(2.0)z+(1.0+1.0i)z^2", p.toString())

        val q = (1.0 + 1.0 * i) * (Z to 3) + 2.0 * (Z to 2) + 1.0 * Z - (1.0 + 0.5 * i)
        assertEquals("(-1.0-0.5i)+(1.0)z+(2.0)z^2+(1.0+1.0i)z^3", q.toString())
    }

    @Test
    fun testPower() {
        val p = ComplexPolynomial(0.5 + i, 1 + 0.5 * i, Complex.fromNumber(2.0))
        val p3 = p to 5
        assertEquals("(1.28125-1.1875i)+(1.5625-8.59375i)z+(-12.1875-30.625i)z^2+(-65.625-45.3125i)z^3+(-157.34375-8.4375i)z^4+(-201.1875+113.78125i)z^5+(-139.375+245.0i)z^6+(10.0+255.0i)z^7+(100.0+160.0i)z^8+(80.0+40.0i)z^9+(32.0)z^10",
                p3.toString())
    }

    @Test
    fun testIsMonomial() {
        val m1 = ComplexPolynomial.monomial(10, 8 * i)
        assertTrue(m1.isMonomial())
        val m2 = ComplexPolynomial.monomial(5, 1 + i)
        assertTrue(m2.isMonomial())
        val p = m1 + m2
        assertFalse(p.isMonomial())
    }

    @Test
    fun testMonomialPower() {
        val m = ComplexPolynomial.monomial(2, 2)
        assertEquals(2, m.degree)
        val m5 = m to 5
        assertTrue(m.isMonomial())
        assertEquals(10, m5.degree)
        assertEquals("(2.0)z^10", m5.toString())
    }

    @Test
    fun testRepresentationOfPolynomials() {
        val p1 = ComplexPolynomial(Complex.fromNumber(2), i, Complex.fromNumber(-1), Complex.fromNumber(5))
        val p2 = ComplexPolynomial(2 + 0 * i, i, -1 + 0 * i, 5 + i * 0)
        val p3 = 2 + i * Z - 1 * (Z to 2) + 5 * (Z to 3)
        assertEquals(p1, p2)
        assertEquals(p2, p3)
        assertEquals(p3, p1)
    }

    private fun verifyCoefficients(expected: List<Complex>, p: ComplexPolynomial) {
        (0..p.degree).forEach { assertEquals(expected[it], p[it]) }
    }
}
