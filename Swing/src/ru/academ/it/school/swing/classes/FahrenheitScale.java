package ru.academ.it.school.swing.classes;

public class FahrenheitScale implements Scale {
    @Override
    public double transferToCelsius(double value) {
        return (value - 32) * 5 / 9;
    }

    @Override
    public double transferFromCelsius(double value) {
        return value * 9 / 5 + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
