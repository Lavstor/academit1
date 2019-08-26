package ru.academitschool.matrix.main;

import ru.academit.vector.vectors.Vector;
import ru.academitschool.matrix.matrix.Matrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);
        System.out.print(matrix1);

        double[][] array1 = {{2, 3}, {4, 5}};
        Matrix matrix2 = new Matrix(array1);

        System.out.println(matrix2);

        Matrix matrix3 = new Matrix(3, 4);
        System.out.println(matrix2);
        System.out.println(matrix3);
        matrix3.getMatrixCopy(matrix2);
        System.out.println(matrix3);

        double[] array3 = {2, 3, 4, 5, 6, 7};
        double[] array4 = {9, 3, 32, 25, 6, 10};
        double[] array5 = {12, 3, 1, 5, 4, 7};

        Vector vector1 = new Vector(array3);
        Vector vector2 = new Vector(array4);
        Vector vector3 = new Vector(array5);

        System.out.println(matrix2);
        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println(vector3);

        Vector[] vectors = {vector1, vector2, vector3};

        matrix2.fillMatrix(vectors);
        System.out.println(matrix2);
        System.out.println(Arrays.toString(matrix2.getMatrixSize()));

        Vector vector4 = new Vector(1);
        matrix2.setVectorLine(0, vector4);
        System.out.println(matrix2);

        matrix2.fillMatrix(vectors);
        System.out.println(matrix2);

        matrix2.setVectorLine(0, vector1);
        System.out.println(matrix2);

        System.out.println(matrix2.getVectorLine(2));

        System.out.println(matrix2);

        System.out.println(matrix2.getVectorColumn(0));

        matrix2.transportMatrix();
        System.out.println(matrix2);

        System.out.println(matrix2);

        matrix2.MatrixMultipleScalar(2);
        System.out.println(matrix2);



        double[][] array2 = {{2, 4, 9}, {7, 80, 9}, {7, 9, 9}};
        Matrix matrix4 = new Matrix(array2);

        System.out.println(matrix4.getMatrixDeterminant());
        System.out.println(matrix4);

        double[] array6 = {4, 5, 6};
        Vector vector5 = new Vector(array6);


        System.out.println(matrix2);
        System.out.println(vector5);
        Matrix matrix5 = matrix2.MatrixMultipleVector(vector5);
        System.out.println(matrix5);

        double[][] array7 = {{2, 4, 9}, {7, 80, 9}, {7, 9, 9}};
        double[][] array8 = {{2, 4, 9}, {7, 80, 9}, {7, 9, 9}};

        Matrix matrix6 = new Matrix(array7);
        Matrix matrix7 = new Matrix(array8);


        System.out.println(matrix6);
        System.out.println(matrix7);

        matrix6.getMatrixSum(matrix7);
        System.out.println(matrix6);

        matrix6.getMatrixDifference(matrix7);
        System.out.println(matrix6);

        Matrix matrix8 = Matrix.getDifference(matrix7, matrix6);
        System.out.println(matrix8);

        Matrix matrix9 = Matrix.getSum(matrix7, matrix6);
        System.out.println(matrix9);

        System.out.println(matrix7);
        System.out.println(matrix6);
        Matrix matrix10 = Matrix.getMultiplication(matrix7, matrix6);
        System.out.println(matrix10);
    }
}
