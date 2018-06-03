package complex.test

import complex.Complex
import complex.ComplexPolynomial
import complex.ONE
import complex.i
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
        val coefficients = arrayOf(i, i+1, i*2)
        val p = -ComplexPolynomial(coefficients)
        assertEquals(-i, p[0])
        assertEquals(-i-1, p[1])
        assertEquals(-i*2, p[2])
    }

    @Test
    fun testTimesConstant() {
        val coefficients = arrayOf(i, i+1, i*2)
        val p = ComplexPolynomial(coefficients) * -2
        assertEquals(-i*2, p[0])
        assertEquals(-i*2 - 2, p[1])
        assertEquals(-i*4, p[2])

        val p2 = p * i
        assertEquals(Complex(2.0, 0.0), p2[0])
        assertEquals( -i*2 + 2, p2[1])
        assertEquals(Complex(4.0, 0.0), p2[2])

    }

    @Test
    fun testAddSubtract() {
        val p1 = ComplexPolynomial(arrayOf(Complex(1.0, 1.0), i, Complex(1.0, 0.0)))
        val p2 = ComplexPolynomial(arrayOf(ONE, i * 2 + 1, i, i - 2))
        assertEquals(2, p1.order())
        assertEquals(3, p2.order())
        val p3 = p1 + p2
        assertEquals(3, p3.order())
        println(p3)

        val p4 = p2 - p1
        assertEquals(3, p4.order())
        println(p4)

    }
}