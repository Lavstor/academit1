package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.Model;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MineSweeper extends JFrame {
    private Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
    private Menu menu;
    private Records records;
    private Info info;
    private Password password;
    private NewPassword newPassword;
    private Model model;
    private LinkedList<Player> players;
    private Player ourPlayer;

    public MineSweeper() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width) / 2, (screenSize.height) / 2, 400, 280);

        setLayout(new BorderLayout());
        customUI();

        setVisible(true);
        setIconImage(img);

        players = Model.readPlayers();

        menu = new Menu();
        info = new Info();
        records = new Records(players);
        password = new Password(players);
        newPassword = new NewPassword(players);

        add(menu, BorderLayout.CENTER);

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getButtons();
    }

    private void customUI() {
        ArrayList<Object> gradients = new ArrayList<>();
        gradients.add(0.3);
        gradients.add(0.0);
        gradients.add(Color.RED);
        gradients.add(Color.RED);
        gradients.add(Color.BLACK);

        UIManager.put("RadioButton.background", new ColorUIResource(Color.RED));
        UIManager.put("RadioButton.foreground", new ColorUIResource(Color.BLACK));
        UIManager.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("Button.select", Color.red);
        UIManager.put("Button.gradient", gradients);
        UIManager.put("Button.foreground", new ColorUIResource(Color.BLACK));
        UIManager.put("OptionPane.background", new ColorUIResource(Color.BLACK));
        UIManager.put("OptionPane.noButtonText", "NOPE");
        UIManager.put("OptionPane.yesButtonText", "YUP");
        UIManager.put("OptionPane.massage", new ColorUIResource(Color.RED));
        UIManager.put("Panel.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Label.foreground", new ColorUIResource(Color.RED));
    }

    private void getButtons() {
        Arrays.stream(Menu.getButtons()).forEach(b -> b.addActionListener(this::actionPerformed));
        Info.getButton().addActionListener(this::actionPerformed);
        Records.getBackButton().addActionListener(this::actionPerformed);
        Password.getButtons().forEach(b -> b.addActionListener(this::actionPerformed));
        NewPassword.getButtons().forEach(b -> b.addActionListener(this::actionPerformed));
    }

    private void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("EXIT")) {
            ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\" +
                    "academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\EXIT.gif");

            if (JOptionPane.showConfirmDialog(this, "           YOU SHURE?", "EXIT",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon) == 0) {
                dispose();
            }
        }

        if (command.equals("RECORDS")) {
            remove(menu);
            add(records, BorderLayout.CENTER);
            records.setVisible(true);
            repaint();
            records.updateUI();
        }

        if (command.equals("INFO")) {
            remove(menu);
            add(info, BorderLayout.CENTER);
            info.setVisible(true);
            repaint();
            info.updateUI();
        }

        if (command.equals("NEW GAME")) {
            remove(menu);
            add(password, BorderLayout.CENTER);
            password.setVisible(true);
            repaint();
            password.updateUI();
        }

        if (command.equals("BACK")) {
            remove(password);
            remove(info);
            remove(records);
            remove(newPassword);

            add(menu, BorderLayout.CENTER);
            menu.updateUI();
        }

        if (command.equals("BACK TO PASSWORD")) {
            remove(newPassword);

            add(password, BorderLayout.CENTER);
            password.updateUI();
        }

        if (command.equals("NEW USER")) {
            remove(password);
            add(newPassword, BorderLayout.CENTER);
            newPassword.updateUI();
        }

        if (command.equals("OK")) {
            if (!password.checkPassword()) {
                System.out.println("Ошибка");
            } else {
                System.out.println("Норм");
                ourPlayer = Password.getPlayer();
            }
        }

        if (command.equals("GAME SETTINGS")) {
            if (!newPassword.checkData()) {
                System.out.println("Нормfedfdf");
            } else {
                updatePlayersList(Model.readPlayers());
                ourPlayer = NewPassword.getPlayer();
            }
        }
    }

    private void updatePlayersList(LinkedList<Player> players) {
        Password.updatePlayers(players);
        NewPassword.updatePlayers(players);
        Records.updatePlayers(players);

        password.updateUI();
        newPassword.updateUI();
        records.updateUI();
    }
}
