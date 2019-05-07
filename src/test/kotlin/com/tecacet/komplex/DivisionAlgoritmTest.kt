package com.tecacet.komplex

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DivisionAlgorithmTest {

    @Test
    fun testRealPolyniomialDivision() {
        val dividend = ComplexPolynomial.of(doubleArrayOf(1.0, 1.0, 2.0, 1.0))
        val divisor = ComplexPolynomial.of(doubleArrayOf(1.0, 2.0))
        val (quotient, remainder) = divide(dividend, divisor)
        assertEquals("(0.1250)+(0.7500)z+(0.5000)z^2", quotient.toString())
        assertEquals("(0.8750)", remainder.toString())

        assertEquals(dividend, divisor * quotient + remainder)
    }

    @Test
    fun testComplexPolynomialDivision() {
        val dividend = ComplexPolynomial(ONE, ONE, i)
        val divisor = ComplexPolynomial(ONE, i)
        val (quotient, remainder) = divide(dividend, divisor)
        assertEquals("(1.0000)z", quotient.toString())
        assertEquals("(1.0000)", remainder.toString())
    }

    @Test
    fun testGcd() {
        val f = ComplexPolynomial.of(doubleArrayOf(-1.0, 0.0, 0.0, 0.0, 1.0))
        val g = ComplexPolynomial.of(doubleArrayOf(-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0))
        val gcd = gcd(f, g)
        assertEquals("(-1.0000)+(1.0000)z^2", gcd.toString())
    }
}