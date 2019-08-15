package ru.academitschool.matrix.main;

import ru.academit.vector.vectors.Vector;
import ru.academitschool.matrix.matrix.Matrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3,3);
        System.out.print(matrix1);
        Matrix matrix2 = new Matrix(3,0);

        double[][] array1 = {{2,3}, {4,5}};
        double[][] array2 = {{2,4}, {7,9}};

        matrix2.fillMatrix(array1);

        System.out.println(matrix2);

        Matrix matrix3 = new Matrix(3,4);
        System.out.println(matrix2);
        System.out.println(matrix3);
        matrix3.getMatrixCopy(matrix2);
        System.out.println(matrix3);




        double[] array3 = {2, 3, 4, 5, 6, 7};
        double[] array4 = {9, 3, 32, 25, 6, 10};
        double[] array5 = {12, 3, 1, 5, 4, 7};

        Vector vector1 = new Vector(2);
        Vector vector2 = new Vector(4);
        Vector vector3 = new Vector(4);

        vector1.fillArray(array3);
        vector2.fillArray(array4);
        vector3.fillArray(array5);

        System.out.println(matrix2);
        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println(vector3);

        Vector[] vectors = {vector1,vector2,vector3};

        matrix2.fillMatrix(vectors);
        System.out.println(matrix2);
        System.out.println(Arrays.toString(matrix2.getMatrixSize()));



        Vector vector4 = new Vector(1);
        matrix2.setVectorLine(0,vector4);
        System.out.println(matrix2);

        matrix2.fillMatrix(vectors);
        System.out.println(matrix2);

        matrix2.setVectorLine(0,vector1);
        System.out.println(matrix2);

       System.out.println(matrix2.getVectorLine(2));

        System.out.println(matrix2);

        System.out.println(matrix2.getVectorColumn(0));

        matrix2.transportMatrix();
        System.out.println(matrix2);


        System.out.println(vector2);
        matrix2.MatrixMultipleVector(vector2);

        System.out.println(matrix2);
    }
}
