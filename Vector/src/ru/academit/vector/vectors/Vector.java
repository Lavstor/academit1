package ru.academit.vector.vectors;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] array;

    public Vector(int n) {
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
            Arrays.fill(this.array, this.n, array.length, 0.0);
        }
        for (int i = 0; i < donor.length; i++) {
            this.array[i] = donor[i];
            if (i + 1 == this.n || i + 1 == donor.length) {
                break;
            }
        }
    }
}
