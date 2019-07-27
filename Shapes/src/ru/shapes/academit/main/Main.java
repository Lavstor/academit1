package ru.shapes.academit.main;

import ru.shapes.academit.calsses.*;
import ru.shapes.academit.sort.SortShapesArrayByArea;
import ru.shapes.academit.sort.SortShapesArrayByPerimeter;

import java.util.Arrays;

public class Main {
    private static void findMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, new SortShapesArrayByArea());
        System.out.println(shapes[0].toString());
    }

    private static void findSecondPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, new SortShapesArrayByPerimeter());
        System.out.println(shapes[1].toString());
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{new Square(2), new Triangle(2, 3, 7, 5, 6, 10),
                new Rectangle(4, 2), new Circle(3), new Circle(4)};

        findMaxAreaShape(shapes);
        System.out.println();
        findSecondPerimeterShape(shapes);
    }
}


