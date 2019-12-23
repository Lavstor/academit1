package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.HighScoresReader;
import ru.academit.school.myskin.minesweeper.user.Player;
import ru.academit.school.myskin.minesweeper.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class NewPassword extends JPanel {
    private static List<JButton> buttons = new LinkedList<>();

    final private JTextField nickNameField;
    final private JPasswordField passwordField;
    final private JPasswordField confirmPasswordField;
    final private LinkedList<User> players;

    NewPassword(LinkedList<User> users) {
        this.players = users;

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
        GridBagConstraints constraints = new GridBagConstraints();

        add(textPane, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(25, 5, 5, 5);

        add(buttonPane, constraints);
    }

    private JComponent createButtonPanel() {
        GridLayout gridLayout = new GridLayout(0, 3);
        gridLayout.setHgap(30);

        JPanel panel = new JPanel(gridLayout);
        buttons.forEach(panel::add);

        return panel;
    }

    static void createButtons() {
        JButton okButton = new JButton("OK");
        JButton back = new JButton("BACK");
        JButton menu = new JButton("MENU");

        okButton.setActionCommand("CHECK DATA");
        back.setActionCommand("BACK TO PASSWORD");
        menu.setActionCommand("BACK TO MENU FROM NEW PASSWORD");

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

        List<User> ourUser;
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

        HighScoresReader.writeUsers(new Player(login, password));

        return true;
    }

    private void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    static List<JButton> getButtons() {
        return buttons;
    }
}
