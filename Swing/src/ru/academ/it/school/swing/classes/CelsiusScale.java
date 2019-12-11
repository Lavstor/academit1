package ru.academ.it.school.swing.classes;

public class CelsiusScale implements Scale {
    @Override
    public String toString() {
        return "Celsius";
    }

    @Override
    public double transferToCelsius(double value) {
        return value;
    }

    @Override
    public double transferFromCelsius(double value) {
        return value;
    }
}
