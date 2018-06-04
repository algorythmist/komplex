package complex.test

import complex.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComplexPolynomialTest {


    @Test
    fun testValue() {
        val coefficients = arrayOf(Complex(1.0, 1.0), i, Complex(1.0, 0.0))
        val p = ComplexPolynomial(coefficients)
        assertEquals(2, p.order())
        val z = p.value(Complex(0.0, -1.0))
        assertEquals(1.0, z.real)
        assertEquals(1.0, z.img)

    }

    @Test
    fun testUnaryMinus() {
        val coefficients = arrayOf(i, i + 1, i * 2)
        val p = -ComplexPolynomial(coefficients)
        assertEquals(-i, p[0])
        assertEquals(-i - 1, p[1])
        assertEquals(-i * 2, p[2])
    }

    @Test
    fun testTimesConstant() {
        val coefficients = arrayOf(i, i + 1, i * 2)
        val p = ComplexPolynomial(coefficients) * -2
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
        val p1 = ComplexPolynomial(arrayOf(1 + i, i, 1 + 0 * i))
        val p2 = ComplexPolynomial(arrayOf(ONE, i * 2 + 1, i, i - 2))
        assertEquals(2, p1.order())
        assertEquals(3, p2.order())
        val p3 = p1 + p2
        assertEquals(3, p3.order())
        assertEquals("(2.0000 + 1.0000i)+(1.0000 + 3.0000i)z+(1.0000 + 1.0000i)z^2+(-2.0000 + 1.0000i)z^3",
                p3.toString())

        val p4 = p2 - p1
        assertEquals(3, p4.order())
        val expected = listOf<Complex>(-i, 1 + i, -1 + i, -2 + i)
        verifyCoefficients(expected, p4)
    }

    @Test
    fun testMultiply() {
        val p1 = ComplexPolynomial(arrayOf(Complex.fromReal(1.0), Complex.fromReal(2.0), Complex.fromReal(-1.0)))
        val p2 = ComplexPolynomial(arrayOf(Complex.fromInt(2), i))
        val p = p1 * p2
        verifyCoefficients(listOf(2 + 0 * i, 4 + i, -2 + 2 * i, -i), p)
    }

    private fun verifyCoefficients(expected: List<Complex>, p: ComplexPolynomial) {
        for (i in 0..p.order()) {
            assertEquals(expected[i], p[i])
        }
    }
}