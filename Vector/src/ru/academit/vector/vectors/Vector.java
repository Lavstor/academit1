package ru.academit.vector.vectors;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int n;
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность должна быть больше 0");
        }
        this.n = n;
        array = new double[n];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public Vector getVectorCopy() {
        double[] newArray = Arrays.copyOf(this.array, this.n);
        Vector vectorCopy = new Vector(this.n);
        vectorCopy.fillArray(newArray);

        return vectorCopy;
    }

    public void fillArray(double[] donor) {
        if (donor.length < this.n) {
            Arrays.fill(this.array, donor.length, this.n, 0.0);
        }
        for (int i = 0; i < donor.length; i++) {
            this.array[i] = donor[i];
            if (i + 1 == this.n || i + 1 == donor.length) {
                break;
            }
        }
    }

    public void getSum(Vector vector) {
        if (vector.n > this.n) {
            double[] arrayCopy = this.array;
            this.n = vector.n;
            this.array = new double[vector.n];

            fillArray(arrayCopy);
        }
        for (int i = 0; i < vector.n; i++) {
            this.array[i] = this.array[i] + vector.array[i];
        }
    }

    public void getDifference(Vector vector) {
        if (vector.n > this.n) {
            double[] arrayCopy = this.array;

            this.n = vector.n;

            this.array = new double[vector.n];

            fillArray(arrayCopy);
        }
        for (int i = 0; i < vector.n; i++) {
            this.array[i] = this.array[i] - vector.array[i];
        }
    }

    public void getMultiplication(int number) {
        for (int i = 0; i < this.n; i++) {
            this.array[i] = this.array[i] * number;
        }
    }

    public void getUturn() {
        for (int i = 0; i < this.n; i++) {
            this.array[i] = this.array[i] * -1;
        }
    }

    public double getLength() {
        double length = 0;

        for (int i = 0; i < this.n; i++) {
            length += Math.pow(this.array[i], 2);
        }
        return Math.sqrt(length);
    }

    public void setArgument(int index, double num) {
        if (index >= this.n) {
            return;
        }
        array[index] = num;
    }

    public double getArgument(int index) {
        if (index >= this.n) {
            return 0.0;
        }
        return array[index];
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

        return n == vector.n && Arrays.equals(array, vector.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(n);

        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    public static Vector getVectorSum(Vector vector1, Vector vector2) {
        int minN = Math.min(vector1.n, vector2.n);
        int maxN = Math.max(vector1.n, vector2.n);

        Vector vector3 = new Vector(maxN);

        for (int i = 0; i < maxN; i++) {
            if (i < minN) {
                vector3.array[i] = vector1.array[i] + vector2.array[i];
                continue;
            }
            if (vector1.array.length > vector2.array.length) {
                vector3.array[i] = vector1.array[i];
            } else {
                vector3.array[i] = vector2.array[i];
            }
        }
        return vector3;
    }

    public static Vector getVectorDifference(Vector vector1, Vector vector2) {

        int minN = Math.min(vector1.n, vector2.n);
        int maxN = Math.max(vector1.n, vector2.n);


        Vector vector3 = new Vector(maxN);

        for (int i = 0; i < maxN; i++) {
            if (i < minN) {
                vector3.array[i] = vector1.array[i] - vector2.array[i];
                continue;
            }
            if (vector1.array.length > vector2.array.length) {
                vector3.array[i] = vector1.array[i] - 0;
            } else {
                vector3.array[i] = -vector2.array[i];
            }
        }
        return vector3;
    }

    public static Vector getVectorComposition(Vector vector1, Vector vector2) {
        int minN = Math.min(vector1.n, vector2.n);
        int maxN = Math.max(vector1.n, vector2.n);

        Vector vector3 = new Vector(maxN);

        for (int i = 0; i < minN; i++) {
            vector3.array[i] = vector1.array[i] * vector2.array[i];
        }
        Arrays.fill(vector3.array, minN, maxN, 0.0);

        return vector3;
    }
}
