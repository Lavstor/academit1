package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.academit.school.myskin.minesweeper.gui.GameSettings.createAndShowGUI3;
import static ru.academit.school.myskin.minesweeper.gui.NewPassword.createAndShowGI;

public class Password extends JPanel implements ActionListener {
    private JTextField nickNameField;
    private JPasswordField passwordField;
    private LinkedList<Player> players = new LinkedList<>();
    private static List<JButton> buttons = new LinkedList<>();

    Password() {
        passwordField = new JPasswordField(10);
        nickNameField = new JTextField(10);
        passwordField.setActionCommand("OK");
        passwordField.addActionListener(this);

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
        getPlayers();
        GridLayout gl = new GridLayout(0, 3);
        gl.setHgap(30);

        JPanel p = new JPanel(gl);
        JButton okButton = new JButton("OK");
        JButton newUser = new JButton("NEW USER");
        JButton menu = new JButton("MENU");
        buttons.add(okButton);
        buttons.add(newUser);
        buttons.add(menu);

        okButton.setActionCommand("OK");
        newUser.setActionCommand("NEW USER");
        menu.setActionCommand("BACK");

        p.add(okButton);
        p.add(newUser);
        p.add(menu);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("OK")) { //Process the password.
            char[] input = passwordField.getPassword();
            String login = nickNameField.getText();
            List<Player> ourUser;

            if (players.size() == 0) {
                JOptionPane.showMessageDialog(this, "Invalid password. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                ourUser = players.stream().filter(x -> x.getName().equals(login)).filter(x -> x.checkPassword(input)).collect(Collectors.toList());

                if (ourUser.size() != 0) {
                    if (ourUser.get(0).checkPassword(input)) {
                        // this.dispatchEvent(new WindowEvent(new Password(), WindowEvent.WINDOW_CLOSING));
                        createAndShowGUI3(ourUser.get(0));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid password. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }

            //Zero out the possible password, for security.
            Arrays.fill(input, '0');

            passwordField.selectAll();
            resetFocus();
        } else {
            createAndShowGI(players);
        }
    }

    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    public static void createAndShowGUI() {
      /*  JDialog frame = new JDialog();

        final Password newContentPane = new Password(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });
        frame.pack();
        frame.setVisible(true);*/
    }

    private void getPlayers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Players.txt"))) {
            players = (LinkedList<Player>) in.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    public static List<JButton> getButtons() {
        return buttons;
    }
}
