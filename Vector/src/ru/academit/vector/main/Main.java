package ru.academit.vector.main;

import ru.academit.vector.vectors.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(2);
        Vector vector2 = new Vector(5);
        Vector vector3 = new Vector(2);

        double[] array = {2, 3, 4, 5, 6, 7};

        double[] array2 = {2, 0, 4};
        double[] array3 = {-20, 5};


        vector1.fillArray(array);
        vector2.fillArray(array);
        vector3.fillArray(array3);

        System.out.println(vector1);
        System.out.println(vector2);
        vector1.difference(vector2);
        System.out.println("Разность "+ vector1);
        System.out.println();

        System.out.println(vector1);
        System.out.println(vector2);
        vector1.sum(vector2);
        System.out.println("Сумма "+ vector1);
        System.out.println();

        System.out.println(vector1);
        System.out.println(vector2);
        vector1.sum(vector2);
        System.out.println("Сумма "+ vector1);
        System.out.println();

        Vector vector4 = vector3.getVectorCopy();

        System.out.println(vector3);
        System.out.println("Копия "+ vector4);
        System.out.println();

        System.out.println(vector4);
        vector4.getMultiplication(10);
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
        vector4.setArgument(0,0);
        System.out.println("Поменяли " + vector4);
        System.out.println("Аргумент " + vector4.getArgument(0));
        System.out.println();

        System.out.println(vector4);
        vector4.setArgument(0,0);
        System.out.println("Поменяли " + vector4);
        System.out.println("Аргумент " + vector4.getArgument(0));
        System.out.println();

        System.out.println(vector1);

        Vector vector6 = new Vector(3);
        vector6.fillArray(array2);
        System.out.println(vector6);

        Vector vector5 = Vector.getVectorComposition(vector1,vector6);
        System.out.println(vector5);
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
       // Vector vector8 = new Vector(0);
    }
}
