package complex


class ComplexPolynomial(vararg coeff: Complex) {

    private val TOLERANCE: Double = 0.00001

    val coefficients = create(arrayOf(*coeff))


    private fun create(coeff: Array<Complex>): Array<Complex> {
        var n = coeff.size
        while ((n > 1) && coeff[n - 1].izZero(TOLERANCE)) --n
        return coeff.sliceArray(IntRange(0, n - 1))
    }

    companion object {
        val ZERO = ComplexPolynomial(complex.ZERO)

        fun constant(c: Complex) = ComplexPolynomial(c)

        fun constant(n: Number) = ComplexPolynomial(Complex.fromNumber(n))

        fun monomial(degree: Int, coefficient: Complex): ComplexPolynomial {
            val a = Array<Complex>(degree + 1, { _ -> complex.ZERO })
            a[degree] = coefficient
            return ComplexPolynomial(*a)
        }

        fun of(a: DoubleArray) = ComplexPolynomial(*(a.map { n -> Complex.fromNumber(n) }.toTypedArray()))
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is ComplexPolynomial)
            return false
        return this.toString() == other.toString()
    }

    override fun hashCode(): Int {
        return this.toString().hashCode()
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

    fun degree() = coefficients.size - 1

    operator fun get(i: Int) = coefficients[i]

    override fun toString(): String {
        fun coefficientToString(i: Int): String {
            val s = "(" + coefficients[i].toString() + ")"
            if (i == 0) {
                return s
            }
            if (i == 1) {
                return s + "z"
            }
            return s + "z^" + i.toString()
        }
        return (0 until coefficients.size).map { i -> coefficientToString(i) }.joinToString(separator = "+")
    }

    operator fun unaryMinus() = ComplexPolynomial(*coefficients.map { c -> -c }.toTypedArray())

    operator fun times(n: Number) = ComplexPolynomial(*coefficients.map { c -> c * n }.toTypedArray())

    operator fun times(z: Complex) = ComplexPolynomial(*coefficients.map { c -> c * z }.toTypedArray())

    operator fun div(z : Complex) = ComplexPolynomial(*coefficients.map{c -> c / z}.toTypedArray())

    operator fun div(n : Number) = ComplexPolynomial(*coefficients.map{c -> c / n}.toTypedArray())


    operator fun plus(other: ComplexPolynomial): ComplexPolynomial {

        fun addCoefficient(i: Int): Complex {
            if (i > this.degree()) {
                return other.coefficients[i]
            }
            if (i > other.degree()) {
                return coefficients[i]
            }
            return coefficients[i] + other.coefficients[i]

        }

        val maxOrder = maxOf(degree(), other.degree())
        val coeff = Array<Complex>(maxOrder + 1, { i -> addCoefficient(i) })
        return ComplexPolynomial(*coeff)
    }

    operator fun minus(other: ComplexPolynomial): ComplexPolynomial {

        fun subtractCoefficient(i: Int): Complex {
            if (i > degree()) {
                return -other.coefficients[i]
            }
            if (i > other.degree()) {
                return coefficients[i]
            }
            return coefficients[i] - other.coefficients[i]

        }

        val maxOrder = maxOf(degree(), other.degree())
        val coeff = Array(maxOrder + 1, { i -> subtractCoefficient(i) })
        return ComplexPolynomial(*coeff)
    }

    operator fun times(other: ComplexPolynomial): ComplexPolynomial {
        val resultOrder = degree() + other.degree()

        val coeff = Array(resultOrder + 1, { _ -> Complex.fromNumber(0) })
        for (k in 0 until coefficients.size) {
            for (j in 0 until other.coefficients.size) {
                coeff[k + j] += coefficients[k] * other.coefficients[j]
            }
        }
        return ComplexPolynomial(*coeff)
    }

    operator fun div(other : ComplexPolynomial) : Pair<ComplexPolynomial, ComplexPolynomial> {
        return complex.divide(this, other)
    }

}