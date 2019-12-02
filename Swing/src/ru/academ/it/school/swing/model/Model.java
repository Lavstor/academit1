package ru.academ.it.school.swing.model;

import ru.academ.it.school.swing.classes.Temperature;

public class Model {
    public double getAnswer(Temperature from, Temperature to, double answer) {
        return from.getAnswer(to, answer);
    }
}
