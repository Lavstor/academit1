package ru.academit.vector.vectors;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность должна быть больше 0");
        }
        components = new double[n];
    }

    @Override
    public String toString() {
        return Arrays.toString(components);
    }

    public Vector getVectorCopy() {
        double[] newArray = Arrays.copyOf(components, components.length);
        Vector vectorCopy = new Vector(components.length);
        vectorCopy.fillArray(newArray);

        return vectorCopy;
    }

    public void fillArray(double[] donor) {
        if (donor.length < components.length) {
            Arrays.fill(components, donor.length, components.length, 0.0);
        }
        for (int i = 0; i < donor.length; i++) {
            components[i] = donor[i];
            if (i + 1 == components.length|| i + 1 == donor.length) {
                break;
            }
        }
    }

    public void sum(Vector vector) {
        if (vector.components.length > components.length) {
            double[] arrayCopy = components;

            components = new double[vector.components.length];

            fillArray(arrayCopy);
        }
        for (int i = 0; i < vector.components.length; i++) {
            components[i] = components[i] + vector.components[i];
        }
    }

    public void difference(Vector vector) {
        if (vector.components.length > components.length) {
            double[] arrayCopy = components;

            components = new double[vector.components.length];

            fillArray(arrayCopy);
        }
        for (int i = 0; i < vector.components.length; i++) {
            components[i] = components[i] - vector.components[i];
        }
    }

    public void getMultiplication(int number) {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i] * number;
        }
    }

    public void turn() {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i] * -1;
        }
    }

    public double getLength() {
        double length = 0;

        for (double anArray : components) {
            length += Math.pow(anArray, 2);
        }

        return Math.sqrt(length);
    }

    public void setArgument(int index, double num) {
        if (index >= components.length) {
            return;
        }
        components[index] = num;
    }

    public double getArgument(int index) {
        if (index >= components.length) {
            return 0.0;
        }

        return components[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;

        return components.length == vector.components.length && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(components.length);

        result = 31 * result + Arrays.hashCode(components);
        return result;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int minN = Math.min(vector1.components.length, vector2.components.length);
        int maxN = Math.max(vector1.components.length, vector2.components.length);

        Vector vector3 = new Vector(maxN);

        for (int i = 0; i < maxN; i++) {
            if (i < minN) {
                vector3.components[i] = vector1.components[i] + vector2.components[i];
                continue;
            }
            if (vector1.components.length > vector2.components.length) {
                vector3.components[i] = vector1.components[i];
            } else {
                vector3.components[i] = vector2.components[i];
            }
        }

        return vector3;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int minN = Math.min(vector1.components.length, vector2.components.length);
        int maxN = Math.max(vector1.components.length, vector2.components.length);


        Vector vector3 = new Vector(maxN);

        for (int i = 0; i < maxN; i++) {
            if (i < minN) {
                vector3.components[i] = vector1.components[i] - vector2.components[i];
                continue;
            }
            if (vector1.components.length > vector2.components.length) {
                vector3.components[i] = vector1.components[i] - 0;
            } else {
                vector3.components[i] = -vector2.components[i];
            }
        }

        return vector3;
    }

    public static Vector getVectorComposition(Vector vector1, Vector vector2) {
        int minN = Math.min(vector1.components.length, vector2.components.length);
        int maxN = Math.max(vector1.components.length, vector2.components.length);

        Vector vector3 = new Vector(maxN);

        for (int i = 0; i < minN; i++) {
            vector3.components[i] = vector1.components[i] * vector2.components[i];
        }
        Arrays.fill(vector3.components, minN, maxN, 0.0);

        return vector3;
    }
}
