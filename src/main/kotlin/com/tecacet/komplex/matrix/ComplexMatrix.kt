package com.tecacet.komplex.matrix

import com.tecacet.komplex.Complex
import org.jblas.ComplexDoubleMatrix
import org.jblas.DoubleMatrix

class ComplexMatrix {

    private val blasComplex : ComplexDoubleMatrix;

    constructor(real : Array<DoubleArray>, imaginary : Array<DoubleArray>) {
        this.blasComplex = ComplexDoubleMatrix(DoubleMatrix(real), DoubleMatrix(imaginary))
    }

    private constructor(fromBlas : ComplexDoubleMatrix) {
        this.blasComplex = fromBlas
    }

    //TODO: constructor(doubleArray : Array<Array<Complex>>) {}

    operator fun plus(other : ComplexMatrix) : ComplexMatrix {
        return ComplexMatrix(blasComplex.add(other.blasComplex))
    }

    operator fun times(other: ComplexMatrix) : ComplexMatrix {
        return ComplexMatrix(blasComplex.mul(other.blasComplex))
    }
}
