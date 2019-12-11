package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Password extends JPanel {
    private JTextField nickNameField;
    private JPasswordField passwordField;
    private static LinkedList<Player> players;
    private static List<JButton> buttons = new LinkedList<>();
    private static Player ourPlayer;
    private static JButton okButton;
    private static JButton newUser;
    private static JButton menu;

    Password(LinkedList<Player> players) {
        Password.players = players;

        passwordField = new JPasswordField(10);
        nickNameField = new JTextField(10);
        passwordField.setActionCommand("OK");

        JLabel enterPassword = new JLabel("Password: ");
        enterPassword.setLabelFor(passwordField);

        JLabel enterNickName = new JLabel("Login: ");
        enterNickName.setLabelFor(nickNameField);

        JComponent buttonPanel = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(enterNickName);
        textPane.add(nickNameField);

        textPane.add(enterPassword);
        textPane.add(passwordField);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        add(textPane, c);
        c.gridy = 1;
        c.insets = new Insets(25, 5, 5, 5);

        add(buttonPanel, c);
    }

    private JComponent createButtonPanel() {
        GridLayout gl = new GridLayout(0, 3);
        gl.setHgap(30);

        JPanel p = new JPanel(gl);

        buttons.add(okButton);
        buttons.add(newUser);
        buttons.add(menu);

        p.add(okButton);
        p.add(newUser);
        p.add(menu);

        return p;
    }

    boolean checkPassword() {
        char[] input = passwordField.getPassword();
        String login = nickNameField.getText();
        List<Player> ourUser;

        if (players.size() == 0) {
            JOptionPane.showMessageDialog(this, "Invalid password or login. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);

            Arrays.fill(input, '0');

            passwordField.selectAll();
            resetFocus();

            return false;
        }
            ourUser = players.stream().filter(x -> x.getName().equals(login)).filter(x -> x.checkPassword(input)).collect(Collectors.toList());

        if (ourUser.size() != 0) {
            if (ourUser.get(0).checkPassword(input)) {
                ourPlayer = ourUser.get(0);

                return true;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid password. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);

            Arrays.fill(input, '0');

            passwordField.selectAll();
            resetFocus();

            return false;
        }

        return false;
    }


    private void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    static List<JButton> getButtons() {
        okButton = new JButton("OK");
        newUser = new JButton("NEW USER");
        menu = new JButton("MENU");

        okButton.setActionCommand("OK");
        newUser.setActionCommand("NEW USER");
        menu.setActionCommand("BACK");

        buttons.add(okButton);
        buttons.add(newUser);
        buttons.add(menu);

        return buttons;
    }

    static void updatePlayers(LinkedList<Player> players) {
        Password.players = players;
    }

    static Player getPlayer() {
        return ourPlayer;
    }
}
