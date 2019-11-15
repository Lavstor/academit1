package ru.academ.it.school.swing.main;

import ru.academ.it.school.swing.controller.Controller;
import ru.academ.it.school.swing.model.Model;
import ru.academ.it.school.swing.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("проверка");
        new Controller(model, view);
    }
}
