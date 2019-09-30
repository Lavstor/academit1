package ru.academitschool.matrix.matrix;

import ru.academit.vector.vectors.Vector;

public class Matrix {
    private Vector[] rows;
    private final static double EPSILON = 0.00001;

    public Matrix(int rows, int columns) {
        if (columns <= 0 || rows <= 0) {
            throw new IllegalArgumentException("Размерность  не корректна");
        }

        this.rows = new Vector[rows];

        for (int i = 0; i < rows; i++) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(double[][] donor) {
        int maxLength = 0;

        for (double[] vector : donor) {
            if (vector.length == 0) {
                throw new IndexOutOfBoundsException("Размерность массива неверная");
            }

            if (maxLength < vector.length) {
                maxLength = vector.length;
            }
        }
        rows = new Vector[donor.length];

        for (int i = 0; i < donor.length; i++) {
            rows[i] = new Vector(maxLength, donor[i]);
        }
    }

    public Matrix(Matrix matrix) {
        Vector[] vectors = new Vector[matrix.getColumnsAmount()];

        for (int i = 0; i < matrix.getColumnsAmount(); i++) {
            vectors[i] = new Vector(matrix.rows[i]);
        }

        rows = vectors;
    }

    public Matrix(Vector[] vectors) {
        int maxLength = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() == 0) {
                throw new IndexOutOfBoundsException("Размерность вектора неверная");
            }

            if (maxLength < vector.getSize()) {
                maxLength = vector.getSize();
            }
        }
        rows = new Vector[vectors.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxLength, vectors[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("{");

        for (int i = 0; i < rows.length; i++) {
            table.append(rows[i]);

            if (i + 1 != rows.length) {
                table.append(", ");
            }
        }
        table.append("}");

        return table.toString();
    }

    private int getRowsAmount() {
        return rows.length;
    }

    private int getColumnsAmount() {
        return rows[0].getSize();
    }

    public void setRow(int index, Vector vector) {
        if (index >= getColumnsAmount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс не корректен");
        }

        if (vector.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Размерность не корректна");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getRow(int index) {
        if (index >= getRowsAmount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше либо равен 0 и меньше количества строк в матрице");
        }

        return new Vector(rows[index]);
    }

    public Vector getColumn(int index) {
        if (index >= getColumnsAmount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс колонки должен быть больше либо равен 0 и не больше количества колонок в матрице");
        }

        double[] components = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            components[i] = rows[i].getComponent(index);
        }

        return new Vector(components);
    }

    public void transpose() {
        Vector[] temp = new Vector[getColumnsAmount()];

        for (int i = 0; i < getColumnsAmount(); i++) {
            temp[i] = new Vector(rows.length);

            for (int j = 0; j < getRowsAmount(); j++) {
                temp[i].setComponent(j, rows[j].getComponent(i));
            }
        }

        rows = temp;
    }

    public void multiply(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyOnScalar(scalar);
        }
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != getRowsAmount()) {
            throw new IllegalArgumentException("Количество элементов вектора не совпадает с количеством строк у матрицы");
        }

        Vector result = new Vector(vector.getSize());

        for (int i = 0; i < vector.getSize(); i++) {
            result.setComponent(i,2);
        }

        return result;
    }

    public double getDeterminant() {
        if (this.getColumnsAmount() != this.getRowsAmount()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }
        Matrix matrix2 = new Matrix(rows);

        double determinant = 1;

        for (int i = 0; i < matrix2.getRowsAmount(); ++i) {
            if (matrix2.getRowsAmount() - i == 2) {
                determinant *= (matrix2.rows[i].getComponent(i) *
                        matrix2.rows[i + 1].getComponent(i + 1) -
                        matrix2.rows[i].getComponent(i + 1) *
                                matrix2.rows[i + 1].getComponent(i));
                break;
            }
            doZeroAlgorithm(matrix2.rows, i);
            determinant *= matrix2.rows[i].getComponent(i);
        }

        return determinant;
    }

    private static double getMultiplyNumber(double x, double y) {
        if (Math.abs(x) < EPSILON) {
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

    public void add(Matrix matrix) {
        if (matrix.getColumnsAmount() != this.getColumnsAmount() || matrix.getRowsAmount() != this.getRowsAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        for (int i = 0; i < this.getRowsAmount(); i++) {
            this.rows[i].sum(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getRowsAmount() != this.getRowsAmount() || matrix.getColumnsAmount() != this.getColumnsAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i].difference(matrix.rows[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount() || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        Matrix matrix3 = new Matrix(matrix1);

        matrix3.subtract(matrix2);

        return matrix3;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount() || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        Matrix matrix3 = new Matrix(matrix1);

        matrix3.add(matrix2);

        return matrix3;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsAmount() != matrix2.getRowsAmount()) {
            throw new IllegalArgumentException("Размер строки первой матрицы должен быть равен числу строк второй матрицы");
        }

        Matrix matrix3 = new Matrix(matrix1.getRowsAmount(), matrix2.getColumnsAmount());

        for (int i = 0; i < matrix1.getRowsAmount(); i++) {
            for (int j = 0; j < matrix2.getColumnsAmount(); j++) {
                matrix3.rows[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return matrix3;
    }
}