package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

class GameSettings extends JPanel {
    private JTextField heightField;
    private JTextField weightField;
    private JTextField mines;
    private JRadioButton easy;
    private JRadioButton normal;
    private JRadioButton high;
    private JPanel customPane;
    private JPanel deafultPane;
    private static Player player;
    private static JButton okButton;
    private static JButton defaultButton;
    private static JButton customButton;
    private static JButton cancel;
    private static JButton backToBattlefield;

    private static List<JButton> buttons = new LinkedList<>();

    GameSettings() {
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
        deafultPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        deafultPane.add(easy);
        deafultPane.add(normal);
        deafultPane.add(high);

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

        add(deafultPane);

        customPane.setVisible(false);

        add(buttonPane);
    }

    private JComponent createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(5, 15, 5, 5);
        c1.weighty = 1;

        c1.gridx = 0;

        panel.add(customButton, c1);
        panel.add(defaultButton, c1);

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.anchor = GridBagConstraints.EAST;
        c1.gridx = 1;
        c1.insets = new Insets(5, 5, 5, 5);

        panel.add(okButton, c1);

        c1.gridx = 2;

        defaultButton.setVisible(false);
        panel.add(cancel, c1);
        c1.gridx = 3;
        panel.add(backToBattlefield, c1);

        return panel;
    }

    JPanel createMap() {
        if (deafultPane.isVisible()) {
            if (easy.isSelected()) {
                return new BattleField(9, 9, 20, player);
            }
            if (normal.isSelected()) {
                return new BattleField(15, 15, 50, player);
            }
            if (high.isSelected()) {
                return new BattleField(30, 30, 110, player);
            }
        } else {
            try {
                int weight = Integer.parseInt(weightField.getText());
                int height = Integer.parseInt(heightField.getText());
                int mines = Integer.parseInt(this.mines.getText());

                if (weight > 0 && weight < 100 && height > 0 && height < 100 && mines > 0 && mines <= (weight * height) * 0.75) {
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
        deafultPane.setVisible(false);
        customPane.setVisible(true);
        defaultButton.setVisible(true);
        customButton.setVisible(false);
    }

    void defaultSetup() {
        deafultPane.setVisible(true);
        customPane.setVisible(false);
        customButton.setVisible(true);
        defaultButton.setVisible(false);
    }

    static List<JButton> getButtons() {
        return buttons;
    }

    static void createButtons() {
        okButton = new JButton("Ok");
        cancel = new JButton("Cancel");
        customButton = new JButton("Custom");
        defaultButton = new JButton("Default");
        backToBattlefield = new JButton("Back");
        backToBattlefield.setVisible(false);

        buttons.add(okButton);
        buttons.add(cancel);
        buttons.add(customButton);
        buttons.add(defaultButton);
        buttons.add(backToBattlefield);

        okButton.setActionCommand("CREATE BATTLEFIELD");
        customButton.setActionCommand("CUSTOM");
        defaultButton.setActionCommand("DEFAULT");
        cancel.setActionCommand("BACK TO PASSWORD");
        backToBattlefield.setActionCommand("BACK TO BATTLEFIELD");
    }

    public void setHideCancel(boolean hide) {
        if (hide) {
            cancel.setText("NEW PLAYER");
            backToBattlefield.setVisible(true);
        } else {
            cancel.setText("CANCEL");
            backToBattlefield.setVisible(false);
        }
    }

    static void updatePlayer(Player player) {
        GameSettings.player = player;
    }
}
