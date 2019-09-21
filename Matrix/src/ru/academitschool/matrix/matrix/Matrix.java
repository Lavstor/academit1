package ru.academitschool.matrix.matrix;

import ru.academit.vector.vectors.Vector;

public class Matrix {
    private Vector[] rows;
    private final static double EPSILON = 0.00001;

    public Matrix(int row, int column) {
        if (column <= 0 || row <= 0) {
            throw new IllegalArgumentException("Размерность  не корректна");
        }

        rows = new Vector[row];

        for (int i = 0; i < row; i++) {
            rows[i] = new Vector(column);
        }
    }

    public Matrix(double[][] donor) {
        int length = 0;

        for (double[] vector : donor) {
            if (vector.length == 0) {
                throw new IndexOutOfBoundsException("Размерность массива неверная");
            }

            if (length < vector.length) {
                length = vector.length;
            }
        }
        rows = new Vector[donor.length];

        for (int i = 0; i < donor.length; i++) {
            rows[i] = new Vector(length, donor[i]);
        }
    }

    public Matrix(Matrix matrix) {
        Vector[] vectors = new Vector[matrix.getColumnAmount()];

        for (int i = 0; i < matrix.getColumnAmount(); i++) {
            vectors[i] = new Vector(matrix.rows[i]);
        }

        rows = vectors;
    }

    public Matrix(Vector[] vectors) {
        int length = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() == 0) {
                throw new IndexOutOfBoundsException("Размерность вектора неверная");
            }

            if (length < vector.getSize()) {
                length = vector.getSize();
            }
        }
        rows = new Vector[vectors.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(length, vectors[i]);
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

    private int getRowAmount() {
        return rows.length;
    }

    private int getColumnAmount() {
        return rows[0].getSize();
    }

    public void setRow(int index, Vector vector) {
        if (index > getColumnAmount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс не корректен");
        }

        if (vector.getSize() != getColumnAmount()) {
            throw new IllegalArgumentException("Размерность не корректна");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getRow(int index) {
        if (index >= getRowAmount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше либо равен 0 и меньше количества строк в матрице");
        }

        return new Vector(rows[index]);
    }

    public Vector getColumn(int index) {
        if (index >= getColumnAmount() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс колонки должен быть больше либо равен 0 и не больше количества колонок в матрице");
        }

        double[] components = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            components[i] = rows[i].getComponent(index);
        }

        return new Vector(components);
    }

    public void transpose() {
        Vector[] temp = new Vector[getColumnAmount()];

        for (int i = 0; i < getColumnAmount(); i++) {
            temp[i] = new Vector(rows.length);

            for (int j = 0; j < getRowAmount(); j++) {
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
        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Количество строк у вектора не совпадает с количеством строк у матрицы");
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < getRowAmount(); i++) {
            double component = 0;

            for (int j = 0; j < getColumnAmount(); j++) {
                component += rows[i].getComponent(j) * vector.getComponent(j);
            }
            result.setComponent(i, component);
        }

        return result;
    }

    public double getDeterminant() {
        if (this.getColumnAmount() != this.getRowAmount()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }
        Matrix matrix2 = new Matrix(rows);

        double determinant = 1;

        for (int i = 0; i < matrix2.getRowAmount(); ++i) {
            if (matrix2.getRowAmount() - i == 2) {
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
        if (matrix.getColumnAmount() != this.getColumnAmount() || matrix.getRowAmount() != this.getRowAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        for (int i = 0; i < this.getRowAmount(); i++) {
            this.rows[i].sum(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getRowAmount() != this.getRowAmount() || matrix.getColumnAmount() != this.getColumnAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i].difference(matrix.rows[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowAmount() != matrix2.getRowAmount() || matrix1.getColumnAmount() != matrix2.getColumnAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        Matrix matrix3 = new Matrix(matrix1);

        matrix3.subtract(matrix2);

        return matrix3;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowAmount() != matrix2.getRowAmount() || matrix1.getColumnAmount() != matrix2.getColumnAmount()) {
            throw new IllegalArgumentException("Размерности матриц должны быть одинаковые");
        }

        Matrix matrix3 = new Matrix(matrix1);

        matrix3.add(matrix2);

        return matrix3;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnAmount() != matrix2.getRowAmount()) {
            throw new IllegalArgumentException("Размер строки первой матрицы должен быть равен числу строк второй матрицы");
        }

        Matrix matrix3 = new Matrix(matrix1.getRowAmount(), matrix2.getColumnAmount());

        for (int i = 0; i < matrix1.getRowAmount(); i++) {
            for (int j = 0; j < matrix2.getColumnAmount(); j++) {
                matrix3.rows[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return matrix3;
    }
}