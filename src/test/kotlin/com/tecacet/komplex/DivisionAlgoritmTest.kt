package com.tecacet.komplex

import com.tecacet.komplex.Complex.Companion.ONE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DivisionAlgorithmTest {

    @Test
    fun testRealPolyniomialDivision() {
        val dividend = ComplexPolynomial.of(1.0, 1.0, 2.0, 1.0)
        val divisor = ComplexPolynomial.of(1.0, 2.0)
        val (quotient, remainder) = divide(dividend, divisor)
        assertEquals("(0.125)+(0.75)z+(0.5)z^2", quotient.toString())
        assertEquals("(0.875)", remainder.toString())

        assertEquals(dividend, divisor * quotient + remainder)
    }

    @Test
    fun testComplexPolynomialDivision() {
        val dividend = ComplexPolynomial(ONE, ONE, i)
        val divisor = ComplexPolynomial(ONE, i)
        val (quotient, remainder) = divide(dividend, divisor)
        assertEquals("(1.0)z", quotient.toString())
        assertEquals("(1.0)", remainder.toString())
    }

    @Test
    fun testGcd() {
        val f = ComplexPolynomial.of(-1.0, 0.0, 0.0, 0.0, 1.0)
        val g = ComplexPolynomial.of(-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0)
        val gcd = gcd(f, g)
        assertEquals("(-1.0)+(1.0)z^2", gcd.toString())
    }
}
