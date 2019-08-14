package ru.academitschool.matrix.matrix;

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
}