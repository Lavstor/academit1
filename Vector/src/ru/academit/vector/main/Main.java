package ru.academit.vector.main;

import ru.academit.vector.vectors.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[] array = {2, 3, 4, 5, 6};
        double[] array2 = {2, 0, 4};
        double[] array3 = {-20, 5};

        Vector vector1 = new Vector(array2);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector vector4 = new Vector(vector3);
        System.out.println(vector2);
        System.out.println(vector1);

        vector2.difference(vector1);

        System.out.println(vector2);

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(array2));
        System.out.println("Копия " + vector4);
        System.out.println();

        System.out.println(vector4);
        vector4.multiplyOnScalar(10);
        System.out.println("Умножение " + vector4);
        System.out.println();

        System.out.println(vector4);
        vector4.turn();
        System.out.println("Разворот " + vector4);
        System.out.println();

        System.out.println(vector4);
        System.out.println("Размер " + vector4.getLength());
        System.out.println();

        System.out.println(vector4);
        vector4.setComponent(0, 0);
        System.out.println("Поменяли " + vector4);
        System.out.println("Аргумент " + vector4.getComponent(0));
        System.out.println();

        System.out.println(vector1);

        Vector vector6 = new Vector(array2);

        System.out.println(vector6);
        System.out.println("Произведение " + Vector.getScalarProduct(vector1, vector2));
        System.out.println();

        System.out.println(vector1);
        System.out.println(vector3);

        Vector vector7 = Vector.getDifference(vector1, vector3);
        System.out.println("Разность " + vector7);
        System.out.println();

        System.out.println(vector1);
        System.out.println(vector3);

        Vector vector8 = Vector.getSum(vector1, vector3);
        System.out.println("Сумма " + vector8);
        System.out.println();

        System.out.println(vector2);
        System.out.println(vector3);

        vector2.difference(vector3);
        System.out.println("Разность " + vector2);
        System.out.println();

        double[] array6 = {-20, 5, 2, 4, 5, 6};

        System.out.println();

        Vector vector5 = new Vector(array6);

        System.out.println(vector5);
        System.out.println(vector2);

        vector2.sum(vector5);

        System.out.println("Сумма " + vector2);
        System.out.println();

        Vector vector9 = new Vector(9, array6);
        System.out.println("Копия до n: " + vector9);
    }
}
