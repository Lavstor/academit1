package ru.academ.it.school.swing.controller;

import ru.academ.it.school.swing.interfaces.Observable;
import ru.academ.it.school.swing.interfaces.Observer;
import ru.academ.it.school.swing.model.Model;
import ru.academ.it.school.swing.view.View;

import java.util.ArrayList;

public class Controller implements Observer, Observable {
    private View view;
    private Model model;
    private ArrayList<Observer> observers = new ArrayList<>();

    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;

        observers.add(view);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Object::notify);
    }

    @Override
    public void update(String text, String from, String to) {
        view.update(text, from, to);
    }
}
