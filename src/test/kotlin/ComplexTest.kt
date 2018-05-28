package komplex

import complex.Complex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ComplexTest {

    @Test
    fun testComplex() {
        val c1  = Complex(2.0, -3.1)
        Assertions.assertEquals("2.0000 - 3.1000i", c1.toString())
        val c2 = -c1;
        Assertions.assertEquals("-2.0000 + 3.1000i", c2.toString())

        val c3 = Complex(-5.0, 2.0)

        val c4 = c1+c3
        Assertions.assertEquals(-3.0, c4.real)
        Assertions.assertEquals(-1.1, c4.img)
    }
}