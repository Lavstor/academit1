package ComparatorShapes;

import Shapes.academ.it.school.Shape;

import java.util.Comparator;

public
    class SortByArea implements Comparator<Shape> {
        public int compare(Shape shape1, Shape shape2) {
            return Double.compare(shape1.getArea(), shape2.getArea());
        }
    }

