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
    private Menu menu;
    private Records records;
    private Info info;
    private Password password;
    private NewPassword newPassword;
    private Model model;
    private Player ourPlayer;
    private GameSettings gameSettings;
    private BattleField battleField;
    private LinkedList<Player> players = Model.readPlayers();

    public MineSweeper() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 900) / 2, (screenSize.height - 600) / 2, 900, 600);

        setLayout(new BorderLayout());
        customUI();

        setVisible(true);
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\" +
                "gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\" +
                "myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        setIconImage(img);


        Menu.createButtons();


        Info.createButtons();
        Records.createButtons();
        Password.createButtons();
        NewPassword.createButtons();
        GameSettings.createButtons();
        BattleField.createButtons();

        menu = new Menu();
        info = new Info();
        records = new Records(players);
         password = new Password(players);
        newPassword = new NewPassword(players);
        gameSettings = new GameSettings();

        add(menu, BorderLayout.CENTER);
        menu.updateUI();

      //  setSize(900, 600);
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
        GameSettings.getButtons().forEach(b -> b.addActionListener(this::actionPerformed));
        BattleField.getButtons().forEach(b -> b.addActionListener(this::actionPerformed));
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
            records = new Records(players);
            add(records, BorderLayout.CENTER);
            records.setVisible(true);
            repaint();
            records.updateUI();
        }

        if (command.equals("INFO")) {
            remove(menu);
            info = new Info();
            add(info, BorderLayout.CENTER);
            info.setVisible(true);
            repaint();
            info.updateUI();
        }

        if (command.equals("NEW GAME")) {
            remove(menu);
            password = new Password(players);
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

            menu = new Menu();

            add(menu, BorderLayout.CENTER);
            menu.updateUI();
        }

        if (command.equals("BACK TO PASSWORD")) {
            remove(gameSettings);
            remove(newPassword);
           // password = new Password(players);
            add(password, BorderLayout.CENTER);
            password.updateUI();
        }

        if (command.equals("NEW USER")) {
            remove(password);
            newPassword = new NewPassword(players);
            add(newPassword, BorderLayout.CENTER);
            newPassword.updateUI();
        }

        if (command.equals("OK")) {
            if (password.checkPassword()) {
                ourPlayer = Password.getPlayer();
                remove(password);
                gameSettings = new GameSettings();
                add(gameSettings, BorderLayout.CENTER);
                GameSettings.updatePlayer(ourPlayer);

                gameSettings.updateUI();
            }
        }

        if (command.equals("OPTIONS")) {
            remove(battleField);
            gameSettings = new GameSettings();
            add(gameSettings, BorderLayout.CENTER);
            repaint();
        }

        if (command.equals("GAME SETTINGS")) {
            if (newPassword.checkData()) {
                ourPlayer = NewPassword.getPlayer();
                updatePlayersList(Model.readPlayers());

                remove(newPassword);
                gameSettings = new GameSettings();
                add(gameSettings, BorderLayout.CENTER);

                gameSettings.updateUI();

            }
        }

        if (command.equals("CREATE BATTLEFIELD")) {
            remove(gameSettings);
            battleField = (BattleField) gameSettings.createMap();

            add(battleField, BorderLayout.CENTER);
            battleField.updateUI();
        }

        if (command.equals("DEFAULT")) {
            gameSettings.defaultSetup();
        }

        if (command.equals("CUSTOM")) {
            gameSettings.customSetup();
        }

        if (command.equals("MENU")) {
            remove(battleField);
            menu = new Menu();
            add(menu, BorderLayout.CENTER);
            menu.continueButton(true);
            menu.updateUI();
            repaint();
        }

        if (command.equals("CONTINUE")) {
            remove(menu);
            add(battleField, BorderLayout.CENTER);
            battleField.updateUI();
            menu.continueButton(false);
            repaint();
        }

        if (command.equals("END GAME")) {
            remove(battleField);

            remove(menu);
            menu = new Menu();

            add(menu, BorderLayout.CENTER);
            menu.updateUI();
            menu.continueButton(false);
            repaint();
        }

        if (command.equals("BACK TO OPTIONS")) {
            remove(battleField);

            add(gameSettings, BorderLayout.CENTER);
            gameSettings.setHideCancel(true);
            repaint();
        }

        if (command.equals("BACK TO BATTLEFIELD")) {
            remove(gameSettings);

            add(battleField, BorderLayout.CENTER);
            gameSettings.setHideCancel(false);
            repaint();
        }

        if (command.equals("UPDATE")) {
            updatePlayersList(players);
        }
    }

    private void updatePlayersList(LinkedList<Player> players) {
        Password.updatePlayers(players);
        NewPassword.updatePlayers(players);
        Records.updatePlayers(players);
        GameSettings.updatePlayer(ourPlayer);

        password.updateUI();
        newPassword.updateUI();
        records.updateUI();
        gameSettings.updateUI();
    }
}
