package ru.academ.it.school.swing.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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

            JButton exit = new JButton("Выход");
            JTextField textField = new JTextField("0");
            JLabel label2 = new JLabel();
            //textField.setVisible(false);

            AtomicInteger temp = new AtomicInteger();

            Consumer<String> answer = o -> label2.setText("= " + o);

            exit.addActionListener(actionEvent -> frame.dispose());


            JPanel pannel = new JPanel();

            String[] temperatures = {"Цельсий", "Фаренгейт", "Кельвины"};
            JComboBox temperatureComboBox = new JComboBox<>(temperatures);

            temperatureComboBox.addActionListener(actionEvent -> {
                if (temperatureComboBox.getSelectedIndex() == 0) {
                    try {
                        double text = Double.parseDouble(textField.getText());

                        if (temp.get() == 0) {
                            answer.accept(textField.getText());
                        }

                        if (temp.get() == 1) {
                            answer.accept(String.valueOf((text - 32) * 1.8));
                        }
                        if (temp.get() == 2) {
                            answer.accept(String.valueOf(text - 273.15));
                        }
                    } catch (NumberFormatException e) {
                        answer.accept("Ошибка, неправильный тип данных!");
                    }
                }
                if (temperatureComboBox.getSelectedIndex() == 1) {
                    try {
                        double text = Double.parseDouble(textField.getText());

                        if (temp.get() == 0) {
                            answer.accept(String.valueOf(text * 1.8 + 32));
                        }

                        if (temp.get() == 1) {
                            answer.accept(textField.getText());
                        }

                        if (temp.get() == 2) {
                            answer.accept(String.valueOf((text - 273.15) * 1.8 + 32));
                        }
                    } catch (NumberFormatException e) {
                        answer.accept("Ошибка, неправильный тип данных!");
                    }
                }
                if (temperatureComboBox.getSelectedIndex() == 2) {
                    try {
                        double text = Double.parseDouble(textField.getText());

                        if (temp.get() == 0) {
                            answer.accept(String.valueOf(text + 273.15));
                        }

                        if (temp.get() == 1) {
                            answer.accept((String.valueOf((text + 459.67) * 5 / 9)));
                        }

                        if (temp.get() == 2) {
                            answer.accept(textField.getText());
                        }
                    } catch (NumberFormatException e) {
                        answer.accept("Ошибка, неправильный тип данных!");
                    }
                }
            });

            JComboBox temperatureComboBox2 = new JComboBox<>(temperatures);

            temperatureComboBox2.addActionListener(actionEvent -> {
                if (temperatureComboBox.getSelectedIndex() == 0) {
                    temp.set(0);
                }
                if (temperatureComboBox.getSelectedIndex() == 1) {
                    temp.set(1);
                }
                if (temperatureComboBox.getSelectedIndex() == 2) {
                    temp.set(2);
                }
            });



            pannel.add(textField);
            pannel.add(label2);
            pannel.add(temperatureComboBox);
            pannel.add(temperatureComboBox2);

            pannel.add(exit);

            frame.add(pannel);
        });
    }
}
