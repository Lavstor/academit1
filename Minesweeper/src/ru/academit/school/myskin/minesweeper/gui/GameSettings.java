package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class GameSettings extends JPanel implements ActionListener {
    private static String OK = "Ok";
    private static String CANCEL = "Cancel";
    private static String DEFAULT = "Default";
    private static String CUSTOM = "Custom";

    private JDialog controllingFrame; //needed for dialogs
    private JTextField heightField;
    private JTextField weightField;
    private JTextField mines;
    private JRadioButton easy;
    private JRadioButton normal;
    private JRadioButton high;
    private JPanel customPane;
    private JPanel deafultPane;
    private static Player player;
    private JButton customButton;
    private JButton defaultButton;
    private ButtonGroup radioGroup = new ButtonGroup();

    public GameSettings(JDialog dialog) {
        controllingFrame = dialog;

        weightField = new JTextField(3);
        heightField = new JTextField(3);
        mines = new JTextField(2);
        weightField.setActionCommand(OK);
        weightField.addActionListener(this);

        easy = new JRadioButton("Easy", false);
        normal = new JRadioButton("Normal", true);
        high = new JRadioButton("High", false);

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


        add(customPane);
        add(deafultPane);

        customPane.setVisible(false);

        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        JButton okButton = new JButton("Ok");
        customButton = new JButton("Custom");
        defaultButton = new JButton("Default");
        JButton cancel = new JButton("Cancel");


        okButton.setActionCommand(OK);
        customButton.setActionCommand(CUSTOM);
        defaultButton.setActionCommand(DEFAULT);

        cancel.setActionCommand(CANCEL);

        defaultButton.addActionListener(this);
        customButton.addActionListener(this);

        okButton.addActionListener(this);
        cancel.addActionListener(this);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(5, 15, 5, 5);
        c1.weighty = 1;

        c1.gridx = 0;

        p.add(customButton, c1);
        p.add(defaultButton, c1);

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.anchor = GridBagConstraints.EAST;
        c1.gridx = 1;
        c1.insets = new Insets(5, 5, 5, 5);

        p.add(okButton, c1);

        c1.gridx = 2;

        defaultButton.setVisible(false);
        p.add(cancel, c1);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (OK.equals(cmd)) {
            if (deafultPane.isVisible()) {
                if (easy.isSelected()) {
                    new BattleField(9, 9, 20, player);
                }
                if (normal.isSelected()) {
                    new BattleField(15, 15, 50, player);
                }
                if (high.isSelected()) {
                    new BattleField(30, 30, 110, player);
                }
                controllingFrame.dispose();

            } else {
                try {
                    int weight = Integer.parseInt(weightField.getText());
                    int height = Integer.parseInt(heightField.getText());
                    int mines = Integer.parseInt(this.mines.getText());

                    if (weight > 0 && weight < 100 && height > 0 && height < 100 && mines > 0 && mines <= (weight * height) * 0.75) {
                        new BattleField(Integer.parseInt(weightField.getText()), Integer.parseInt(heightField.getText()),
                                Integer.parseInt(this.mines.getText()), player);
                        controllingFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(controllingFrame, "Error! Wrong options!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException o) {
                    JOptionPane.showMessageDialog(controllingFrame, "Error! Wrong type!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (CANCEL.equals(cmd)) {
            controllingFrame.dispatchEvent(new WindowEvent(controllingFrame, WindowEvent.WINDOW_CLOSING));
        }

        if (DEFAULT.equals(cmd)) {
            deafultPane.setVisible(true);
            customPane.setVisible(false);
            customButton.setVisible(true);
            defaultButton.setVisible(false);
        }

        if (CUSTOM.equals(cmd)) {
            deafultPane.setVisible(false);
            customPane.setVisible(true);
            defaultButton.setVisible(true);
            customButton.setVisible(false);
        }

    }

    protected void resetFocus() {
        weightField.requestFocusInWindow();
    }

    public static void createAndShowGUI3(Player player) {
        GameSettings.player = player;

        JDialog frame = new JDialog();

        final GameSettings newContentPane = new GameSettings(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
}
