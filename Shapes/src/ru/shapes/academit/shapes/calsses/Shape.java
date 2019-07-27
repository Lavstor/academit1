package ru.shapes.academit.shapes.calsses;

import ru.shapes.academit.shapes.interfaces.ShapeInterface;

public abstract class Shape implements ShapeInterface {
    public abstract double getWidth();

    public abstract double getHeight();

    public abstract double getArea();

    public abstract double getPerimeter();

    @Override
    public abstract String toString();
}
