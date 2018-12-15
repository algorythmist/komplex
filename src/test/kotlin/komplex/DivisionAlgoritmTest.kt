package komplex

import komplex.ComplexPolynomial
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DivisionAlgoritmTest {

    @Test
    fun testDivision() {
        val dividend = ComplexPolynomial.of(doubleArrayOf(1.0, 1.0, 2.0, 1.0))
        val divisor = ComplexPolynomial.of(doubleArrayOf(1.0, 2.0))
        val (quotient, remainder) = komplex.divide(dividend, divisor)
        assertEquals("(0.1250)+(0.7500)z+(0.5000)z^2", quotient.toString())
        assertEquals("(0.8750)", remainder.toString())

        assertEquals(dividend, divisor * quotient + remainder)

        //TODO use komplex
    }


    @Test
    fun testGcd() {
        val f = ComplexPolynomial.of(doubleArrayOf(-1.0, 0.0, 0.0, 0.0, 1.0))
        val g = ComplexPolynomial.of(doubleArrayOf(-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0))
        val gcd = komplex.gcd(f, g)
        assertEquals("(-1.0000)+(1.0000)z^2", gcd.toString())
    }
}