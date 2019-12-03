package ru.academ.it.school.swing.model;

import ru.academ.it.school.swing.classes.Celsius;
import ru.academ.it.school.swing.classes.Fahrenheit;
import ru.academ.it.school.swing.classes.Kelvin;
import ru.academ.it.school.swing.classes.Temperature;

import java.util.Objects;

public class Model {
    public double getAnswer(String from, String to, double answer) {
        Temperature temperatureFrom = getTemperature(Objects.requireNonNull(from));
        Temperature temperatureTo = getTemperature(Objects.requireNonNull(to));

        assert temperatureFrom != null;
        return temperatureFrom.getAnswer(temperatureTo, answer);
    }

    private Temperature getTemperature(String text) {
        if (text.equals("Celsius")) {
            return new Celsius();
        }

        if (text.equals("Fahrenheit")) {
            return new Fahrenheit();
        }

        if (text.equals("Kelvin")) {
            return new Kelvin();
        }

        return null;
    }
}
