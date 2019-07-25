package Shapes.academ.it.school;

public class Circle extends Shapes.academ.it.school.Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    public String toString(){
        return "r = " + radius + " S = " + getArea() + " P = " + getPerimeter() + " Высота = " + getHeight() +
                " Ширина = " + getWidth() + " Окружность";
    }
}
