package Shapes.academ.it.school;

import java.util.Objects;

public class Rectangle extends Shapes.academ.it.school.Shape {
    private double xSide;
    private double ySide;

    public Rectangle(double xSide, double ySide) {
        this.xSide = xSide;
        this.ySide = ySide;
    }

    public double getWidth() {
        return Math.min(xSide, ySide);
    }

    public double getHeight() {
        return Math.max(xSide, ySide);
    }

    public double getArea() {
        return xSide * ySide;
    }

    public double getPerimeter() {
        return xSide * 2 + ySide * 2;
    }

    public String toString() {
        return "Сторона 1 = " + xSide + " Сторона 2 = " + ySide + " S = " + getArea() + " P = " + getPerimeter() +
                " Высота = " + getHeight() + " Ширина = " + getWidth() + "Прямоугольник";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;

        return Double.compare(rectangle.xSide, xSide) == 0 &&
                Double.compare(rectangle.ySide, ySide) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xSide, ySide);
    }
}
