package ru.shapes.academit.calsses;

import ru.shapes.academit.interfaces.Shape;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getWidth() {
        return this.side;
    }

    @Override
    public double getHeight() {
        return this.side;
    }

    @Override
    public double getArea() {
        return this.side * this.side;
    }

    @Override
    public double getPerimeter() {
        return this.side * 4;
    }

    @Override
    public String toString() {
        return  "Квадрат " + "Сторона = " + side + " S = " + getArea() + " P = " + getPerimeter() +
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
        Square square = (Square) o;

        return Double.compare(square.side, side) == 0;
    }
}
