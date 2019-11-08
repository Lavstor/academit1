package ru.academit.school.serialization.matrix;

import java.util.Arrays;

public class Matrix {
    private int[][] matrix;

    public Matrix(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array.length != array[i].length) {
                throw new IllegalArgumentException("Нужна симметричная матрица!!!");
            }

            for (int j = 0; j < array.length; j++) {
                if (array[i][j] != array[j][i]) {
                    throw new IllegalArgumentException("Нужна симметричная матрица!!!");
                }
            }
        }
        matrix = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            matrix[i] = Arrays.copyOf(array[i], array.length);
        }
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();

        for (int[] aMatrix : matrix) {
            table.append(Arrays.toString(aMatrix));
            table.append(System.lineSeparator());
        }

        return table.toString();
    }
}
