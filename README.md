# komplex

komplex is a Kotlin library for Complex Analysis.
It supports complex number and complex polynomial arithmetic.

## Complex Arithmetic
```kotlin
import komplex.*

 val c1 = Complex(2.0, -3.1)
 val c2 = 2 + 3*i
 val c3 = 5*c1 + i*PI
```

All the standard special functions are defined:

```kotlin
var c = sec(z)
var c = exp(z)
var c = tanh(z)
var c = ln(z)
var c = pow(z, 2.0)
var c = pow(z, z2)
```

et cetera...

## Complex Polynomials

```kotlin
val p1 = ComplexPolynomial(1 + i, i, 1 + 0 * i)
val p2 = ComplexPolynomial(ONE, i * 2 + 1, i, i - 2)

val p3 = p1 + p2
val p4 = p1 * p2
```

Division of complex polynomials is also supported:

```kotlin
val dividend = ComplexPolynomial(ONE, ONE, i)
val divisor = ComplexPolynomial(ONE, i)
val (quotient, remainder) = divide(dividend, divisor)
```