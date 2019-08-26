package ru.academit.vector.main;

import ru.academit.vector.vectors.Vector;

public class Main {
    public static void main(String[] args) {


        double[] array = {2, 3, 4, 5, 6, 7};

        double[] array2 = {2, 0, 4};
        double[] array3 = {-20, 5};

        Vector vector1 = new Vector(array);
        Vector vector2 = new Vector(array);
        Vector vector3 = new Vector(array3);

        Vector vector4 = new Vector(vector3);
        System.out.println(vector3);
        System.out.println(vector4);


        System.out.println("Копия "+ vector4);
        System.out.println();

        System.out.println(vector4);
        vector4.doMultiplication(10);
        System.out.println("Умножение "+ vector4);
        System.out.println();

        System.out.println(vector4);
        vector4.turn();
        System.out.println("Разворот "+ vector4);
        System.out.println();

        System.out.println(vector4);
        System.out.println("Размер "+ vector4.getLength());
        System.out.println();

        System.out.println(vector4);
        vector4.setComponent(0,0);
        System.out.println("Поменяли " + vector4);
        System.out.println("Аргумент " + vector4.getComponent(0));
        System.out.println();

        System.out.println(vector1);

        Vector vector6 = new Vector(array2);

        System.out.println(vector6);


        System.out.println("Произведение " + Vector.getComposition(vector1, vector2));
        System.out.println();

        System.out.println(vector1);
        System.out.println(vector3);


        Vector vector7 = Vector.getDifference(vector1,vector3);
        System.out.println("Разность " + vector7);
        System.out.println();

        System.out.println(vector1);
        System.out.println(vector3);


        Vector vector8 = Vector.getSum(vector1,vector3);
        System.out.println("Сумма " + vector8);
        System.out.println();

        System.out.println(vector2);
        System.out.println(vector3);

        vector2.doDifference(vector3);
        System.out.println("Разность "+ vector2);
        System.out.println();

        double[] array5 = {2, 0, 4};
        double[] array6 = {-20, 5, 2, 4, 5, 6};

        System.out.println();

        Vector vector5 = new Vector(array6);

        System.out.println(vector5);
        System.out.println(vector2);

        vector2.doSum(vector5);

        System.out.println("Сумма "+ vector2);
        System.out.println();
    }
}
