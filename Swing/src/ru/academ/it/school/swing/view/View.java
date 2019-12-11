package ru.academ.it.school.swing.view;

import ru.academ.it.school.swing.classes.CelsiusScale;
import ru.academ.it.school.swing.classes.FahrenheitScale;
import ru.academ.it.school.swing.classes.KelvinScale;
import ru.academ.it.school.swing.classes.Scale;
import ru.academ.it.school.swing.model.Model;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Objects;

public class View {
    private Model model = new Model();
    private Scale[] scales = {new KelvinScale(), new CelsiusScale(), new FahrenheitScale()};

    public View() {
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

            JComboBox<Scale> convertedTo = new JComboBox<>(this.scales);
            JComboBox<Scale> convertible = new JComboBox<>(this.scales);

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
                    answer.setText("= " + model.transfer((Scale) Objects.requireNonNull(convertible.getSelectedItem()),
                            (Scale) Objects.requireNonNull(convertedTo.getSelectedItem()), textField.getText()));
                } catch (NullPointerException ignored) {
                } catch (NumberFormatException e) {
                    answer.setText("Error! Invalid format!");
                }
            });

            UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        });
    }
}
