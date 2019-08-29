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

    public Vector(double[] donor) {
        if (donor.length <= 0) {
            throw new IllegalArgumentException("Размерность должна быть больше 0");
        }
        components = Arrays.copyOf(donor, donor.length);
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(int n, double[] donor) {
        if (donor.length < n) {
            Arrays.fill(components, donor.length, components.length, 0.0);
        }
        components = Arrays.copyOf(donor, n);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();

        line.append("{");

        for (int i = 0; i < components.length; i++) {
            line.append(components[i]);
            if (i + 1 != components.length) {
                line.append(", ");
            }
        }
        line.append("}");

        return line.toString();
    }


    public void doSum(Vector vector) {
        if (components.length < vector.components.length) {
            this. (vector.components.length, components);
        }
        double[] newArray = Arrays.copyOf(components, vector.components.length);
    }

    public void doDifference(Vector vector) {
        if (components.length < vector.components.length) {
            double[] arrayCopy = Arrays.copyOf(vector.components, vector.components.length);

            for (int i = 0; i < components.length; i++) {
                arrayCopy[i] = vector.components[i] - arrayCopy[i];
            }
            for (int i = components.length; i < vector.components.length; i++) {
                arrayCopy[i] *= -1;
            }
            components = arrayCopy;
        } else {
            for (int i = 0; i < vector.components.length; i++) {
                components[i] -= vector.components[i];
            }
        }
    }

    public void doMultiplication(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void turn() {
        doMultiplication(-1);
    }

    public double getLength() {
        double length = 0;

        for (double anArray : components) {
            length += Math.pow(anArray, 2);
        }

        return Math.sqrt(length);
    }

    public void setComponent(int index, double num) {
        if (index >= components.length) {
            throw new IllegalArgumentException("Индекс больше длинны массива");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс меньше 0");
        }

        components[index] = num;
    }

    public double getComponent(int index) {
        if (index >= components.length) {
            throw new IllegalArgumentException("Индекс больше длинны массива");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс меньше 0");
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

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(components.length);
        result = 31 * result + Arrays.hashCode(components);

        return result;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        vector3.doSum(vector2);

        return vector3;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        vector3.doDifference(vector2);

        return vector3;
    }

    public static int getComposition(Vector vector1, Vector vector2) {
        int result = 0;

        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minLength; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
