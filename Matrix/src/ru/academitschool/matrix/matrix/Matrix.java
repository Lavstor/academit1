package ru.academitschool.matrix.matrix;

import ru.academit.vector.vectors.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrix;

    public Matrix(int index) {
        if (index <= 0) {
            index = 1;
        }
        matrix = new Vector[index];

        for (int i = 0; i < index; i++) {
            matrix[i] = new Vector(index);
        }
    }

    public Matrix(double[][] donor) {
        for (int i = 0; i < donor.length; i++) {
            if (donor[i].length != donor.length) {
                double[][] arrayCopy = Arrays.copyOf(donor, donor.length);
                donor = new double[donor.length][donor.length];

                int k = 0;

                for (int j = 0; j < donor.length; j++) {
                    donor[j][k] = arrayCopy[j][i];
                    k++;
                }
            }
        }
        matrix = new Vector[donor.length];

        for (int i = 0; i < donor.length; i++) {
            matrix[i] = new Vector(donor[i]);
        }

    }

    public Matrix(Matrix matrix) {
        Vector[] vectors = new Vector[matrix.matrix.length];

        for (int i = 0; i < matrix.matrix.length; i++) {
           vectors[i] = new Vector(matrix.matrix[i]);
        }

        this.matrix = Arrays.copyOf(vectors, vectors.length);
    }

    public Matrix(Vector[] vectors) {
        matrix = new Vector[vectors.length];

        for (Vector vector : vectors) {
            if (vector.getSize() != vectors.length) {
                matrix = new Vector[vector.getSize()];

            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if(i < vectors.length) {
                matrix[i] = new Vector(matrix.length, vectors[i]);
            } else{
                matrix[i] = new Vector(matrix.length);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("{");

        for (Vector aMatrix : matrix) {
            table.append(aMatrix);
        }
        table.append("}");

        return table.toString();
    }

    public int getMatrixSize() {
        return matrix.length;
    }

    public void setLine(int n, Vector vector) {
        if (n > matrix.length || vector.getSize() != matrix.length || n < 0) {
            throw new IndexOutOfBoundsException("Размерность или индекс не корректны");
        }

        matrix[n] = new Vector(vector);
    }

    public Vector getLine(int n) {
        if (n >= matrix.length || n < 0) {
            throw new IndexOutOfBoundsException("Размерность должна быть больше 0 и меньше размера матрицы");
        }

        return new Vector(matrix[n]);
    }

    public Vector getColumn(int n) {
        if (n >= matrix.length) {
            throw new IndexOutOfBoundsException("Размерность должна быть больше 0 и меньше размера матрицы");
        }
        double[] components = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            components[i] = matrix[i].getComponent(n);
        }

        return new Vector(components);
    }

    public void transport() {
        Vector[] temp = new Vector[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            temp[i] = new Vector(matrix.length);

            for (int j = 0; j < matrix.length; j++) {
                temp[i].setComponent(j, matrix[j].getComponent(i));
            }
        }

        matrix = Arrays.copyOf(temp, temp.length);
    }

    public void multipleScalar(double scalar) {
        for (Vector vector : matrix) {
            vector.doMultiplication(scalar);
        }
    }

    public double getDeterminant() {
        if (matrix.length == 1) {
            return matrix[0].getComponent(0);
        }
        Vector[] matrix2 = new Vector[matrix.length];

        System.arraycopy(matrix, 0, matrix2, 0, matrix2.length);
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

        Vector vectorToSet = new Vector(matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            double component = 0;

            for (int j = 0; j < matrix.length; j++) {
                component += matrix[i].getComponent(j) * vector.getComponent(j);
            }
            vectorToSet.setComponent(i, component);
        }

        return vectorToSet;
    }

    public void getSum(Matrix matrix) {
        if (matrix.matrix.length != this.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        for (int i = 0; i < this.matrix.length; i++) {
            this.matrix[i].sum(matrix.matrix[i]);
        }
    }

    public void getDifference(Matrix matrix) {
        if (matrix.matrix.length != this.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        for (int i = 0; i < this.matrix.length; i++) {
            this.matrix[i].difference(matrix.matrix[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix1);

        matrix3.getDifference(matrix2);

        return matrix3;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix1);

        matrix3.getSum(matrix2);

        return matrix3;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix2);

        for (int i = 0; i < matrix1.matrix.length; i++) {
            for (int j = 0; j < matrix1.matrix.length; j++) {
                matrix3.matrix[i].setComponent(j, Vector.getScalarProduct(matrix1.getLine(i), matrix2.getColumn(j)));
            }
        }

        return matrix3;
    }
}