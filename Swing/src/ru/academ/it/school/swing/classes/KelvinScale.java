package ru.academ.it.school.swing.classes;

public class KelvinScale implements Scale {
    @Override
    public double transfer(Scale to, double value) {
        if (to instanceof CelsiusScale) {
            return value - 273.15;
        }

        if (to instanceof FahrenheitScale) {
            return (value - 273.15) * 1.8 + 32;
        }

        return value;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}
