package ru.academit.school.serialization.matrix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class Matrix implements Serializable {
    private int[][] matrix;
    static final long serialVersionUID = 1L;

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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            out.writeInt(matrix[0][i]);
        }

        out.writeInt(matrix[matrix.length - 1][matrix.length - 2]);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int length = in.readInt();
        matrix = new int[length][length];

        for (int i = 0; i < matrix.length; i++) {
            int x = in.readInt();

            matrix[0][i] = x;
            matrix[i][0] = x;

            if (i == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][j] = x;
                }
            }

            if (i == matrix.length - 1 && matrix.length % 2 == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][matrix.length - j - 1] = x;
                }
            }
        }
        int x = in.readInt();

        matrix[matrix.length - 1][matrix.length - 2] = x;
        matrix[matrix.length - 2][matrix.length - 1] = x;
    }
}
