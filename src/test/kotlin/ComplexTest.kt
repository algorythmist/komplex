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

        var c4 = c1+c3
        Assertions.assertEquals(-3.0, c4.real)
        Assertions.assertEquals(-1.1, c4.img)

        c4 =  c1*c3
        Assertions.assertEquals(-3.8, c4.real)
        Assertions.assertEquals(19.5, c4.img)

    }

    @Test
    fun testMultiplication() {
        val c1  = Complex(2.0, -3.1)

        var c2 = c1*2
        Assertions.assertEquals(4.0, c2.real)
        Assertions.assertEquals(-6.2, c2.img)

        c2 = c1*2.5
        Assertions.assertEquals(5.0, c2.real)
        Assertions.assertEquals(-7.75, c2.img)

        c2 = c1*0
        Assertions.assertEquals(0.0, c2.real)
        Assertions.assertEquals(0.0, c2.img, 0.001)
    }

    @Test
    fun testDivision() {
        val c1  = Complex(3.0, -2.5)
        var c2 = c1 / 2.0
        Assertions.assertEquals(1.5, c2.real)
        Assertions.assertEquals(-1.25, c2.img)

        c2 = c1 / Complex(-1.0, 1.0)
        Assertions.assertEquals(-2.75, c2.real)
        Assertions.assertEquals(-0.25, c2.img)

        println(c2*Complex(-1.0, 1.0))
    }

    @Test
    fun testDivideByZero() {
        val c1  = Complex(3.0, -2.5)
        val c2 = c1/0
        println(c2)
    }
}