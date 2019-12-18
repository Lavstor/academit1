package ru.academ.it.school.swing.view;

import ru.academ.it.school.swing.classes.Scale;
import ru.academ.it.school.swing.model.Model;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Objects;

public class View {
    public View(Model model, Scale[] scales) {
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

            JComboBox<Scale> convertedTo = new JComboBox<>(scales);
            JComboBox<Scale> convertible = new JComboBox<>(scales);

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
            frame.setBounds((screenSize.width - 400) / 2, (screenSize.height - 280) / 2, 400, 280);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(img);
            frame.setMinimumSize(new Dimension(400, 280));
            frame.add(mainPanel);

            enter.addActionListener(actionEvent -> {

                Scale convertibleScale = (Scale) convertible.getSelectedItem();
                Scale convertedScale = (Scale) convertedTo.getSelectedItem();

                if (convertedScale != null && convertibleScale != null) {
                    try {
                        double scaleResult = model.convert((Scale) Objects.requireNonNull(convertible.getSelectedItem()),
                                (Scale) Objects.requireNonNull(convertedTo.getSelectedItem()), textField.getText());

                        answer.setText("= " + scaleResult);
                    } catch (NumberFormatException e) {
                        answer.setText("Error! Invalid format!");
                    }
                } else {
                    answer.setText("Error! Invalid scale!");
                }
            });
            UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        });
    }
}
