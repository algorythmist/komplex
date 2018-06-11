package complex.test

import complex.ComplexPolynomial
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DivisionAlgoritmTest {

    @Test
    fun testDivision() {
        val dividend = ComplexPolynomial.of(doubleArrayOf(1.0, 1.0, 2.0, 1.0))
        val divisor = ComplexPolynomial.of(doubleArrayOf(1.0, 2.0))
        val (quotient, remainder) = complex.divide(dividend, divisor)
        assertEquals("(0.1250 + 0.0000i)+(0.7500 + 0.0000i)z+(0.5000 + 0.0000i)z^2", quotient.toString())
        assertEquals("(0.8750 + 0.0000i)", remainder.toString())

        assertEquals(dividend,  divisor * quotient + remainder)
    }


    @Test
    fun testGcd() {
        val f = ComplexPolynomial.of(doubleArrayOf(-1.0, 0.0, 0.0, 0.0, 1.0))
        val g = ComplexPolynomial.of(doubleArrayOf(-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0))
        val gcd = complex.gcd(f, g)
        assertEquals("(-1.0000 + 0.0000i)+(0.0000 + 0.0000i)z+(1.0000 + 0.0000i)z^2", gcd.toString())
    }
}