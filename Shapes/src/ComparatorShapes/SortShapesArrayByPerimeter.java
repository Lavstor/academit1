package ComparatorShapes;

import Shapes.academ.it.school.Shape;

import java.util.Comparator;

public class SortShapesArrayByPerimeter implements Comparator<Shape> {
        public int compare(Shape shape2, Shape shape1) {
            return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
        }
}
