package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.academit.school.myskin.minesweeper.gui.NewPassword.createAndShowGI;

public class Password extends JPanel implements ActionListener {

    private static String OK = "Ok";
    private static String NEWUSER = "New User";

    private JDialog controllingFrame; //needed for dialogs
    private JTextField nickNameField;
    private JPasswordField passwordField;
    private LinkedList<Player> players = new LinkedList<>();

    public Password(JDialog dialog) {
        controllingFrame = dialog;

        passwordField = new JPasswordField(10);
        nickNameField = new JTextField(10);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(this);

        JLabel enterPassword = new JLabel("Enter the password: ");
        enterPassword.setLabelFor(passwordField);

        JLabel enterNickName = new JLabel("Enter your Login: ");
        enterNickName.setLabelFor(nickNameField);

        JComponent buttonPane = createButtonPanel();

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(enterNickName);
        textPane.add(nickNameField);

        textPane.add(enterPassword);
        textPane.add(passwordField);

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        getPlayers();
        JPanel p = new JPanel(new GridLayout(0, 1));
        JButton okButton = new JButton("Ok");
        JButton newUser = new JButton("New User");

        okButton.setActionCommand(OK);
        newUser.setActionCommand(NEWUSER);
        okButton.addActionListener(this);
        newUser.addActionListener(this);

        p.add(okButton);
        p.add(newUser);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (OK.equals(cmd)) { //Process the password.
            char[] input = passwordField.getPassword();
            String login = nickNameField.getText();
            List<Player> ourUser;

            if (players.size() == 0) {
                JOptionPane.showMessageDialog(controllingFrame, "Invalid password. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                ourUser = players.stream().filter(x -> x.getName().equals(login)).filter(x -> x.checkPassword(input)).collect(Collectors.toList());

                if (ourUser.size() != 0) {
                    if (ourUser.get(0).checkPassword(input)) {
                        JOptionPane.showMessageDialog(controllingFrame, "Success! You typed the right password.");
                    }
                } else {
                    JOptionPane.showMessageDialog(controllingFrame, "Invalid password. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }

            //Zero out the possible password, for security.
            Arrays.fill(input, '0');

            passwordField.selectAll();
            resetFocus();
        } else {
            controllingFrame.dispatchEvent(new WindowEvent(controllingFrame, WindowEvent.WINDOW_CLOSING));
            createAndShowGI();
        }
    }

    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        JDialog frame = new JDialog();

        final Password newContentPane = new Password(frame);
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

    private void getPlayers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Players.txt"))) {
            players = (LinkedList<Player>) in.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
}
