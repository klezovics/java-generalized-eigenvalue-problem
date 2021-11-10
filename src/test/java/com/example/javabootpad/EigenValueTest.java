package com.example.javabootpad;

import org.junit.jupiter.api.Test;
import org.ojalgo.matrix.decomposition.Eigenvalue;
import org.ojalgo.matrix.store.Primitive64Store;

import javax.transaction.Transactional;

public class EigenValueTest {

    @Test
    @Transactional(value = Transactional.TxType.NEVER)
    void test() {
        var arr = new int[]{1, 2, 3, 4, 5};
        for (var i: arr) {
            System.out.println(i);
        }
    }


    @Test
    void main() {
        var dim = 2;

        Primitive64Store mtrxA = Primitive64Store.FACTORY.make(2, 2);
        mtrxA.fillOne(0, 0, 4.0);
        mtrxA.fillOne(0, 1, 0.0);
        mtrxA.fillOne(1, 0, 0.0);
        mtrxA.fillOne(1, 1, 1.0);

        System.out.println(mtrxA);

        Primitive64Store mtrxB = Primitive64Store.FACTORY.make(2, 2);

        mtrxB.fillOne(0, 0, 0.6);
        mtrxB.fillOne(0, 1, -0.5);
        mtrxB.fillOne(1, 0, -0.5);
        mtrxB.fillOne(1, 1, 0.7);

        System.out.println(mtrxB);
        /*
         * There are several generalisations. 3 are supported by ojAlgo, specified by the enum:
         * Eigenvalue.Generalisation This factory method returns the most common alternative.
         */
        Eigenvalue.Generalised<Double> generalisedEvD = Eigenvalue.PRIMITIVE.makeGeneralised(mtrxA);
        // Generalisation: [A][V] = [B][V][D]

        // Use 2-args alternative

        generalisedEvD.computeValuesOnly(mtrxA, mtrxB);
        generalisedEvD.decompose(mtrxA, mtrxB);

        // Eigenvectors in the columns
        System.out.println("Eigenvectors are in the columns below");
        System.out.println(generalisedEvD.getV());

        // Eigenvalues on the diagonal
        System.out.println("Eigenvalues are on the diagonal in the matrix below");
        System.out.println(generalisedEvD.getD());

        // You can also transpose the matrices with this lib
        var transposed = mtrxB.transpose();
        // Printing out the transposed matrix
        System.out.println(transposed.toString());
    }
}
