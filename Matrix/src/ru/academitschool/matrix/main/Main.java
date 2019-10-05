package ru.academitschool.matrix.main;

import ru.academit.vector.vectors.Vector;
import ru.academitschool.matrix.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        double[][] array1 = {{2, 4, 9}, {7, 80, 9}, {7, 9, 9}};

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

        Matrix matrix4 = new Matrix(3, 3);

        System.out.println(matrix4);

        matrix4.setRow(2, vector1);
        matrix4.setRow(0, vector2);
        System.out.println(matrix4);
        System.out.println();

        Vector vector4 = matrix4.getColumn(0);
        System.out.println(vector4);
        System.out.println();

        Vector vector5 = matrix4.getRow(2);
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
        matrix4.transpose();
        System.out.println("Транспортируем " + matrix4);
        System.out.println();

        matrix4.multiply(2);
        System.out.println("Умножаем на скаляр " + matrix4);
        System.out.println();

        System.out.println("Определитель " + matrix4.getDeterminant());
        System.out.println();

        System.out.println(vector6);
        System.out.println(matrix4);
        Vector vector10 = matrix4.multiply(vector6);
        System.out.println("Перемножение " + vector10);
        System.out.println();

        matrix4.add(matrix4);
        System.out.println("Суммируем " + matrix4);
        System.out.println();

        matrix4.subtract(matrix4);
        System.out.println("Разность " + matrix4);
        System.out.println();

        matrix1 = new Matrix(vectors2);
        matrix2 = new Matrix(vectors3);

        System.out.println(matrix1);
        System.out.println(matrix2);

        Matrix matrix5 = Matrix.getDifference(matrix1, matrix2);

        System.out.println("Разность " + matrix5);
        System.out.println();

        System.out.println(matrix1);
        System.out.println(matrix2);

        matrix5 = Matrix.getMultiplication(matrix1, matrix2);
        System.out.println("Умножение " + matrix5);
        System.out.println();

        double[][] testArray = {{2, 4, 0}, {-2, 1, 3}, {-1, 0, 1}, {3,4,5}};
        double[] testArray2 = {1, 2 , -1};
        Vector vector = new Vector(testArray2);
        Matrix testMatrix = new Matrix(testArray);

        System.out.println(testMatrix);
        System.out.println(vector);

        System.out.println(testMatrix.multiply(vector));
    }
}