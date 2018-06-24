# komplex

Komplex is a Kotlin library for Complex Analysis. 
It uses operator overloading to make the expression look
as similar to mathemetical formulas as possible

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

