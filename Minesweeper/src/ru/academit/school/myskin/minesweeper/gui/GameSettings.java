package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

class GameSettings extends JPanel {
    final private Player player;

    final private JTextField heightField;
    final private JTextField weightField;
    final private JTextField mines;

    final private JRadioButton easy;
    final private JRadioButton normal;
    final private JRadioButton high;
    final private JPanel customPane;
    final private JPanel defaultPane;

    private static JButton okButton;
    private static JButton defaultButton;
    private static JButton customButton;
    private static JButton cancel;
    private static JButton backToBattlefield;

    private static List<JButton> buttons = new LinkedList<>();

    GameSettings(Player player) {
        this.player = player;
        weightField = new JTextField(3);
        heightField = new JTextField(3);
        mines = new JTextField(2);

        easy = new JRadioButton("Easy", false);
        normal = new JRadioButton("Normal", true);
        high = new JRadioButton("High", false);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(easy);
        radioGroup.add(normal);
        radioGroup.add(high);

        JLabel weight = new JLabel("Enter weight: ");
        weight.setLabelFor(weightField);

        JLabel height = new JLabel("Enter height: ");
        height.setLabelFor(heightField);

        JLabel mines = new JLabel("Enter mines count: ");
        mines.setLabelFor(this.mines);

        JComponent buttonPane = createButtonPanel();

        customPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        defaultPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        defaultPane.add(easy);
        defaultPane.add(normal);
        defaultPane.add(high);

        customPane.add(height);
        customPane.add(heightField);

        customPane.add(weight);
        customPane.add(weightField);

        customPane.add(mines);
        customPane.add(this.mines);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        add(customPane);

        c.gridy = 1;
        c.insets = new Insets(25, 5, 5, 5);

        add(defaultPane);

        customPane.setVisible(false);

        add(buttonPane);
    }

    private JComponent createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 15, 5, 5);
        constraints.weighty = 1;

        constraints.gridx = 0;

        panel.add(customButton, constraints);
        panel.add(defaultButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.insets = new Insets(5, 5, 5, 5);

        panel.add(okButton, constraints);

        constraints.gridx = 2;

        defaultButton.setVisible(false);
        panel.add(cancel, constraints);
        constraints.gridx = 3;
        panel.add(backToBattlefield, constraints);

        return panel;
    }

    JPanel createMap() {
        if (defaultPane.isVisible()) {
            if (easy.isSelected()) {
                return new BattleField(9, 9, 10, player);
            }
            if (normal.isSelected()) {
                return new BattleField(15, 15, 50, player);
            }
            if (high.isSelected()) {
                return new BattleField(20, 20, 85, player);
            }
        } else {
            try {
                int weight = Integer.parseInt(weightField.getText());
                int height = Integer.parseInt(heightField.getText());
                int mines = Integer.parseInt(this.mines.getText());

                if (weight > 0 && weight < 35 && height > 0 && height < 35 && mines > 0 && mines <= (weight * height) * 0.75) {
                    return new BattleField(Integer.parseInt(weightField.getText()), Integer.parseInt(heightField.getText()),
                            Integer.parseInt(this.mines.getText()), player);
                } else {
                    JOptionPane.showMessageDialog(this, "Error! Wrong options!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException o) {
                JOptionPane.showMessageDialog(this, "Error! Wrong options!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    void customSetup() {
        defaultPane.setVisible(false);
        customPane.setVisible(true);

        defaultButton.setVisible(true);
        customButton.setVisible(false);
    }

    void defaultSetup() {
        defaultPane.setVisible(true);
        customPane.setVisible(false);

        customButton.setVisible(true);
        defaultButton.setVisible(false);
    }

    static List<JButton> getButtons() {
        return buttons;
    }

    static void createButtons() {
        okButton = new JButton("OK");
        cancel = new JButton("CANCEL");
        customButton = new JButton("CUSTOM");
        defaultButton = new JButton("DEFAULT");
        backToBattlefield = new JButton("BACK");

        backToBattlefield.setVisible(false);

        buttons.add(okButton);
        buttons.add(cancel);
        buttons.add(customButton);
        buttons.add(defaultButton);
        buttons.add(backToBattlefield);

        okButton.setActionCommand("CREATE BATTLEFIELD");
        customButton.setActionCommand("CUSTOM");
        defaultButton.setActionCommand("DEFAULT");
        cancel.setActionCommand("BACK TO PASSWORD FROM OPTIONS");
        backToBattlefield.setActionCommand("BACK TO BATTLEFIELD FROM OPTIONS");
    }

    void setHideCancel(boolean hide) {
        if (hide) {
            cancel.setText("NEW PLAYER");
            backToBattlefield.setVisible(true);
        } else {
            cancel.setText("CANCEL");
            backToBattlefield.setVisible(false);
        }
    }
}
