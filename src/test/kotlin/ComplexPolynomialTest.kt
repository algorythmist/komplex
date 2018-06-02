package complex.test

import complex.Complex
import complex.ComplexPolynomial
import org.junit.jupiter.api.Test
import complex.i
import org.junit.jupiter.api.Assertions.assertEquals

class ComplexPolynomialTest {


    @Test
    fun testValue() {
        val coefficients = arrayOf(Complex(1.0, 1.0), i, Complex(1.0, 0.0))
        val p = ComplexPolynomial(coefficients)
        val z = p.value(Complex(0.0,-1.0))
        assertEquals(1.0, z.real)
        assertEquals(1.0, z.img)

    }
}