package ru.shapes.academit.main;

import ru.shapes.academit.calsses.*;
import ru.shapes.academit.interfaces.Shape;
import ru.shapes.academit.sort.AreaComparator;
import ru.shapes.academit.sort.PerimeterComparator;

import java.util.Arrays;

public class Main {
    private static Shape findMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());

        return shapes[0];
    }

    private static Shape findSecondPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[1];
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{new Square(200), new Triangle(2, 3, 7, 5, 6, 10),
                new Rectangle(4, 2), new Circle(3), new Circle(4)};

        findMaxAreaShape(shapes);
        findSecondPerimeterShape(shapes);
        System.out.println(findMaxAreaShape(shapes));
        System.out.println();
        System.out.println(findSecondPerimeterShape(shapes));
    }
}


