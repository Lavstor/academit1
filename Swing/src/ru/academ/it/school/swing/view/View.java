package ru.academ.it.school.swing.view;

import ru.academ.it.school.swing.interfaces.Observable;
import ru.academ.it.school.swing.interfaces.Observer;
import ru.academ.it.school.swing.model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View implements Observable, Observer {
    private JButton enter;
    private JTextField textField;
    private JLabel answer;
    private JComboBox convertedTo;
    private JComboBox convertible;
    private String result;
    private ArrayList<Observer> observers = new ArrayList<>();
    Model model;

    public View(String title) {
        model = new Model();

        Image img = Toolkit.getDefaultToolkit().getImage("Swing/Картинка фрейма.jpg");

        JLabel from = new JLabel(" Из ");
        JLabel to = new JLabel(" В ");

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        JPanel panel = new JPanel();

        enter = new JButton("ОТВЕТ");
        textField = new JTextField("55", 25);
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

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setBounds((screenSize.width - 360) / 2, (screenSize.height - 280) / 2, 360, 280);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(img);
            frame.setMinimumSize(new Dimension(360, 280));

            frame.add(mainPanel);
        });

        registerObserver(model);

        enter.addActionListener(actionEvent -> observers.get(0).update(getTextField().getText(), String.valueOf(convertible.getSelectedIndex()), String.valueOf(convertedTo.getSelectedIndex())));
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


    public JTextField getTextField() {
        return textField;
    }

    @Override
    public void update(String text, String from, String to) {
        answer.setText(text);
    }
}
