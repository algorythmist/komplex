package complex

import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.atan
import kotlin.math.ln
import kotlin.math.pow

val ZERO = Complex(0.0, 0.0)
val ONE = Complex(1.0,0.0)
val i = Complex(0.0,1.0)

fun abs(c: Complex): Double = c.abs()

fun exp(c: Complex): Complex {
    val e = exp(c.real)
    return Complex(e * cos(c.img), e * sin(c.img))
}

fun sinh(c: Complex) = (exp(c) - exp(-c))/2

fun cosh(c: Complex) = (exp(c) + exp(-c))/2

fun tanh(c: Complex) = sinh(c) / cosh(c)

fun coth(c: Complex) =  cosh(c) / sinh(c)

fun cos(c: Complex) = (exp(i*c) + exp(-i*c)) / 2.0

fun sin(c: Complex) = i *(exp(-i*c) - exp(i*c)) / 2.0

fun tan(c: Complex) = sin(c) / cos(c)

fun cot(c: Complex) = cos(c) / sin(c)

fun sec(c: Complex) =  ONE / cos(c)

/**
 * The natural logarithm on the principal branch
 */
fun ln(c: Complex) = Complex(ln(c.abs()), c.phase())

operator fun Number.plus(c: Complex) = Complex(this.toDouble() + c.real, c.img)

operator fun Number.minus(c: Complex) = Complex(this.toDouble() - c.real, -c.img)

operator fun Number.times(c : Complex) = Complex(this.toDouble()* c.real, this.toDouble() * c.img)

/**
 * Defines complex numbers and their algebraic operations
 */
class Complex(val real: Double, val img: Double) {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Complex || real != other.real || img != other.img)
            return false
        return true
    }

    override fun hashCode(): Int {
        return real.hashCode() * 31 + img.hashCode()
    }

    operator fun unaryMinus() = Complex(-real, -img)

    operator fun plus(c: Complex) = Complex(real + c.real, img + c.img)

    operator fun plus(n : Number) = Complex(real + n.toDouble(), img)

    operator fun minus(c: Complex) = Complex(real - c.real, img - c.img)

    operator fun minus(n : Number) = Complex(real - n.toDouble(), img )

    operator fun times(c: Complex) = Complex(real * c.real - img * c.img, real * c.img + img * c.real)

    operator fun times(n: Number) = Complex(n.toDouble() * real, n.toDouble() * img)

    operator fun div(n: Number) = Complex(real / n.toDouble(), img / n.toDouble())

    operator fun div(c: Complex): Complex {
        val den = c.normSquared()
        val num = this * c.conjugate()
        return num / den
    }

    fun conjugate(): Complex = Complex(real, -img)

    fun normSquared(): Double = real * real + img * img

    fun abs(): Double = sqrt(this.normSquared())

    fun phase(): Double = atan(img/real)

    fun pow(a: Double) : Complex {
       return exp(ln(this)*a)
    }

    fun pow(a: Int) : Complex {
        return exp(ln(this)*a)
    }

    fun pow(a: Complex) : Complex {
        return exp(ln(this)*a)
    }

    override fun toString(): String {
        if (img < 0) {
            return "%.4f - %.4fi".format(real, -img)
        }
        return "%.4f + %.4fi".format(real, img)
    }

    companion object {
        fun fromNumber(n : Number) = Complex(n.toDouble(), 0.0)
    }

}