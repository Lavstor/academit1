package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BattleField {
    private JFrame battleField;
    private JButton[] buttons;
    JLabel[] gifLabelArray;

    public BattleField(int size, int mines, String name) {
        String pass = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\555.gif";

        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        JFrame frame = new JFrame("MINESWEEPER");
        battleField = frame;
        JPanel topPanel = new JPanel();
        JPanel gamePanel = new JPanel();
        JPanel allPanel = new JPanel();
        JButton exit = new JButton("EXIT");
        JLabel score = new JLabel("Your score: ");
        JLabel timer = new JLabel("Timer: ");
        exit.setForeground(Color.BLACK);

        GridBagConstraints c1 = new GridBagConstraints();

        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(0, 5, 0, 0);
        c2.weightx = 4;
        c2.weighty = 1;
        c2.anchor = GridBagConstraints.WEST;

        c2.gridx = 0;
        topPanel.add(new JLabel(name), c2);

        c2.weightx = 10;
        c2.gridx = 5;
        topPanel.add(score, c2);

        c2.gridx = 6;
        topPanel.add(timer, c2);

        c2.anchor = GridBagConstraints.EAST;
        c2.gridx = 10;
        topPanel.add(exit, c2);


        c1.weightx = 1;
        c1.weighty = 1;
        gamePanel.setLayout(new GridBagLayout());
        for (int i = 0; i < size; i++) {
            c1.gridx = i;

            for (int j = 0; j < size; j++) {
                c1.gridy = j;

                gamePanel.add(new JLabel("" + i), c1);
            }
        }

        frame.setSize(500, 500);
        //  frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(img);
        gamePanel.setBackground(Color.black);
        allPanel.setLayout(new BorderLayout());

        allPanel.add(gamePanel, BorderLayout.CENTER);
        allPanel.add(topPanel, BorderLayout.NORTH);

        frame.add(allPanel);

    }

    private JLabel createGifLabel(String pass) {
        ImageIcon icon = new ImageIcon(pass);
        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);
        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JLabel createBlackLabel(String pass) {
        ImageIcon icon = new ImageIcon(pass);

        return new JLabel(icon);
    }
}

