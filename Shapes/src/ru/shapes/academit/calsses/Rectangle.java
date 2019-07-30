package ru.shapes.academit.calsses;

import ru.shapes.academit.interfaces.Shape;

import java.util.Objects;

public class Rectangle implements Shape {
    private double xSide;
    private double ySide;

    public Rectangle(double xSide, double ySide) {
        this.xSide = xSide;
        this.ySide = ySide;
    }

    @Override
    public double getWidth() {
        return Math.min(xSide, ySide);
    }

    @Override
    public double getHeight() {
        return Math.max(xSide, ySide);
    }

    @Override
    public double getArea() {
        return xSide * ySide;
    }

    @Override
    public double getPerimeter() {
        return xSide * 2 + ySide * 2;
    }

    @Override
    public String toString() {
        return "Прямоугольник " + "Сторона 1 = " + xSide + " Сторона 2 = " + ySide + " S = " + getArea() + " P = " + getPerimeter() +
                " Высота = " + getHeight() + " Ширина = " + getWidth();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;

        return Double.compare(rectangle.xSide, xSide) == 0 &&
                Double.compare(rectangle.ySide, ySide) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xSide, ySide);
    }
}
