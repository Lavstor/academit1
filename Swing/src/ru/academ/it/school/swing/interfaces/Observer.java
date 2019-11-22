package ru.academ.it.school.swing.interfaces;

import java.awt.event.ActionEvent;

public interface Observer {
    void update (String text, String from, String to);
}
