package ru.academitschool.matrix.matrix;

import ru.academit.vector.vectors.Vector;

import java.util.Arrays;


public class Matrix {
    private double[][] matrix;

    public Matrix(int n, int m) {
        if (n <= 0 && m > 0) {
            n = m;
        }
        if (m <= 0 && n > 0) {
            m = n;
        }
        if (m <= 0) {
            m = 1;
            n = 1;
        }
        matrix = new double[n][m];
    }

    public Matrix(double[][] donor) {
        for (int i = 0; i < donor.length; i++) {
            if (donor[i].length != donor.length) {
                double[][] arrayCopy = donor;
                donor = new double[donor.length][donor.length];
                int k = 0;

                for (int j = 0; j < donor.length; j++) {
                    donor[j][k] = arrayCopy[j][i];
                    k++;
                }
            }
        }

        matrix = donor;
    }

    public Matrix(Matrix matrix) {
        double[][] newArray = Arrays.copyOf(matrix.matrix, matrix.matrix.length);
        this.matrix = new double[matrix.matrix.length][matrix.matrix[0].length];

        fillMatrix(newArray);
    }

    private void fillMatrix(double[][] donor) {
        if (matrix.length != matrix[0].length) {
            double[][] matrix2 = matrix;

            matrix = new double[matrix.length][matrix.length];
            fillMatrix(matrix2);
        }
        for (int i = 0; i < donor.length; i++) {
            for (int j = 0; j < donor[i].length; j++) {
                if (donor.length > matrix.length) {
                    matrix = new double[donor.length][matrix[i].length];
                }
                if (donor[i].length > matrix[i].length) {
                    matrix = new double[matrix.length][donor[i].length];
                }

                matrix[i][j] = donor[i][j];
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (matrix.length != matrix[0].length) {
            double[][] matrix2 = matrix;
            matrix = new double[matrix.length][matrix.length];

            fillMatrix(matrix2);
        }
        int maxLength = 0;

        for (Vector vector : vectors) {
            if (maxLength < vector.getComponents().length) {
                maxLength = vector.getComponents().length;
            }
        }
        double[][] donor = new double[vectors.length][maxLength];

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getComponents().length; j++) {
                donor[i][j] = vectors[i].getComponents()[j];
            }
        }

        fillMatrix(donor);
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("{");

        for (int i = 0; i < matrix.length; i++) {
            table.append("{");
            for (int j = 0; j < matrix[i].length; j++) {
                table.append(matrix[i][j]);
                if (j != matrix[i].length - 1) {
                    table.append(", ");
                }
            }
            if (i != matrix.length - 1) {
                table.append("}, ");
            } else {
                table.append("}");
            }
        }
        table.append("}\n");

        return table.toString();
    }

    public void getMatrixCopy(Matrix matrix) {
        double[][] newArray = Arrays.copyOf(matrix.matrix, matrix.matrix.length);
        this.matrix = new double[matrix.matrix.length][matrix.matrix[0].length];

        fillMatrix(newArray);
    }

    public void fillMatrix(Vector[] vectors) {
        if (matrix.length != matrix[0].length) {
            double[][] matrix2 = matrix;
            matrix = new double[matrix.length][matrix.length];

            fillMatrix(matrix2);
        }
        int maxLength = 0;

        for (Vector vector : vectors) {
            if (maxLength < vector.getComponents().length) {
                maxLength = vector.getComponents().length;
            }
        }
        double[][] donor = new double[vectors.length][maxLength];

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].getComponents().length; j++) {
                donor[i][j] = vectors[i].getComponents()[j];
            }
        }

        fillMatrix(donor);
    }

    public int[] getMatrixSize() {
        return new int[]{matrix.length, matrix[0].length};
    }

    public void setVectorLine(int n, Vector vector) {
        if (n > matrix[n].length) {
            throw new IllegalArgumentException("Размерность должна быть больше 0 и меньше размера матрицы");
        }
        for (int i = 0; i < vector.getComponents().length; i++) {
            matrix[n][i] = vector.getComponents()[i];
        }
    }

    public Vector getVectorLine(int n) {
        if (n > matrix[n].length) {
            throw new IllegalArgumentException("Размерность должна быть больше 0 и меньше размера матрицы");
        }

        Vector vector = new Vector(matrix[n]);

        return vector;
    }

    public Vector getVectorColumn(int n) {
        if (n > matrix.length) {
            throw new IllegalArgumentException("Размерность должна быть больше 0 и меньше размера матрицы");
        }
        double[] components = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            components[i] = matrix[i][n];
        }
        Vector vector = new Vector(components);

        return vector;
    }

    public void transportMatrix() {
        double[][] temp = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][i] = matrix[i][j];
            }
        }

        matrix = temp;
    }


    public Matrix MatrixMultipleVector(Vector vector) {
        double[] vectorComponents = vector.getComponents();

        Matrix newMatrix = new Matrix(3, 1);

        for (int i = 0; i < vectorComponents.length; i++) {
            double num = 0;

            for (int j = 0; j < vectorComponents.length; j++) {
                num += matrix[i][j] * vectorComponents[j];
            }
            newMatrix.matrix[i][0] = num;
        }

        return newMatrix;
    }

    public void MatrixMultipleScalar(double scalar) {
        for (int i = 0; i < matrix[i].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] *= scalar;
            }
        }
    }

    public double getMatrixDeterminant() {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        double[][] matrix2 = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; ++i) {
            System.arraycopy(matrix[i], 0, matrix2[i], 0, matrix.length);
        }
        double determinant = 1;

        for (int i = 0; i < matrix2.length; ++i) {
            if (matrix2.length - i == 2) {
                determinant *= (matrix2[i][i] * matrix2[i + 1][i + 1] - matrix2[i][i + 1] * matrix2[i + 1][i]);
                break;
            }
            doZeroAlgorithm(matrix2, i);
            determinant *= matrix2[i][i];
        }

        return determinant;
    }

    private static double getMultiplyNumber(double x, double y) {
        if (x == 0) {
            return 0;
        }

        return -(y / x);
    }

    private void doZeroAlgorithm(double[][] matrix, int k) {
        for (int i = 1 + k; i < matrix.length; ++i) {
            double multiplyNumber = getMultiplyNumber(matrix[k][k], matrix[i][k]);

            for (int j = k; j < matrix.length; ++j) {
                matrix[i][j] += multiplyNumber * matrix[k][j];
            }
        }
    }

    public void getMatrixSum(Matrix matrix) {
        if (matrix.matrix.length != this.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                this.matrix[i][j] += matrix.matrix[i][j];
            }
        }
    }

    public void getMatrixDifference(Matrix matrix) {
        if (matrix.matrix.length != this.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                this.matrix[i][j] -= matrix.matrix[i][j];
            }
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix1.matrix.length, matrix1.matrix.length);

        matrix3.getMatrixCopy(matrix1);
        matrix3.getMatrixDifference(matrix2);

        return matrix3;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix1.matrix.length, matrix1.matrix.length);

        matrix3.getMatrixCopy(matrix1);
        matrix3.getMatrixSum(matrix2);

        return matrix3;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }
        Matrix matrix3 = new Matrix(matrix1.matrix.length, matrix1.matrix.length);

        matrix3.getMatrixCopy(matrix1);

        for (int i = 0; i < matrix1.matrix.length; i++) {
            for (int j = 0; j < matrix1.matrix.length; j++) {
                matrix3.matrix[i][j] *= matrix2.matrix[i][j];
            }
        }

        return matrix3;
    }
}