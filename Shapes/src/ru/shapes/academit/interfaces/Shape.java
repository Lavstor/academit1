package ru.shapes.academit.interfaces;

public interface Shape {
    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();

    @Override
    String toString();
}
