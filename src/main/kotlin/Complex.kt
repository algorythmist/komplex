package complex

class Complex(val real: Double, val img: Double) {

    operator fun unaryMinus() = Complex(-real, -img)

    operator fun plus(c: Complex) = Complex(real + c.real, img + c.img)

    override fun toString(): String {
        if (img < 0) {
            return "%.4f - %.4fi".format(real, -img)
        }
        return "%.4f + %.4fi".format(real, img)
    }

}