package complex

import kotlin.math.abs

class Complex(val real: Double, val img: Double) {

    override fun toString(): String {
        if (img < 0) {
            return "" + real +"-" + abs(img)+"i"
        }
        return "" + real + "+" + img + "i"
    }

}