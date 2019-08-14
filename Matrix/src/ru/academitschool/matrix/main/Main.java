package ru.academitschool.matrix.main;

import ru.academitschool.matrix.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3,3);
        System.out.print(matrix1);
        Matrix matrix2 = new Matrix(3,0);

        double[][] array1 = {{2,3}, {4,5}};
        matrix2.fillMatrix(array1);

        System.out.println(matrix2);

        Matrix matrix3 = new Matrix(3,4);
        System.out.println(matrix2);
        System.out.println(matrix3);
        matrix3.getMatrixCopy(matrix2);
        System.out.println(matrix3);


    }
}
