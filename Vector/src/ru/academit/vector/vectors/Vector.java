package ru.academit.vector.vectors;

public class Vector {
    private int n;
    private double x;
    private double y;
    private double z;

    public Vector(int n) {
        this.n = n;
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vector getVectorCopy(Vector vector) {
        return vector;
    }

    public void fillVector(double[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                this.x = array[i];
            }
            if (i == 1) {
                this.y = array[i];
            }
            if (i == 2) {
                this.z = array[i];
            }
        }
    }

    public void fillVector2(int n, double[] array) {
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                this.x = array[0];
            } else if (i == 1) {
                this.y = array[1];
            } else if (i == 2) {
                this.y = array[2];
            }
        }
    }
}
