package complex

class ComplexPolynomial(val coefficients: Array<Complex>) {
    //TODO test that last coefficient is not zero

    companion object {
        val ZERO = ComplexPolynomial(arrayOf(complex.ZERO))

        fun constant(c : Complex) = ComplexPolynomial(arrayOf(c))

        fun constant(n : Number) = ComplexPolynomial(arrayOf(Complex.fromNumber(n)))
    }

    fun value(x: Complex): Complex {
        var powx = x
        var v = coefficients[0]
        for (n in 1 until coefficients.size) {
            v += coefficients[n] * powx
            powx *= x
        }
        return v

    }

    fun order() = coefficients.size - 1

    operator fun get(i : Int) = coefficients[i]

    override fun toString(): String {
        fun coefficientToString(i : Int) : String {
           val s =  "("+coefficients[i].toString()+")"
           if (i == 0) {
               return s
           }
            if (i == 1) {
                return s+"z"
            }
            return s+"z^"+i.toString()
        }
        return (0 until coefficients.size).map { i -> coefficientToString(i) }.joinToString(separator = "+")
    }

    operator fun unaryMinus() = ComplexPolynomial(coefficients.map { c -> -c }.toTypedArray())

    operator fun times(n : Number) = ComplexPolynomial(coefficients.map { c -> c * n }.toTypedArray())

    operator fun times(z : Complex) = ComplexPolynomial(coefficients.map { c -> c * z }.toTypedArray())

    operator fun plus(other: ComplexPolynomial): ComplexPolynomial {

        fun addCoefficient(i: Int): Complex {
            if (i > this.order()) {
                return other.coefficients[i]
            }
            if (i > other.order()) {
                return coefficients[i]
            }
            return coefficients[i] + other.coefficients[i]

        }

        val maxOrder = maxOf(order(), other.order())
        val coeff = Array<Complex>(maxOrder + 1, { i -> addCoefficient(i) })
        return ComplexPolynomial(coeff)
    }

    operator fun minus(other: ComplexPolynomial): ComplexPolynomial {

        fun subtractCoefficient(i: Int): Complex {
            if (i > order()) {
                return -other.coefficients[i]
            }
            if (i > other.order()) {
                return coefficients[i]
            }
            return coefficients[i] - other.coefficients[i]

        }

        val maxOrder = maxOf(order(), other.order())
        val coeff = Array(maxOrder + 1, { i -> subtractCoefficient(i) })
        return ComplexPolynomial(coeff)
    }

    operator fun times(other : ComplexPolynomial) : ComplexPolynomial {
        val resultOrder = order() + other.order()

        val coeff = Array(resultOrder+1, { _ -> Complex.fromNumber(0)})
        for (k in 0 until coefficients.size) {
            for (j in 0 until other.coefficients.size) {
                coeff[k+j] += coefficients[k] * other.coefficients[j]
            }
        }
        return ComplexPolynomial(coeff)
    }
}