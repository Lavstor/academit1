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

    public void fillMatrix(double[][] donor) {
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

    public void getMatrixCopy(Matrix matrix) {
        double[][] newArray = Arrays.copyOf(matrix.matrix, matrix.matrix.length);
        this.matrix = new double[matrix.matrix.length][matrix.matrix[0].length];

        fillMatrix(newArray);
    }

    public void fillMatrix(Vector[] vectors) {
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

        Vector vector = new Vector(matrix[n].length);
        vector.fillArray(matrix[n]);
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

        Vector vector = new Vector(matrix.length);
        vector.fillArray(components);

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

    public void MatrixMultipleVector(Vector vector) {
        double[] vectorComponents = vector.getComponents();

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] *= vectorComponents[j];
            }
        }
        for (double temp : vectorComponents) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }

}