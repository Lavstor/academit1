package ru.shapes.academit.shapes.calsses;

public class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getWidth() {
        return this.side;
    }

    public double getHeight() {
        return this.side;
    }

    public double getArea() {
        return this.side * this.side;
    }

    public double getPerimeter() {
        return this.side * 4;
    }

    public String toString() {
        return "Сторона = " + side + " S = " + getArea() + " P = " + getPerimeter() +
                " Высота = " + getHeight() + " Ширина = " + getWidth() + " Квадрат";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;

        return Double.compare(square.side, side) == 0;
    }
}
