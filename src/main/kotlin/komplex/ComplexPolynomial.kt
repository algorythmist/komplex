package komplex

operator fun Number.times(cp: ComplexPolynomial) = cp * this.toDouble()

operator fun Complex.times(cp: ComplexPolynomial) = cp * this

/**
 * a komplex polynomial of the form c[0] + c[1]z + c[2]z^2 + ...
 * where the c's are komplex numbers
 */
class ComplexPolynomial(vararg coeff: Complex) {

    private val TOLERANCE: Double = 0.00001

    private val coefficients = create(arrayOf(*coeff))

    //discard 0 coefficients from the right
    private fun create(coeff: Array<Complex>): Array<Complex> {
        var n = coeff.size
        while ((n > 1) && coeff[n - 1].isZero(TOLERANCE)) --n
        return coeff.sliceArray(IntRange(0, n - 1))
    }

    constructor(cp: ComplexPolynomial) : this(*cp.coefficients)

    companion object {
        val ZERO = ComplexPolynomial(komplex.ZERO)

        fun constant(c: Complex) = ComplexPolynomial(c)

        fun constant(n: Number) = ComplexPolynomial(Complex.fromNumber(n))

        fun monomial(degree: Int, coefficient: Complex): ComplexPolynomial {
            val a = Array<Complex>(degree + 1) { _ -> komplex.ZERO }
            a[degree] = coefficient
            return ComplexPolynomial(*a)
        }
        fun of(a: DoubleArray) = ComplexPolynomial(*(a.map { Complex.fromNumber(it) }.toTypedArray()))
    }

    override fun equals(other: Any?): Boolean {
        return (other is ComplexPolynomial && coefficients contentEquals other.coefficients)
    }

    //TODO implement without string repr
    override fun hashCode(): Int {
        return this.toString().hashCode()
    }

    operator fun invoke(x: Complex): Complex {
        var powx = x
        var v = coefficients[0]
        for (n in 1 until coefficients.size) {
            v += coefficients[n] * powx
            powx *= x
        }
        return v

    }

    val degree get() = coefficients.size - 1

    /**
     * Access the ith coefficient
     */
    operator fun get(i: Int) = coefficients[i]

    override fun toString(): String {

        if (this.isZero()) return "0"

        fun coefficientToString(i: Int): String {
            if (coefficients[i].isZero(0.00001)) {
                return ""
            }
            val s = "(${coefficients[i]})"
            return when (i) {
                0 -> s
                1 -> s + "z"
                else -> s + "z^" + i.toString()
            }
        }

        return (0 until coefficients.size).map { coefficientToString(it) }.filter { !it.isEmpty() }.joinToString(separator = "+")
    }

    operator fun unaryMinus() = ComplexPolynomial(*coefficients.map { c -> -c }.toTypedArray())

    operator fun times(n: Number) = ComplexPolynomial(*coefficients.map { c -> c * n }.toTypedArray())

    operator fun times(z: Complex) = ComplexPolynomial(*coefficients.map { c -> c * z }.toTypedArray())

    operator fun div(z: Complex) = ComplexPolynomial(*coefficients.map { c -> c / z }.toTypedArray())

    operator fun div(n: Number) = ComplexPolynomial(*coefficients.map { c -> c / n }.toTypedArray())

    operator fun plus(other: ComplexPolynomial): ComplexPolynomial {

        fun addCoefficient(i: Int): Complex {
            return when {
                i > this.degree -> other.coefficients[i]
                i > other.degree -> coefficients[i]
                else -> coefficients[i] + other.coefficients[i]
            }
        }

        val maxOrder = maxOf(degree, other.degree)
        val coeff = Array<Complex>(maxOrder + 1, { addCoefficient(it) })
        return ComplexPolynomial(*coeff)
    }

    operator fun minus(other: ComplexPolynomial): ComplexPolynomial {

        fun subtractCoefficient(i: Int): Complex {
            return when {
                i > degree -> -other.coefficients[i]
                i > other.degree -> coefficients[i]
                else -> coefficients[i] - other.coefficients[i]
            }
        }

        val maxOrder = maxOf(degree, other.degree)
        val coeff = Array(maxOrder + 1, { subtractCoefficient(it) })
        return ComplexPolynomial(*coeff)
    }

    operator fun times(other: ComplexPolynomial): ComplexPolynomial {
        val resultOrder = degree + other.degree

        val coeff = Array(resultOrder + 1, { _ -> Complex.fromNumber(0) })
        for (k in 0 until coefficients.size) {
            for (j in 0 until other.coefficients.size) {
                coeff[k + j] += coefficients[k] * other.coefficients[j]
            }
        }
        return ComplexPolynomial(*coeff)
    }

    operator fun div(other: ComplexPolynomial): Pair<ComplexPolynomial, ComplexPolynomial> {
        return komplex.divide(this, other)
    }

    private fun isZero() = degree == 0 && coefficients[0].isZero(TOLERANCE)

}