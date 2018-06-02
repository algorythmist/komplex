package complex

class ComplexPolynomial(val coefficients : Array<Complex>) {
    //TODO test that last coefficient is not zero

    fun value(x: Complex) : Complex {
        var powx = x
        var v = coefficients[0]
        for (n in 1 until coefficients.size) {
            v += coefficients[n] * powx
            powx *= x
        }
        return v

    }
}