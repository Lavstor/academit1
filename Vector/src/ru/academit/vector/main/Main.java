package ru.academit.vector.main;

import ru.academit.vector.vectors.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(5);
        System.out.println(vector);
        double[] array = {2,3,4,5,6,7};
        double[] array2 = {2,3,4};
        vector.fillArray(array);
        vector.fillArray(array2);
        System.out.println(vector);
        System.out.println(vector.getVectorCopy());
    }
}
