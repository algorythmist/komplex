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