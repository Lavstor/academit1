package ru.academ.it.school.swing.controller;

import ru.academ.it.school.swing.model.Model;

public class Controller {
    private Model model;

    public Controller() {
        this.model = new Model();
    }

    public double getAnswer(String from, String to, double textField) {
        return model.getAnswer(from, to, textField);
    }
}
