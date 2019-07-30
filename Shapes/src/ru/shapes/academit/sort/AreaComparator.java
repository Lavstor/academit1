package ru.shapes.academit.sort;

import ru.shapes.academit.interfaces.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape2, Shape shape1) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}