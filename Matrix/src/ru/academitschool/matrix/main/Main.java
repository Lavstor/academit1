package ru.academitschool.matrix.main;

import ru.academit.vector.vectors.Vector;
import ru.academitschool.matrix.matrix.Matrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       /* Matrix matrix1 = new Matrix(3, 3);
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
        System.out.println(matrix10);*/

        double[][] array1 = {{2, 4, 9}, {7, 80, 9}, {7, 9, 9}};
        double[][] array2 = {{2, 14, 9, 1}, {7, 80, 9, 2}, {7, 9, 9, 2}};

        Matrix matrix1 = new Matrix(array1);
        System.out.println(matrix1);
        System.out.println();


        Matrix matrix2 = new Matrix(matrix1);
        System.out.println(matrix2);
        System.out.println();

        double[] array3 = {1, 24, 9};
        double[] array4 = {11, 4, 9};
        double[] array5 = {1, 4, 92};

        Vector vector1 = new Vector(array3);
        Vector vector2 = new Vector(array4);
        Vector vector3 = new Vector(array5);

        Vector[] vectors1 = {vector1, vector2, vector3};

        Matrix matrix3 = new Matrix(vectors1);

        System.out.println(matrix3);
        System.out.println();

        Matrix matrix4 = new Matrix(3);

        System.out.println(matrix4);

        matrix4.setVectorLine(2, vector1);
        matrix4.setVectorLine(0, vector2);
        System.out.println(matrix4);
        System.out.println();

        Vector vector4 = matrix4.getVectorColumn(0);
        System.out.println(vector4);
        System.out.println();

        Vector vector5 = matrix4.getVectorLine(2);
        System.out.println(vector5);
        System.out.println();

        double[] array6 = {1, 24, 9, 2};
        double[] array7 = {11, 4, 9, 7};
        double[] array8 = {1, 4, 92, 10};
        double[] array9 = {11, 47, 2, 0};

        Vector vector6 = new Vector(array6);
        Vector vector7 = new Vector(array7);
        Vector vector8 = new Vector(array8);
        Vector vector9 = new Vector(array9);

        Vector[] vectors2 = {vector6, vector7, vector8, vector9};
        Vector[] vectors3 = {vector7, vector8, vector9, vector9};
        matrix4 = new Matrix(vectors2);

        System.out.println(matrix4);
        matrix4.transportMatrix();
        System.out.println(matrix4);
        System.out.println();

        matrix4.MatrixMultipleScalar(2);
        System.out.println(matrix4);
        System.out.println();

        System.out.println(matrix4.getMatrixDeterminant());
        System.out.println();

        System.out.println(vector6);
        System.out.println(matrix4);
        Vector vector10 = matrix4.MatrixMultipleVector(vector6);
        System.out.println(vector10);
        System.out.println();

        matrix4.getMatrixSum(matrix4);
        System.out.println(matrix4);
        System.out.println();

        matrix4.getMatrixDifference(matrix4);
        System.out.println(matrix4);
        System.out.println();

        matrix1 = new Matrix(vectors2);
        matrix2 = new Matrix(vectors3);

        System.out.println(matrix1);
        System.out.println(matrix2);

        Matrix matrix5 = Matrix.getDifference(matrix1, matrix2);


        System.out.println(matrix5);
        System.out.println();

        System.out.println(matrix2);

        double[] array10 = {1, 24, 9, 2};
        double[] array11 = {11, 4, 9, 7};
        double[] array12 = {1, 4, 92, 10};
        double[] array13 = {11, 47, 2, 0};


        Vector vector11 = new Vector(array7);
        Vector vector12 = new Vector(array8);
        Vector vector13 = new Vector(array9);
        Vector vector14 = new Vector(array6);

        Vector[] vectors4 = {vector11, vector12, vector13, vector14};

        Matrix matrix6 = new Matrix(vectors4);

        Matrix matrix7 = Matrix.getMultiplication(matrix6,matrix6);
        System.out.println(matrix7);

    }
}
