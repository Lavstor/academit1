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

import static ru.academit.school.myskin.minesweeper.gui.GameSettings.createAndShowGUI3;
import static ru.academit.school.myskin.minesweeper.gui.Password.createAndShowGUI;

public class NewPassword extends JPanel implements ActionListener {

    private static String OK = "Ok";
    private static String CANCEL = "Cancel";
    private JDialog controllingFrame;
    private JTextField nickNameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private static LinkedList<Player> players = new LinkedList<>();

    public NewPassword(JDialog f) {

        controllingFrame = f;

        passwordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10);
        nickNameField = new JTextField(10);
        confirmPasswordField.setActionCommand(OK);
        confirmPasswordField.addActionListener(this);

        JLabel enterPassword = new JLabel("Enter new password: ");
        enterPassword.setLabelFor(passwordField);

        JLabel confirmPassword = new JLabel("Enter new password: ");
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

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0, 1));
        JButton okButton = new JButton("OK");
        JButton newUser = new JButton("CANCEL");

        okButton.setActionCommand(OK);
        newUser.setActionCommand(CANCEL);
        okButton.addActionListener(this);
        newUser.addActionListener(this);

        p.add(okButton);
        p.add(newUser);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        getPlayers();

        if (OK.equals(cmd)) {
            char[] password = passwordField.getPassword();
            char[] input2 = confirmPasswordField.getPassword();
            String login = nickNameField.getText();

            if (login.length() <= 3 || password.length <= 3) {
                JOptionPane.showMessageDialog(controllingFrame, "Too short login or password!", "Error Message", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<Player> ourUser;

            ourUser = players.stream().filter(x -> x.getName().equals(login)).collect(Collectors.toList());

            if (ourUser.size() != 0) {
                JOptionPane.showMessageDialog(controllingFrame, "This login already registered. Try another!", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
                    if (!Arrays.equals(password, input2)) {
                        JOptionPane.showMessageDialog(controllingFrame, "Passwords dnt match", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Player newPlayer = new Player(login, password);
                        players.add(newPlayer);
                        out.writeObject(players);

                        JOptionPane.showMessageDialog(controllingFrame, "Nice! You have been registered!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);

                        controllingFrame.dispatchEvent(new WindowEvent(controllingFrame, WindowEvent.WINDOW_CLOSING));

                      createAndShowGUI3(newPlayer);
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            Arrays.fill(password, '0');
            Arrays.fill(input2, '0');

            passwordField.selectAll();
            resetFocus();

            confirmPasswordField.selectAll();
            resetFocus();
        } else {
            createAndShowGUI();
            controllingFrame.dispatchEvent(new WindowEvent(controllingFrame, WindowEvent.WINDOW_CLOSING));
        }
    }

    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    public static void createAndShowGI(LinkedList<Player> players) {
        JDialog frame = new JDialog();

        final NewPassword newContentPane = new NewPassword(frame);
        newContentPane.setOpaque(true);
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
