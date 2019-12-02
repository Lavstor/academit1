package ru.academ.it.school.swing.controller;

import ru.academ.it.school.swing.classes.Temperature;
import ru.academ.it.school.swing.model.Model;

public class Controller {
    private Model model;

    public Controller() {
        this.model = new Model();
    }

    public double getAnswer(Temperature from, Temperature to, double textField) {
        return model.getAnswer(from, to, textField);
    }
}
