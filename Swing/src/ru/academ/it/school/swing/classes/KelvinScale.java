package ru.academ.it.school.swing.classes;

public class KelvinScale implements Scale {
    @Override
    public double transferToCelsius(double value) {
        return value - 273.15;
    }

    @Override
    public double transferFromCelsius(double value) {
        return value + 273.15;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}
