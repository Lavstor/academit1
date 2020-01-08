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

public class MineSweeper {
    private JFrame mainFrame;
    private Menu menu;
    private Records records;
    private Password password;
    private NewPassword newPassword;
    private GameSettings gameSettings;
    private Info info;
    private LinkedList<User> users;
    private JPanel battleFieldPanel;

    final private String frameImagePass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/minesweeperFrame/";

    public MineSweeper() {
        SwingUtilities.invokeLater(() -> {
            mainFrame = new JFrame();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mainFrame.setBounds((screenSize.width - 900) / 2, (screenSize.height - 700) / 2, 900, 700);

            mainFrame.setLayout(new BorderLayout());
            customUI();

            mainFrame.setVisible(true);
            Image img = Toolkit.getDefaultToolkit().getImage(frameImagePass + "frameIcon.jpg");
            mainFrame.setIconImage(img);
            mainFrame.setTitle("Minesweeper");

            Menu.createButtons();
            Info.createButtons();
            Records.createButtons();
            Password.createButtons();
            NewPassword.createButtons();
            GameSettings.createButtons();
            BattleField.createButtons();

            menu = new Menu();
            mainFrame.add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();

            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getButtons();

            users = HighScoresReader.readPlayers();
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
            ImageIcon icon = new ImageIcon(frameImagePass + "exitGame.gif");

            if (JOptionPane.showConfirmDialog(mainFrame, "           YOU SHURE?", "EXIT",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon) == 0) {
                mainFrame.dispose();
            }
        }

        if (command.equals("RECORDS")) {
            mainFrame.remove(menu.getMenuPanel());
            records = new Records(users);

            mainFrame.add(records.getRecordsPanel(), BorderLayout.CENTER);
            records.getRecordsPanel().setVisible(true);

            mainFrame.repaint();
            records.getRecordsPanel().updateUI();
        }

        if (command.equals("INFO")) {
            mainFrame.remove(menu.getMenuPanel());
            info = new Info();

            mainFrame.add(info.getPanel(), BorderLayout.CENTER);
            info.getPanel().setVisible(true);
            mainFrame.repaint();
            info.getPanel().updateUI();
        }

        if (command.equals("NEW GAME")) {
            mainFrame.remove(menu.getMenuPanel());
            password = new Password(users);
            mainFrame.add(password.getPasswordPanel(), BorderLayout.CENTER);
            password.getPasswordPanel().setVisible(true);
            mainFrame.repaint();
            password.getPasswordPanel().updateUI();
        }

        if (command.equals("TO MENU FROM PASSWORD")) {
            mainFrame.remove(password.getPasswordPanel());

            menu = new Menu();

            mainFrame.add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            mainFrame.repaint();
        }

        if (command.equals("BACK TO MENU FROM NEW PASSWORD")) {
            mainFrame.remove(newPassword.getNewPasswordPanel());

            menu = new Menu();

            mainFrame.add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            mainFrame.repaint();
        }

        if (command.equals("BACK TO MENU FROM INFO")) {
            mainFrame.remove(info.getPanel());

            menu = new Menu();

            mainFrame.add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            mainFrame.repaint();
        }

        if (command.equals("BACK TO MENU FROM RECORDS")) {
            mainFrame.remove(records.getRecordsPanel());

            menu = new Menu();

            mainFrame.add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.getMenuPanel().updateUI();
            mainFrame.repaint();
        }

        if (command.equals("BACK TO PASSWORD")) {
            mainFrame.remove(newPassword.getNewPasswordPanel());
            password = new Password(users);
            mainFrame.add(password.getPasswordPanel(), BorderLayout.CENTER);
            password.getPasswordPanel().updateUI();
        }

        if (command.equals("BACK TO PASSWORD FROM OPTIONS")) {
            mainFrame.remove(gameSettings.getGameSettingsPanel());
            password = new Password(users);
            mainFrame.add(password.getPasswordPanel(), BorderLayout.CENTER);
            password.getPasswordPanel().updateUI();
        }

        if (command.equals("TO NEW PASSWORD FROM PASSWORD")) {
            mainFrame.remove(password.getPasswordPanel());
            newPassword = new NewPassword(users);
            mainFrame.add(newPassword.getNewPasswordPanel(), BorderLayout.CENTER);
            newPassword.getNewPasswordPanel().updateUI();
        }

        if (command.equals("TO OPTIONS FROM PASSWORD")) {
            if (password.checkPassword()) {
                User currentUser = Password.getPlayer();
                mainFrame.remove(password.getPasswordPanel());
                gameSettings = new GameSettings(currentUser);
                gameSettings.defaultSetup();
                gameSettings.setHideCancel(false);

                mainFrame.add(gameSettings.getGameSettingsPanel(), BorderLayout.CENTER);
                gameSettings.getGameSettingsPanel().updateUI();
            }
        }

        if (command.equals("CHECK DATA")) {
            if (newPassword.checkData()) {
                users = HighScoresReader.readPlayers();
                password = new Password(users);
                mainFrame.remove(newPassword.getNewPasswordPanel());
                mainFrame.add(password.getPasswordPanel(), BorderLayout.CENTER);
                password.getPasswordPanel().updateUI();
            }
        }

        if (command.equals("CREATE BATTLEFIELD")) {
            JPanel testBattleField = gameSettings.createMap();

            if (testBattleField != null) {
                battleFieldPanel = testBattleField;

                mainFrame.remove(gameSettings.getGameSettingsPanel());
                mainFrame.add(battleFieldPanel, BorderLayout.CENTER);
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
            mainFrame.remove(battleFieldPanel);
            menu = new Menu();
            mainFrame.add(menu.getMenuPanel(), BorderLayout.CENTER);
            menu.continueButton(true);
            menu.getMenuPanel().updateUI();
            mainFrame.repaint();
        }

        if (command.equals("CONTINUE")) {
            mainFrame.remove(menu.getMenuPanel());
            mainFrame.add(battleFieldPanel, BorderLayout.CENTER);
            battleFieldPanel.updateUI();
            menu.continueButton(false);
            mainFrame.repaint();
        }

        if (command.equals("BACK TO OPTIONS FROM BATTLEFIELD")) {
            mainFrame.remove(battleFieldPanel);

            mainFrame.add(gameSettings.getGameSettingsPanel(), BorderLayout.CENTER);
            gameSettings.setHideCancel(true);
            mainFrame.repaint();
            gameSettings.getGameSettingsPanel().updateUI();
        }

        if (command.equals("BACK TO BATTLEFIELD FROM OPTIONS")) {
            mainFrame.remove(gameSettings.getGameSettingsPanel());

            mainFrame.add(battleFieldPanel, BorderLayout.CENTER);
            gameSettings.setHideCancel(false);
            mainFrame.repaint();
        }

        if (command.equals("UPDATE")) {
            HighScoresReader.updateList(users);
        }
    }
}
