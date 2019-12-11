package ru.academ.it.school.swing.model;

import ru.academ.it.school.swing.classes.Scale;

public class Model {
    public String transfer(Scale from, Scale to, String text) {
        return String.valueOf(to.transferFromCelsius(from.transferToCelsius(Double.parseDouble(text))));
    }
}
