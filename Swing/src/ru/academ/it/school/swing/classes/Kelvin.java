package ru.academ.it.school.swing.classes;

public class Kelvin implements Temperature {

    @Override
    public double getAnswer(Temperature to, double textField) {
        if (to instanceof Celsius) {
            textField = textField - 273.15;

            return textField;
        }

        if (to instanceof Fahrenheit) {
            textField = (textField - 273.15) * 1.8 + 32;

            return textField;
        }

        return textField;
    }
}
