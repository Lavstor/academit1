package ru.academit.school.serialization.main;

import ru.academit.school.serialization.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {2, 4, 1}, {3, 1, 4}};
        Matrix matrix1 = new Matrix(array);

        System.out.println(matrix1);
    }
}
