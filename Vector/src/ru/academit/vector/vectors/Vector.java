package ru.academit.vector.vectors;

import java.util.Arrays;

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
        if (n <= 0) {
            throw new IllegalArgumentException("Аргумент меньше, либо равен 0");
        }

        components = Arrays.copyOf(donor, n);
    }

    public Vector(int n, Vector vector) {
        if (n <= 0) {
            throw new IllegalArgumentException("Аргумент меньше, либо равен 0");
        }

        components = Arrays.copyOf(vector.components, n);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Vector vector = (Vector) object;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
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
    
    public void sum(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < components.length; i++) {
            if (i < vector.components.length) {
                components[i] += vector.components[i];
            }
        }
    }

    public void difference(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }
        for (int i = 0; i < components.length; i++) {
            if (i < vector.components.length) {
                components[i] -= vector.components[i];
            }
        }
    }

    public void multiplyOnScalar(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void turn() {
        multiplyOnScalar(-1);
    }

    public double getLength() {
        double length = 0;

        for (double component : components) {
            length += Math.pow(component, 2);
        }

        return Math.sqrt(length);
    }

    public void setComponent(int index, double number) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть равен, либо меньше длины массива");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше 0");
        }

        components[index] = number;
    }

    public double getComponent(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть равен, либо меньше длины массива");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше 0");
        }

        return components[index];
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        vector3.sum(vector2);

        return vector3;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        vector3.difference(vector2);

        return vector3;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int result = 0;

        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minLength; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
