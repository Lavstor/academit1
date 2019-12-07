package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.academit.school.myskin.minesweeper.gui.Password.createAndShowGUI;

public class MineSweeper extends JFrame {
    private Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
    private JPanel menu;
    private JPanel records;
    private JPanel info;

    public MineSweeper() {
        setLayout(new BorderLayout());
        customUI();

        setVisible(true);
        setIconImage(img);

        menu = new Menu();

        add(menu, BorderLayout.CENTER);

        getButtons();

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        Records.getScoreBack().addActionListener(this::actionPerformed);
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
            menu.setVisible(false);
            records.setVisible(true);
        }

        if (command.equals("INFO")) {
            remove(menu);

            JPanel info = new Info();
            info.setVisible(true);
            add(info, BorderLayout.CENTER);
            
            info.updateUI();
        }

        if (command.equals("NEW GAME")) {
            createAndShowGUI();
        }

        if (command.equals("BACK")) {
            menu.setVisible(true);
            info.setVisible(false);
            records.setVisible(false);
        }
    }
}
