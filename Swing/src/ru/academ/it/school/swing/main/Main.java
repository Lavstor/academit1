package ru.academ.it.school.swing.main;

import ru.academ.it.school.swing.classes.CelsiusScale;
import ru.academ.it.school.swing.classes.FahrenheitScale;
import ru.academ.it.school.swing.classes.KelvinScale;
import ru.academ.it.school.swing.classes.Scale;
import ru.academ.it.school.swing.model.Model;
import ru.academ.it.school.swing.view.View;

public class Main {
    public static void main(String[] args) {
        new View(new Model(), new Scale[]{new KelvinScale(), new CelsiusScale(), new FahrenheitScale()});
    }
}

