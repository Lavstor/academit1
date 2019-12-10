package ru.academ.it.school.swing.classes;

public class CelsiusScale implements Scale {

    @Override
    public double transfer(Scale to, double value) {
        if (to instanceof FahrenheitScale) {
            return value * 1.8 + 32;
        }

        if (to instanceof KelvinScale) {
            return value + 273.15;
        }

        return value;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}
