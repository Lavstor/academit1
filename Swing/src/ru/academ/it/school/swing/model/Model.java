package ru.academ.it.school.swing.model;

import ru.academ.it.school.swing.classes.Scale;

public class Model {
    public double convert(Scale from, Scale to, String text) {
        return to.convertFromCelsius(from.convertToCelsius(Double.parseDouble(text)));
    }
}
