package ru.academ.it.school.swing.controller;

import ru.academ.it.school.swing.model.Model;
import ru.academ.it.school.swing.view.View;

public class Controller {
    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;

        initController();
        initView();
        getAnswer();
    }

    private void initView() {
        view.getAnswer().setText(model.getAnswer(view.getTextField().getText()));
    }

    private void initController() {
        view.getConvertedTo().addActionListener(actionEvent -> model.setTo(view.getConvertedTo().getSelectedIndex()));
        view.getConvertible().addActionListener(actionEvent -> model.setFrom(view.getConvertible().getSelectedIndex()));
    }

    private void getAnswer() {
        view.getEnterButton().addActionListener(actionEvent -> view.getAnswer().setText(model.getAnswer(view.getTextField().getText())));
    }
}
