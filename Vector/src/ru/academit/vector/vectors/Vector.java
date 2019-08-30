package ru.academit.vector.vectors;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new ArrayIndexOutOfBoundsException("Размерность должна быть больше 0");
        }

        components = new double[n];
    }

    public Vector(double[] donor) {
        if (donor.length <= 0) {
            throw new ArrayIndexOutOfBoundsException("Размерность должна быть больше 0");
        }

        components = Arrays.copyOf(donor, donor.length);
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(int n, double[] donor) {
        if (donor.length >= n) {
            Arrays.fill(components, donor.length, components.length, 0.0);
        }

        components = Arrays.copyOf(donor, n);
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


    public void doSum(Vector vector) {
        if (this.components.length < vector.components.length) {
         new Vector(vector.components.length, this.components);
        }
        double[] newArray = Arrays.copyOf(components, vector.components.length);

        for(int i = 0; i < newArray.length; i++){
            newArray[i] += vector.components[i];
        }

        this.components = Arrays.copyOf(newArray, newArray.length);
    }

    public void doDifference(Vector vector) {
        if (this.components.length < vector.components.length) {
            new Vector(vector.components.length, this.components);
        }
        double[] newArray = Arrays.copyOf(components, vector.components.length);

        for(int i = 0; i < newArray.length; i++){
            newArray[i] -= vector.components[i];
        }

        this.components = Arrays.copyOf(newArray, newArray.length);
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

        for (double component : components) {
            length += Math.pow(component, 2);
        }

        return Math.sqrt(length);
    }

    public void setComponent(int index, double number) {
        if (index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс больше длинны массива");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс меньше 0");
        }

        components[index] = number;
    }

    public double getComponent(int index) {
        if (index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс больше длинны массива");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс меньше 0");
        }

        return components[index];
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

    public static double getMultiplication(Vector vector1, Vector vector2) {
        int result = 0;

        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minLength; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
