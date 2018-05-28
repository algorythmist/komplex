package complex



class Complex(val real: Double, val img: Double) {

    operator fun unaryMinus() = Complex(-real, -img)

    operator fun plus(c: Complex) = Complex(real + c.real, img + c.img)

    operator fun times(c: Complex) = Complex(real*c.real - img*c.img, real*c.img+ img*c.real)

    operator fun times(n: Number) = Complex(n.toDouble()*real, n.toDouble()*img)

    operator fun div(n: Number) = Complex(real/n.toDouble(), img/n.toDouble())

    operator fun div(c: Complex) : Complex {
        val den = c.normSquared();
        val num = this*c.conjugate()
        return num/den
    }

    fun conjugate(): Complex = Complex(real ,-img)

    fun normSquared() : Double = real*real + img*img

    override fun toString(): String {
        if (img < 0) {
            return "%.4f - %.4fi".format(real, -img)
        }
        return "%.4f + %.4fi".format(real, img)
    }

}