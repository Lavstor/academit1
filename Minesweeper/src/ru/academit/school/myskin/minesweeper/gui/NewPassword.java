package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class NewPassword extends JPanel {
    private JTextField nickNameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private  LinkedList<Player> players;
    private static List<JButton> buttons = new LinkedList<>();

    NewPassword(LinkedList<Player> players) {
        this.players = players;

        setVisible(true);

        passwordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10);
        nickNameField = new JTextField(10);
        confirmPasswordField.setActionCommand("OK");

        JLabel enterPassword = new JLabel("Enter new password: ");
        enterPassword.setLabelFor(passwordField);

        JLabel confirmPassword = new JLabel("Confirm password: ");
        confirmPassword.setLabelFor(passwordField);

        JLabel enterNickName = new JLabel("Enter new Login: ");
        enterNickName.setLabelFor(nickNameField);

        JComponent buttonPane = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(enterNickName);
        textPane.add(nickNameField);

        textPane.add(enterPassword);
        textPane.add(passwordField);

        textPane.add(confirmPassword);
        textPane.add(confirmPasswordField);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        add(textPane, c);

        c.gridy = 1;
        c.insets = new Insets(25, 5, 5, 5);

        add(buttonPane, c);
    }

    private JComponent createButtonPanel() {
        GridLayout gl = new GridLayout(0, 3);
        gl.setHgap(30);

        JPanel p = new JPanel(gl);
        buttons.forEach(p::add);

        return p;
    }

    static void createButtons() {
        JButton okButton = new JButton("OK");
        JButton back = new JButton("BACK");
        JButton menu = new JButton("MENU");

        okButton.setActionCommand("NEW USER CREATED");
        back.setActionCommand("BACK TO PASSWORD");
        menu.setActionCommand("BACK NEW PASSWORD");

        buttons.add(back);
        buttons.add(okButton);
        buttons.add(menu);
    }

    boolean checkData() {
        char[] password = passwordField.getPassword();
        char[] input2 = confirmPasswordField.getPassword();
        String login = nickNameField.getText();

        if (login.length() <= 3 || password.length <= 3) {
            JOptionPane.showMessageDialog(this, "Too short login or password!", "Error Message", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        List<Player> ourUser;
        ourUser = players.stream().filter(x -> x.getName().equals(login)).collect(Collectors.toList());

        if (ourUser.size() != 0) {
            JOptionPane.showMessageDialog(this, "This login already registered. Try another!", "Error Message", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if (!Arrays.equals(password, input2)) {
            JOptionPane.showMessageDialog(this, "Passwords dnt match", "Error Message", JOptionPane.ERROR_MESSAGE);
            Arrays.fill(password, '0');
            Arrays.fill(input2, '0');

            passwordField.selectAll();
            resetFocus();

            confirmPasswordField.selectAll();
            resetFocus();

            return false;
        }
        Player ourPlayer = new Player(login, password);

        Model.writePlayers(ourPlayer);

        return true;
    }

    private void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    static List<JButton> getButtons() {
        return buttons;
    }
}
