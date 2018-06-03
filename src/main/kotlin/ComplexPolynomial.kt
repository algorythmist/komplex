package complex

class ComplexPolynomial(val coefficients: Array<Complex>) {
    //TODO test that last coefficient is not zero

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

    //TODO display as poly
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
}