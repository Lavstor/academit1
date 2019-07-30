package ru.shapes.academit.sort;


import ru.shapes.academit.interfaces.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape2, Shape shape1) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}
