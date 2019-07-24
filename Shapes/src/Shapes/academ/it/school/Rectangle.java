package Shapes.academ.it.school;

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
}
