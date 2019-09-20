package ru.academitschool.matrix.matrix;

import ru.academit.vector.vectors.Vector;

public class Matrix {
    private Vector[] vectorArray;
    private final static double EPSILON = 0.00001;

    public Matrix(int row, int column) {
        if (column <= 0 || row <= 0) {
            throw new IllegalArgumentException("Размерность  не корректна");
        }

        vectorArray = new Vector[row];

        for (int i = 0; i < row; i++) {
            vectorArray[i] = new Vector(column);
        }
    }

    public Matrix(double[][] donor) {
        if (donor[0].length == 0) {
            donor = new double[donor.length][1];
        }
        int length = 0;

        for (double[] vector : donor) {
            if (length < vector.length) {
                length = vector.length;
            }
        }
        vectorArray = new Vector[donor.length];

        for (int i = 0; i < donor.length; i++) {
            vectorArray[i] = new Vector(length, donor[i]);
        }
    }

    public Matrix(Matrix matrix) {
        Vector[] vectors = new Vector[matrix.vectorArray.length];

        for (int i = 0; i < matrix.vectorArray.length; i++) {
            vectors[i] = new Vector(matrix.vectorArray[i]);
        }

        vectorArray = vectors;
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            vectors = new Vector[1];
        }
        vectorArray = new Vector[vectors.length];

        int length = 0;

        for (Vector vector : vectors) {
            if (length < vector.getSize()) {
                length = vector.getSize();
            }
        }
        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] = new Vector(length, vectors[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("{");

        for (int i = 0; i < vectorArray.length; i++) {
            table.append(vectorArray[i]);

            if (i + 1 != vectorArray.length) {
                table.append(", ");
            }
        }
        table.append("}");

        return table.toString();
    }

    private int getMatrixRowSize() {
        return vectorArray.length;
    }

    private int getMatrixColumnSize() {
        return vectorArray[0].getSize();
    }

    public void setRow(int n, Vector vector) {
        if (vector.getSize() != vectorArray.length) {
            throw new IndexOutOfBoundsException("Размерность не корректна");
        }

        if (n > vectorArray.length || n < 0) {
            throw new IndexOutOfBoundsException("Индекс не корректен");
        }

        vectorArray[n] = new Vector(vector);
    }

    public Vector getRow(int index) {
        if (index >= getMatrixRowSize() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше либо равен 0 и меньше количества строк в матрице");
        }

        return new Vector(vectorArray[index]);
    }

    public Vector getColumn(int index) {
        if (index > getMatrixColumnSize() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс колонки должен быть больше либо равен 0 и не больше количества колонок в матрице");
        }

        double[] components = new double[vectorArray.length];

        for (int i = 0; i < vectorArray.length; i++) {
            components[i] = vectorArray[i].getComponent(index);
        }

        return new Vector(components);
    }

    public void transpose() {
        int deleteColumn = 0;

        if (getMatrixRowSize() != getMatrixColumnSize()) {
            deleteColumn = getMatrixRowSize() - getMatrixColumnSize();
        }
        Vector[] temp = new Vector[vectorArray.length - deleteColumn];

        for (int i = 0; i < getMatrixColumnSize(); i++) {
            temp[i] = new Vector(vectorArray.length);

            for (int j = 0; j < getMatrixRowSize(); j++) {
                temp[i].setComponent(j, vectorArray[j].getComponent(i));
            }
        }

        vectorArray = temp;
    }

    public void multiply(double scalar) {
        for (Vector vector : vectorArray) {
            vector.multiplyOnScalar(scalar);
        }
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != vectorArray[0].getSize()) {
            throw new IllegalArgumentException("Вектор должен быть такого же размера как и матрица");
        }

        Vector result = new Vector(vectorArray.length);

        for (int i = 0; i < getMatrixRowSize(); i++) {
            double component = 0;

            for (int j = 0; j < getMatrixColumnSize(); j++) {
                component += vectorArray[i].getComponent(j) * vector.getComponent(j);
            }
            result.setComponent(i, component);
        }

        return result;
    }

    public double getDeterminant() {
        if (this.getMatrixColumnSize() != this.getMatrixRowSize()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        if (vectorArray.length == 1) {
            return vectorArray[0].getComponent(0);
        }
        Matrix matrix2 = new Matrix(vectorArray);

        double determinant = 1;

        for (int i = 0; i < matrix2.getMatrixRowSize(); ++i) {
            if (matrix2.getMatrixRowSize() - i == 2) {
                determinant *= (matrix2.vectorArray[i].getComponent(i) *
                        matrix2.vectorArray[i + 1].getComponent(i + 1) -
                        matrix2.vectorArray[i].getComponent(i + 1) *
                                matrix2.vectorArray[i + 1].getComponent(i));
                break;
            }
            doZeroAlgorithm(matrix2.vectorArray, i);
            determinant *= matrix2.vectorArray[i].getComponent(i);
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
        if (matrix.vectorArray.length != this.vectorArray.length || matrix.vectorArray[0].getSize() != this.vectorArray[0].getSize()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        for (int i = 0; i < this.vectorArray.length; i++) {
            this.vectorArray[i].sum(matrix.vectorArray[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.vectorArray.length != this.vectorArray.length || matrix.vectorArray[0].getSize() != this.vectorArray[0].getSize()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        for (int i = 0; i < this.vectorArray.length; i++) {
            this.vectorArray[i].difference(matrix.vectorArray[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectorArray.length != matrix2.vectorArray.length || matrix1.vectorArray[0].getSize() != matrix2.vectorArray[0].getSize()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        Matrix matrix3 = new Matrix(matrix1);

        matrix3.subtract(matrix2);

        return matrix3;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectorArray.length != matrix2.vectorArray.length || matrix1.vectorArray[0].getSize() != matrix2.vectorArray[0].getSize()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        Matrix matrix3 = new Matrix(matrix1);

        matrix3.add(matrix2);

        return matrix3;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getMatrixColumnSize() != matrix2.getMatrixRowSize()) {
            throw new IllegalArgumentException("Размер строки первой матрицы должен быть равен числу строк второй матрицы");
        }

        Matrix matrix3 = new Matrix(matrix2);

        for (int i = 0; i < matrix1.getMatrixRowSize(); i++) {
            for (int j = 0; j < matrix2.getMatrixColumnSize(); j++) {
                matrix3.vectorArray[i].setComponent(j, Vector.getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j)));
            }
        }

        return matrix3;
    }
}