package ru.academ.it.school.swing.main;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {

        }

        SwingUtilities.invokeLater(() -> {
            Image img = Toolkit.getDefaultToolkit().getImage("Swing/Без названия (2).jpg");
            JFrame frame = new JFrame("Мой фрейм");
            frame.setSize(310, 210);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(img);

            JLabel label1 = new JLabel("");
            JButton celsius = new JButton("Цельсий");
            JButton fahrenheit = new JButton("Фаренгейт");
            JButton kelvin = new JButton("Кельвин");
            JButton toCelsius = new JButton("Цельсий");
            JButton toFahrenheit = new JButton("Фаренгейт");
            JButton toKelvin = new JButton("Кельвин");
            JButton[] options = {celsius, fahrenheit, kelvin};

            JButton exit = new JButton("Выход");
            JTextField textField = new JTextField(15);
            JLabel label2 = new JLabel();
            //textField.setVisible(false);

            AtomicInteger temp = new AtomicInteger();
            celsius.addActionListener(actionEvent -> {
                label1.setText("Цельсии: ");
                temp.set(0);
            });

            fahrenheit.addActionListener(actionEvent -> {
                label1.setText("Фаренгейты: ");
                temp.set(1);
            });

            kelvin.addActionListener(actionEvent -> {
                label1.setText("Кельвины: ");
                temp.set(2);
            });

            toCelsius.addActionListener(actionEvent -> {
                try {
                    double text = Double.parseDouble(textField.getText());

                    if (temp.get() == 0) {
                        label2.setText(textField.getText());
                    }

                    if (temp.get() == 1) {
                        label2.setText(String.valueOf((text - 32) * 1.8));
                    }
                    if (temp.get() == 2) {
                        label2.setText(String.valueOf(text - 273.15));
                    }
                } catch (NumberFormatException e) {
                    textField.setText("Нужно ввести число");
                }
            });

            toFahrenheit.addActionListener(actionEvent -> {
                try {
                    double text = Double.parseDouble(textField.getText());

                    if (temp.get() == 0) {
                        label2.setText(String.valueOf(text * 1.8 + 32));
                    }

                    if (temp.get() == 1) {
                        label2.setText(textField.getText());
                    }

                    if (temp.get() == 2) {
                        label2.setText(String.valueOf((text - 273.15) * 1.8 + 32));
                    }
                } catch (NumberFormatException e) {
                    textField.setText("Нужно ввести число");
                }
            });

            toKelvin.addActionListener(actionEvent -> {
                try {
                    double text = Double.parseDouble(textField.getText());

                    if (temp.get() == 0) {
                        label2.setText(String.valueOf(text + 273.15));
                    }

                    if (temp.get() == 1) {
                        label2.setText((String.valueOf((text + 459.67) * 5 / 9)));
                    }

                    if (temp.get() == 2) {
                        label2.setText(textField.getText());
                    }

                } catch (NumberFormatException e) {
                    textField.setText("Нужно ввести число");
                }
            });

            exit.addActionListener(actionEvent -> frame.dispose());


            JPanel pannel = new JPanel();

          /*  pannel.add(celsius);
            pannel.add(fahrenheit);
            pannel.add(kelvin);*/

            JList<JButton> listJ = new JList<>(options);

            listJ.addListSelectionListener(listSelectionEvent -> {
             System.out.println(listSelectionEvent.toString());
            });
            pannel.add(listJ);

            pannel.add(label1);
            pannel.add(textField);
            pannel.add(label2);
            pannel.add(toCelsius);
            pannel.add(toFahrenheit);
            pannel.add(toKelvin);
            pannel.add(exit);

            frame.add(pannel);

        });
    }
}
