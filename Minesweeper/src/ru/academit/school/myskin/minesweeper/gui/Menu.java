package ru.academit.school.myskin.minesweeper.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {

    public Menu(String title) {
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        JFrame frame = new JFrame(title);
        JPanel panel = new JPanel();
        JButton exit = new JButton("EXIT");

        JButton info = new JButton("INFO");
        JButton newGame = new JButton("NEW GAME");
        JButton records = new JButton("RECORDS");

        panel.setLayout(new GridBagLayout());
        JLabel newLabel = new JLabel();

        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\123.gif");
        icon.setImageObserver(newLabel);

        newLabel.setIcon(icon);


        newLabel.setBackground(Color.RED);
        newLabel.setIcon(icon);

        GridBagConstraints c1 = new GridBagConstraints();

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.ipadx = 40;
        c1.weighty = 1;
        c1.weightx = 5;
        c1.gridx = 1;
        c1.gridy = 0;

        c1.gridx = 0;
        panel.add(newLabel, c1);

        c1.gridx = 1;
        panel.add(newGame, c1);

        c1.gridx = 2;
        panel.add(new JLabel(), c1);

        c1.gridy = 1;

        c1.gridx = 0;
        panel.add(new JLabel(), c1);

        c1.gridx = 1;
        panel.add(info, c1);

        c1.gridx = 2;
        panel.add(new JLabel(),c1);

        c1.gridy = 2;

        c1.gridx = 0;
        panel.add(new JLabel(), c1);

        c1.gridx = 1;
        panel.add(records, c1);

        c1.gridx = 2;
        panel.add(new JLabel(), c1);

        c1.gridy = 3;

        c1.gridx = 0;
        panel.add(new JLabel(), c1);

        c1.gridx = 1;
        panel.add(exit, c1);

        c1.gridx = 2;
        panel.add(new JLabel(), c1);

        panel.setVisible(true);
        frame.setSize(180, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(img);
        panel.setBackground(Color.black);

        frame.add(panel);
    }
}

