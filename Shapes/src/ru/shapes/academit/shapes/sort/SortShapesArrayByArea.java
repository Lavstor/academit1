package ru.shapes.academit.shapes.sort;

import ru.shapes.academit.shapes.calsses.Shape;

import java.util.Comparator;

public class SortShapesArrayByArea implements Comparator<Shape> {
    public int compare(Shape shape2, Shape shape1) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}