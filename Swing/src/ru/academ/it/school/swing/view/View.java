package ru.academ.it.school.swing.view;

import javax.swing.*;
import java.awt.*;

public class View {
    private JButton enter;
    private JTextField textField;
    private JLabel answer;
    private JComboBox convertedTo, convertible;

    public View(String title) {
        Image img = Toolkit.getDefaultToolkit().getImage("Swing/Без названия (2).jpg");
        JFrame frame = new JFrame(title);

        JLabel from = new JLabel(" Из ");
        JLabel to = new JLabel(" В ");

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        JPanel panel = new JPanel();

        enter = new JButton("ОТВЕТ");
        textField = new JTextField("0", 25);
        answer = new JLabel("= ");

        String[] temperatures = {"Цельсий", "Фаренгейт", "Кельвины"};
        convertedTo = new JComboBox<>(temperatures);
        convertible = new JComboBox<>(temperatures);

        panel.setLayout(new GridLayout(4, 2));

        textPanel.add(textField);
        textPanel.add(answer);

        panel.add(from);
        panel.add(convertible);
        panel.add(to);
        panel.add(convertedTo);

        mainPanel.add(textPanel);
        mainPanel.add(panel);
        mainPanel.add(enter);

        frame.setSize(360, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(img);
        frame.setMinimumSize(new Dimension(360, 280));

        frame.add(mainPanel);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JLabel getAnswer() {
        return answer;
    }

    public JComboBox getConvertible() {
        return convertible;
    }

    public JComboBox getConvertedTo() {
        return convertedTo;
    }

    public JButton getEnterButton() {
        return enter;
    }
}
