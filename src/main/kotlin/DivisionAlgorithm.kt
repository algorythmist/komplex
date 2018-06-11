package complex

/**
 * Division of polynomials using Euclid's algorithm
 */
fun divide(dividend: ComplexPolynomial,
           divisor: ComplexPolynomial): Pair<ComplexPolynomial, ComplexPolynomial> {

    var quotient = ComplexPolynomial.ZERO
    var remainder = dividend
    val divisorDegree = divisor.degree()
    var remainderDegree = remainder.degree()
    while (!isZero(remainder) && remainderDegree >= divisorDegree) {

        val c = remainder[remainderDegree] / divisor[divisorDegree]
        val monomial = ComplexPolynomial.monomial(remainderDegree - divisorDegree, c)

        remainder -= (monomial * divisor)
        quotient +=  monomial
        remainderDegree = remainder.degree()
    }
    return Pair(quotient, remainder)
}

fun gcd(f: ComplexPolynomial,
        g: ComplexPolynomial): ComplexPolynomial {
    var gcd = ComplexPolynomial(f)
    var s = ComplexPolynomial(g)
    while (!isZero(s)) {
        val remainder = (gcd / s).second
        gcd = s
        s = remainder
    }
    return gcd
}

/**
 * Check if the instance is the null polynomial.
 *
 * @return true if the polynomial is null
 */
private fun isZero(p: ComplexPolynomial): Boolean {
    return p.degree() == 0 && p[0] == complex.ZERO // TODO zero comparison
}