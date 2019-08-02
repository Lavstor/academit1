package ru.shapes.academit.calsses;

import ru.shapes.academit.interfaces.Shape;

import java.util.Objects;

public class Triangle implements Shape {
    private double x1;
    private double y1;

    private double x2;
    private double y2;

    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;

        this.x2 = x2;
        this.y2 = y2;

        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return maxMin(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return maxMin(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - getLength(x1, y1, x2, y2)) *
                (halfPerimeter - getLength(x1, y1, x3, y3)) *
                (halfPerimeter - getLength(x2, y2, x3, y3)));
    }

    @Override
    public double getPerimeter() {
        return getLength(x1, y1, x2, y2) + getLength(x1, y1, x3, y3) + getLength(x2, y2, x3, y3);
    }

    private double maxMin(double y1, double y2, double y3) {
        double max1 = Math.max(y1, y2);
        double max2 = Math.max(max1, y3);

        double min1 = Math.min(y1, y2);
        double min2 = Math.min(min1, y3);

        return max2 - min2;
    }

    private static double getLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public String toString() {
        return "Треугольник " + "x1 = " + x1 + " x2 = " + x2 + " x3 = " + x3 + " y1 = " + y1 + " y2 = " + y2 + " y3 = " + y3 +
                " S = " + getArea() + " P = " + getPerimeter() + " Высота = " + getHeight() + " Ширина = "
                + getWidth();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;

        return Double.compare(triangle.x1, x1) == 0 &&
                Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.x2, x2) == 0 &&
                Double.compare(triangle.y2, y2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 &&
                Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}
