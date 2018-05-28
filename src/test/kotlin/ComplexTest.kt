package komplex

import complex.Complex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ComplexTest {

    @Test
    fun testComplex() {
        val c1  = Complex(2.0, -3.0)
        Assertions.assertEquals("2.0-3.0i", c1.toString())
    }
}