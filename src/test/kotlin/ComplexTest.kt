package complex

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.PI

class ComplexTest {


    @Test
    fun testEquals() {
        val c1  = Complex(2.0, -3.1)
        var c2  = Complex(2.0, -3.1)
        assertEquals(c1, c2)
        assertTrue(c1 == c2)
    }

    @Test
    fun testAdditionSubtraction() {
        val c1  = Complex(2.0, -3.1)
        Assertions.assertEquals("2.0000 - 3.1000i", c1.toString())
        val c2 = -c1
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
    }

    @Test
    fun testDivideByZero() {
        val c1  = Complex(3.0, -2.5)
        val c2 = c1/0
        println(c2) //TODO
    }

    @Test
    fun testPolarCoordinates() {
        val c1  = Complex(3.0, -4.0)
        Assertions.assertEquals(5.0, c1.abs())
        Assertions.assertEquals(5.0, abs(c1))
        Assertions.assertEquals(-0.9273, c1.phase(), 0.001)
    }

    @Test
    fun testSpecialFunctions() {
        val c1 = Complex(0.0, PI)
        val c2 = exp(c1)
        Assertions.assertEquals("-1.0000 + 0.0000i", c2.toString())

        val c3 = Complex(2.0, -1.0)
        var z = exp(c3)
        Assertions.assertEquals(3.99232, z.real, 0.0001)
        Assertions.assertEquals(-6.217676, z.img, 0.0001)

        z = sinh(c3)
        Assertions.assertEquals(1.95960, z.real, 0.0001)
        Assertions.assertEquals(-3.165778, z.img, 0.0001)

        z = cosh(c3)
        Assertions.assertEquals(2.0327230, z.real, 0.0001)
        Assertions.assertEquals(-3.0518977, z.img, 0.0001)

        z = tanh(c3)
        Assertions.assertEquals(1.014793, z.real, 0.0001)
        Assertions.assertEquals(-0.0338128, z.img, 0.0001)

        z = coth(c3)
        Assertions.assertEquals(0.98432922, z.real, 0.0001)
        Assertions.assertEquals(0.0327977, z.img, 0.0001)

    }

}