package ru.academ.it.school.swing.view;

import ru.academ.it.school.swing.controller.Controller;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Objects;

public class View {
    private Controller controller;
    private String[] temperatures;

    public View(String[] temperatures) {
        this.temperatures = temperatures;

        SwingUtilities.invokeLater(() -> {
            controller = new Controller();

            Image img = Toolkit.getDefaultToolkit().getImage("Swing/Картинка фрейма.jpg");

            JLabel from = new JLabel(" Из ");
            JLabel to = new JLabel(" В ");

            JPanel mainPanel = new JPanel(new GridLayout(3, 1));
            JPanel textPanel = new JPanel(new GridLayout(1, 2));
            JPanel panel = new JPanel();

            JLabel answer = new JLabel("= ");

            JButton enter = new JButton("ОТВЕТ");

            JTextField textField = new JTextField("55", 25);

            JComboBox convertedTo = new JComboBox<>(this.temperatures);
            JComboBox convertible = new JComboBox<>(this.temperatures);

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

            JFrame frame = new JFrame("Temperature converter");

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setBounds((screenSize.width - 360) / 2, (screenSize.height - 280) / 2, 360, 280);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(img);
            frame.setMinimumSize(new Dimension(360, 280));
            frame.add(mainPanel);

            enter.addActionListener(actionEvent -> {
                try {
                    answer.setText("= " + controller.getAnswer(Objects.requireNonNull(convertible.getSelectedItem()).toString(),
                            Objects.requireNonNull(convertedTo.getSelectedItem()).toString(), Double.parseDouble(textField.getText())));

                } catch (NumberFormatException e) {

                    textField.setText("Ошибка! Неверный тип данных!");
                }
            });
            UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
            UIManager.put("Button.select", Color.red);
            UIManager.put("OptionPane.massage", new ColorUIResource(Color.RED));
            UIManager.put("Label.foreground", new ColorUIResource(Color.RED));
        });
    }
}
