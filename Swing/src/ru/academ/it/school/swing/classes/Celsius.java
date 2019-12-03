package ru.academ.it.school.swing.classes;

public class Celsius implements Temperature {

    @Override
    public double getAnswer(Temperature to, double textField) {
        if (to instanceof Fahrenheit) {
            textField = textField * 1.8 + 32;

            return textField;
        }

        if (to instanceof Kelvin) {
            textField = textField + 273.15;

            return textField;
        }

        return textField;
    }
}
