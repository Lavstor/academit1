package ru.academ.it.school.swing.classes;

public class CelsiusScale implements Scale {
    @Override
    public double convertToCelsius(double value) {
        return value;
    }

    @Override
    public double convertFromCelsius(double value) {
        return value;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}
