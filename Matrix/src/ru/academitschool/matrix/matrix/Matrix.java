package ru.academitschool.matrix.matrix;

import ru.academit.vector.vectors.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixTable;

    public Matrix(int row, int column) {
        if (row <= 0) {
            row = 1;
        }

        if (column <= 0) {
            column = 1;
        }
        matrixTable = new Vector[row];

        for (int i = 0; i < row; i++) {
            matrixTable[i] = new Vector(column);
        }
    }

    public Matrix(double[][] donor) {
        if(donor.length <= 0){
            donor = new double[1][1];
        }
      /*  for (int i = 0; i < donor.length; i++) {
            if (donor[i].length != donor.length) {
                double[][] arrayCopy = Arrays.copyOf(donor, donor.length);
                donor = new double[donor.length][donor.length];

                int k = 0;

                for (int j = 0; j < donor.length; j++) {
                    donor[j][k] = arrayCopy[j][i];
                    k++;
                }
            }
        }*/
        matrixTable = new Vector[donor.length];

        for (int i = 0; i < donor.length; i++) {
            matrixTable[i] = new Vector(donor[i]);
        }

    }

    public Matrix(Matrix matrix) {
        Vector[] vectors = new Vector[matrix.matrixTable.length];

        for (int i = 0; i < matrix.matrixTable.length; i++) {
           vectors[i] = new Vector(matrix.matrixTable[i]);
        }

        matrixTable = vectors;
    }

    public Matrix(Vector[] vectors) {
        if(vectors.length <= 0){
            vectors = new Vector[1];
        }
        matrixTable = new Vector[vectors.length];

        for (Vector vector : vectors) {
            if (vector.getSize() != vectors.length) {
                matrixTable = new Vector[vector.getSize()];

            }
        }
        for (int i = 0; i < matrixTable.length; i++) {
            if(i < vectors.length) {
                matrixTable[i] = new Vector(matrixTable.length, vectors[i]);
            } else{
                matrixTable[i] = new Vector(matrixTable.length);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("{");

        for (Vector aMatrix : matrixTable) {
            table.append(aMatrix);
        }
        table.append("}");

        return table.toString();
    }

    public int getMatrixSize() {
        return matrixTable.length;
    }

    public void setLine(int n, Vector vector) {
        if (n > matrixTable.length || vector.getSize() != matrixTable.length || n < 0) {
            throw new IndexOutOfBoundsException("Размерность или индекс не корректны");
        }

        matrixTable[n] = new Vector(vector);
    }

    public Vector getLine(int n) {
        if (n >= matrixTable.length || n < 0) {
            throw new IndexOutOfBoundsException("Размерность должна быть больше 0 и меньше размера матрицы");
        }

        return new Vector(matrixTable[n]);
    }

    public Vector getColumn(int n) {
        if (n >= matrixTable.length) {
            throw new IndexOutOfBoundsException("Размерность должна быть больше 0 и меньше размера матрицы");
        }
        double[] components = new double[matrixTable.length];

        for (int i = 0; i < matrixTable.length; i++) {
            components[i] = matrixTable[i].getComponent(n);
        }

        return new Vector(components);
    }

    public void transport() {
        Vector[] temp = new Vector[matrixTable.length];

        for (int i = 0; i < matrixTable.length; i++) {
            temp[i] = new Vector(matrixTable.length);

            for (int j = 0; j < matrixTable.length; j++) {
                temp[i].setComponent(j, matrixTable[j].getComponent(i));
            }
        }

        matrixTable = Arrays.copyOf(temp, temp.length);
    }

    public void multipleScalar(double scalar) {
        for (Vector vector : matrixTable) {
            vector.doMultiplication(scalar);
        }
    }

    public double getDeterminant() {
        if (matrixTable.length == 1) {
            return matrixTable[0].getComponent(0);
        }
        Vector[] matrix2 = new Vector[matrixTable.length];

        System.arraycopy(matrixTable, 0, matrix2, 0, matrix2.length);
        double determinant = 1;

        for (int i = 0; i < matrix2.length; ++i) {

            if (matrix2.length - i == 2) {
                determinant *= (matrix2[i].getComponent(i) * matrix2[i + 1].getComponent(i + 1) -
                        matrix2[i].getComponent(i + 1) * matrix2[i + 1].getComponent(i));
                break;
            }
            doZeroAlgorithm(matrix2, i);
            determinant *= matrix2[i].getComponent(i);
        }

        return determinant;
    }

    private static double getMultiplyNumber(double x, double y) {
        if (x == 0) {
            return 0;
        }

        return -(y / x);
    }

    private static void doZeroAlgorithm(Vector[] matrix, int k) {
        for (int i = 1 + k; i < matrix.length; ++i) {
            double multiplyNumber = getMultiplyNumber(matrix[k].getComponent(k), matrix[i].getComponent(k));

            for (int j = k; j < matrix.length; ++j) {
                matrix[i].setComponent(j, matrix[i].getComponent(j) + multiplyNumber * matrix[k].getComponent(j));
            }
        }
    }

    public Vector matrixMultipleVector(Vector vector) {

        Vector vectorToSet = new Vector(matrixTable.length);

        for (int i = 0; i < matrixTable.length; i++) {
            double component = 0;

            for (int j = 0; j < matrixTable.length; j++) {
                component += matrixTable[i].getComponent(j) * vector.getComponent(j);
            }
            vectorToSet.setComponent(i, component);
        }

        return vectorToSet;
    }

    public void getSum(Matrix matrix) {
        if (matrix.matrixTable.length != this.matrixTable.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        for (int i = 0; i < this.matrixTable.length; i++) {
            this.matrixTable[i].sum(matrix.matrixTable[i]);
        }
    }

    public void getDifference(Matrix matrix) {
        if (matrix.matrixTable.length != this.matrixTable.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        for (int i = 0; i < this.matrixTable.length; i++) {
            this.matrixTable[i].difference(matrix.matrixTable[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixTable.length != matrix2.matrixTable.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix1);

        matrix3.getDifference(matrix2);

        return matrix3;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixTable.length != matrix2.matrixTable.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix1);

        matrix3.getSum(matrix2);

        return matrix3;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrixTable.length != matrix2.matrixTable.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix2);

        for (int i = 0; i < matrix1.matrixTable.length; i++) {
            for (int j = 0; j < matrix1.matrixTable.length; j++) {
                matrix3.matrixTable[i].setComponent(j, Vector.getScalarProduct(matrix1.getLine(i), matrix2.getColumn(j)));
            }
        }
        return matrix3;
    }
}