# komplex

komplex is a Kotlin library for Complex Analysis.
It supports complex number and complex polynomial arithmetic.

## Complex Arithmetic
```kotlin
import com.tecacet.komplex.*

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

It is also possible to define powers of complex numbers in infix notation:

```kotlin
val c = 1.0 + 0.5 * i
val c10 = c to 10

val c = (1 + 2*i)to 2*i
```

## Complex Polynomials

The library supports complex polynomials and their operations

```kotlin
val p1 = ComplexPolynomial(1 + i, i, 1 + 0 * i)
val p2 = ComplexPolynomial(ONE, i * 2 + 1, i, i - 2)

val p3 = p1 + p2
val p4 = p1 * p2
```

Division of complex polynomials returns the quotient and remainder:

```kotlin
val dividend = ComplexPolynomial(ONE, ONE, i)
val divisor = ComplexPolynomial(ONE, i)
val (quotient, remainder) = divide(dividend, divisor)
```

Complex polynomials can be defined using infix notation in terms of the variable Z 
which represents the first order monomial:

```kotlin
val q = (1.0 + 1.0 * i) * (Z to 3) + 2.0 * (Z to 2) + 1.0 * Z - (1.0 + 0.5 * i)
assertEquals("(-1.0-0.5i)+(1.0)z+(2.0)z^2+(1.0+1.0i)z^3", q.toString())
```