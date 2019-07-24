package Main.shapes.academ.it.school.ru;

import ComparatorShapes.SortByArea;
import Shapes.academ.it.school.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{new Square(12), new Triangle(2,3,4,5,6,7),
        new Rectangle(99, 34), new Circle(3), new Circle(99)};
        Arrays.sort(shapes, new SortByArea());
        System.out.println(Arrays.toString(shapes));
    }
}

