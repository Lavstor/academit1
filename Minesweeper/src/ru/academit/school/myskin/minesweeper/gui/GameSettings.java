package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static ru.academit.school.myskin.minesweeper.gui.Password.createAndShowGUI;

public class GameSettings extends JPanel implements ActionListener {
    private static String OK = "Ok";
    private static String CANCEL = "New User";

    private JDialog controllingFrame; //needed for dialogs
    private JTextField heightField;
    private JTextField weightField;
    private JTextField mines;
    private static Player player;

    public GameSettings(JDialog dialog) {
        controllingFrame = dialog;

        weightField = new JTextField(3);
        heightField = new JTextField(3);
        mines = new JTextField(2);
        weightField.setActionCommand(OK);
        weightField.addActionListener(this);

        JLabel weight = new JLabel("Enter weight: ");
        weight.setLabelFor(weightField);

        JLabel height = new JLabel("Enter height: ");
        height.setLabelFor(heightField);

        JLabel mines = new JLabel("Enter mines count: ");
        mines.setLabelFor(this.mines);

        JComponent buttonPane = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(height);
        textPane.add(heightField);

        textPane.add(weight);
        textPane.add(weightField);

        textPane.add(mines);
        textPane.add(this.mines);

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0, 1));
        JButton okButton = new JButton("Ok");
        JButton cancel = new JButton("Cancel");

        okButton.setActionCommand(OK);
        cancel.setActionCommand(CANCEL);
        okButton.addActionListener(this);
        cancel.addActionListener(this);

        p.add(okButton);
        p.add(cancel);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int weight = Integer.parseInt(weightField.getText());
        int height = Integer.parseInt(heightField.getText());
        int mines = Integer.parseInt(this.mines.getText());

        if (OK.equals(cmd)) {
            if (weight > 0 && weight < 100 && height > 0 && height < 100) {
                new BattleField(weight, height, mines, player);
            }
        } else {
            controllingFrame.dispatchEvent(new WindowEvent(controllingFrame, WindowEvent.WINDOW_CLOSING));
            createAndShowGUI();
        }
    }

    protected void resetFocus() {
        weightField.requestFocusInWindow();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
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
