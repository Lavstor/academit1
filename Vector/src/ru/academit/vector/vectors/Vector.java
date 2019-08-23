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

    public double[] getComponents() {
        return this.components;
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
       StringBuilder line = new StringBuilder();

        for (double component : components) {
            line.append("{").append(component).append("}");
        }

        return line.toString();
    }

    public void vectorCopy(Vector vector) {
        double[] newArray = Arrays.copyOf(vector.components, vector.components.length);

        components = new double[vector.components.length];

        fillArray(newArray);
    }

    public void fillArray(double[] donor) {
        if (donor.length < components.length) {
            Arrays.fill(components, donor.length, components.length, 0.0);
        }
        for (int i = 0; i < donor.length; i++) {
            components[i] = donor[i];
            if (i + 1 == components.length || i + 1 == donor.length) {
                break;
            }
        }
    }

    private void fillArrayToN(int n, double[] donor) {
        if (donor.length < n) {
            Arrays.fill(components, donor.length, components.length, 0.0);
        }
        for (int i = 0; i < donor.length; i++) {
            components[i] = donor[i];
            if (i + 1 == components.length || i + 1 == donor.length) {
                break;
            }
        }
    }

    public void sum(Vector vector) {
        double[] arrayCopy = new double[Math.max(vector.components.length, components.length)];

        if (Math.max(vector.components.length, components.length) == vector.components.length) {
            for (int i = 0; i < components.length; i++) {
                arrayCopy[i] += components[i] + vector.components[i];
            }
        } else {
            for (int i = 0; i < vector.components.length; i++) {
                arrayCopy[i] += vector.components[i] + components[i];
            }
        }
        components = new double[arrayCopy.length];
        components = arrayCopy;
    }

    public void difference(Vector vector) {
        double[] arrayCopy = new double[Math.max(vector.components.length, components.length)];

        if (Math.max(vector.components.length, components.length) == vector.components.length) {
            for (int i = 0; i < components.length; i++) {
                arrayCopy[i] += components[i];
            }
            for (int i = 0; i < arrayCopy.length; i++) {
                arrayCopy[i] -= vector.components[i];
            }
        } else {
            for (int i = 0; i < vector.components.length; i++) {
                arrayCopy[i] -= vector.components[i];
            }
            for (int i = 0; i < components.length; i++) {
                arrayCopy[i] += components[i];
            }
        }
        components = new double[arrayCopy.length];
        components = arrayCopy;
    }

    public void multiplication(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] = components[i] * number;
        }
    }

    public void turn() {
        multiplication(-1);
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
        Vector vector3 = new Vector(Math.max(vector1.components.length, vector2.components.length));
        vector3.vectorCopy(vector1);
        vector3.sum(vector2);

        return vector3;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(Math.max(vector1.components.length, vector2.components.length));
        vector3.vectorCopy(vector1);
        vector3.difference(vector2);

        return vector3;
    }

    public static int getComposition(Vector vector1, Vector vector2) {
        int result = 0;
        int min = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < min; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
