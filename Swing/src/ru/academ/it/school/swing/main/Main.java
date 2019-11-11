package ru.academ.it.school.swing.main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Image img = Toolkit.getDefaultToolkit().getImage("Swing/Без названия (2).jpg");
            JFrame frame = new JFrame("Мой фрейм");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(img);

            JButton button = new JButton("Моя кнопочка1");
            JButton button2 = new JButton("Моя кнопочка2");

            frame.add(button, FlowLayout.LEADING);
           // frame.add(button2);
        });
    }
}
