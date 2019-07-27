package ru.shapes.academit.shapes.sort;

import ru.shapes.academit.shapes.calsses.Shape;

import java.util.Comparator;

public class SortShapesArrayByPerimeter implements Comparator<Shape> {
    public int compare(Shape shape2, Shape shape1) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}
