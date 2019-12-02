package ru.academ.it.school.swing.classes;

public class Fahrenheit implements Temperature {

    @Override
    public double getAnswer(Temperature to, double textField) {
        if (to instanceof Celsius) {
            textField = (textField - 32) * 5 / 9;

            return textField;
        }
        if (to instanceof Kelvin) {
            textField = (textField + 459.67) * 5 / 9;

            return textField;
        }
        return textField;
    }
}
