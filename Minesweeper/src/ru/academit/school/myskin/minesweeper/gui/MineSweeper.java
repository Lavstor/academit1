package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.model.HighScoresReader;
import ru.academit.school.myskin.minesweeper.user.User;

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
    private Password password;
    private NewPassword newPassword;
    private GameSettings gameSettings;
    private LinkedList<User> users = HighScoresReader.readPlayers();
    private JPanel infoPanel;
    private JPanel battleFieldPanel;

    public MineSweeper() {
        SwingUtilities.invokeLater(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds((screenSize.width - 900) / 2, (screenSize.height - 700) / 2, 900, 700);

            setLayout(new BorderLayout());
            customUI();

            setVisible(true);
            Image img = Toolkit.getDefaultToolkit().getImage("Minesweeper/src/ru/academit/school" +
                    "/myskin/minesweeper/resources/minesweeperFrame/frameIcon.jpg");
            setIconImage(img);
            setTitle("Minesweeper");

            Menu.createButtons();
            Info.createButtons();
            Records.createButtons();
            Password.createButtons();
            NewPassword.createButtons();
            GameSettings.createButtons();
            BattleField.createButtons();

            menu = new Menu();
            add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getButtons();
        });
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
            ImageIcon icon = new ImageIcon("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/minesweeperFrame/exitGame.gif");

            if (JOptionPane.showConfirmDialog(this, "           YOU SHURE?", "EXIT",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon) == 0) {
                dispose();
            }
        }

        if (command.equals("RECORDS")) {
            remove(menu.getMenuPanel());

            records = new Records(users);
            add(records.getRecordsPanel(), BorderLayout.CENTER);
            records.getRecordsPanel().setVisible(true);
            repaint();
            records.getRecordsPanel().updateUI();
        }

        if (command.equals("INFO")) {
            remove(menu.getMenuPanel());
            Info info = new Info();
            infoPanel = info.getPanel();

            add(infoPanel, BorderLayout.CENTER);
            infoPanel.setVisible(true);
            repaint();
            infoPanel.updateUI();
        }

        if (command.equals("NEW GAME")) {
            remove(menu.getMenuPanel());
            password = new Password(users);
            add(password.getPasswordPanel(), BorderLayout.CENTER);
            password.getPasswordPanel().setVisible(true);
            repaint();
            password.getPasswordPanel().updateUI();
        }

        if (command.equals("TO MENU FROM PASSWORD")) {
            remove(password.getPasswordPanel());

            menu = new Menu();

            add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            repaint();
        }

        if (command.equals("BACK TO MENU FROM NEW PASSWORD")) {
            remove(newPassword.getNewPasswordPanel());

            menu = new Menu();

            add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            repaint();
        }

        if (command.equals("BACK TO MENU FROM INFO")) {
            remove(infoPanel);

            menu = new Menu();

            add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            repaint();
        }

        if (command.equals("BACK TO MENU FROM RECORDS")) {
            remove(records.getRecordsPanel());

            menu = new Menu();

            add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            repaint();
        }

        if (command.equals("BACK TO PASSWORD")) {
            remove(newPassword.getNewPasswordPanel());
            password = new Password(users);
            add(password.getPasswordPanel(), BorderLayout.CENTER);
            password.getPasswordPanel().updateUI();
        }

        if (command.equals("BACK TO PASSWORD FROM OPTIONS")) {
            remove(gameSettings.getGameSettingsPanel());
            password = new Password(users);
            add(password.getPasswordPanel(), BorderLayout.CENTER);
            password.getPasswordPanel().updateUI();
        }

        if (command.equals("TO NEW PASSWORD FROM PASSWORD")) {
            remove(password.getPasswordPanel());
            newPassword = new NewPassword(users);
            add(newPassword.getNewPasswordPanel(), BorderLayout.CENTER);
            newPassword.getNewPasswordPanel().updateUI();
        }

        if (command.equals("TO OPTIONS FROM PASSWORD")) {
            if (password.checkPassword()) {
                User currentUser = Password.getPlayer();
                remove(password.getPasswordPanel());
                gameSettings = new GameSettings(currentUser);
                gameSettings.defaultSetup();
                gameSettings.setHideCancel(false);

                add(gameSettings.getGameSettingsPanel(), BorderLayout.CENTER);
                gameSettings.getGameSettingsPanel().updateUI();
            }
        }

        if (command.equals("CHECK DATA")) {
            if (newPassword.checkData()) {
                users = HighScoresReader.readPlayers();
                password = new Password(users);
                remove(newPassword.getNewPasswordPanel());
                add(password.getPasswordPanel(), BorderLayout.CENTER);
                password.getPasswordPanel().updateUI();
            }
        }

        if (command.equals("CREATE BATTLEFIELD")) {
            JPanel testBattleField = gameSettings.createMap();

            if (testBattleField != null) {
                battleFieldPanel = testBattleField;

                remove(gameSettings.getGameSettingsPanel());
                add(battleFieldPanel, BorderLayout.CENTER);
                battleFieldPanel.updateUI();
            }
        }

        if (command.equals("DEFAULT")) {
            gameSettings.defaultSetup();
        }

        if (command.equals("CUSTOM")) {
            gameSettings.customSetup();
        }

        if (command.equals("BACK TO MENU FROM BATTLEFIELD")) {
            remove(battleFieldPanel);
            menu = new Menu();
            add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.continueButton(true);
            menu.getMenuPanel().updateUI();
            repaint();
        }

        if (command.equals("CONTINUE")) {
            remove(menu.getMenuPanel());
            add(battleFieldPanel, BorderLayout.CENTER);
            battleFieldPanel.updateUI();
            menu.continueButton(false);
            repaint();
        }

        if (command.equals("BACK TO OPTIONS FROM BATTLEFIELD")) {
            remove(battleFieldPanel);

            add(gameSettings.getGameSettingsPanel(), BorderLayout.CENTER);
            gameSettings.setHideCancel(true);
            repaint();
            gameSettings.getGameSettingsPanel().updateUI();
        }

        if (command.equals("BACK TO BATTLEFIELD FROM OPTIONS")) {
            remove(gameSettings.getGameSettingsPanel());

            add(battleFieldPanel, BorderLayout.CENTER);
            gameSettings.setHideCancel(false);
            repaint();
        }

        if (command.equals("UPDATE")) {
            HighScoresReader.updateList(users);
        }
    }
}
