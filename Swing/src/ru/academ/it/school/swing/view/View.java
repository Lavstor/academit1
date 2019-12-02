package ru.academ.it.school.swing.view;

import ru.academ.it.school.swing.classes.Celsius;
import ru.academ.it.school.swing.classes.Fahrenheit;
import ru.academ.it.school.swing.classes.Kelvin;
import ru.academ.it.school.swing.classes.Temperature;
import ru.academ.it.school.swing.controller.Controller;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Objects;

public class View {
    private Controller controller;
    private String[] temperatures = {"Kelvin", "Celsius", "Fahrenheit"};

    public View() {
        this.controller = new Controller();

        SwingUtilities.invokeLater(() -> {
            Image img = Toolkit.getDefaultToolkit().getImage("Swing/FrameImage.jpg");

            JLabel from = new JLabel(" From ");
            JLabel to = new JLabel(" To ");

            JPanel mainPanel = new JPanel(new GridLayout(3, 1));
            JPanel textPanel = new JPanel(new GridLayout(1, 2));
            JPanel panel = new JPanel();

            JLabel answer = new JLabel("= ");

            JButton enter = new JButton("ANSWER");

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
            frame.setBounds((screenSize.width - 360) / 2, (screenSize.height - 280) / 2, 400, 280);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(img);
            frame.setMinimumSize(new Dimension(400, 280));
            frame.add(mainPanel);

            enter.addActionListener(actionEvent -> {
                try {
                    Temperature temperatureFrom = getTemperature(Objects.requireNonNull(convertible.getSelectedItem()).toString());
                    Temperature temperatureTo = getTemperature(Objects.requireNonNull(convertedTo.getSelectedItem()).toString());

                    answer.setText("= " + controller.getAnswer(temperatureFrom, temperatureTo, Double.parseDouble(textField.getText())));
                } catch (NumberFormatException e) {
                    answer.setText("ERROR! NOT RIGHT TYPE!");
                } catch (NullPointerException ignored) {
                }
            });
            UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.RED));
            UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        });
    }

    private Temperature getTemperature(String text) {
        if (text.equals("Celsius")) {
            return new Celsius();
        }
        if (text.equals("Fahrenheit")) {
            return new Fahrenheit();
        }
        if (text.equals("Kelvin")) {
            return new Kelvin();
        }
        return null;
    }
}
