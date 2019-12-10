package ru.academ.it.school.swing.classes;

public class FahrenheitScale implements Scale {

    @Override
    public double transfer(Scale to, double value) {
        if (to instanceof CelsiusScale) {
            return (value - 32) * 5 / 9;
        }

        if (to instanceof KelvinScale) {
            return (value + 459.67) * 5 / 9;
        }

        return value;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
