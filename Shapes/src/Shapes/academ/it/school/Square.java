package Shapes.academ.it.school;

public class Square extends Shapes.academ.it.school.Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }
    public double getWidth(){
        return this.side;
    }
    public double getHeight(){
        return this.side;
    }
    public double getArea(){
        return this.side * this.side;
    }
    public double getPerimeter(){
        return this.side * 4;
    }
}
