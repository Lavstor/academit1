package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BattleField {
    private JFrame battleField;
    private JButton[] buttons;
    JLabel[][] cellLabels;
    Model m;
    Cell[][] map;
    Player player;
    String cellSkinPass = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cellSkin.jpg";

    public BattleField(int x, int y, int mines, Player player) {
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
        topPanel.add(new JLabel(player.getName()), c2);

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

        m = new Model(x, y, mines);

        map = m.getCell();

        cellLabels = new JLabel[x][y];

        for (int i = 0; i < x; i++) {
            c1.gridy = i;

            for (int j = 0; j < y; j++) {
                c1.gridx = j;
                cellLabels[i][j] = createGifLabel(cellSkinPass);
                gamePanel.add(cellLabels[i][j], c1);
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

        exit.addActionListener(a -> {
            frame.setVisible(false);
        });

    }

    private JLabel createGifLabel(String pass) {
        JLabel newLabel = new JLabel();
        newLabel.setSize(30, 30);
        try {
            BufferedImage img = ImageIO.read(new File(pass));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(newLabel.getWidth(), newLabel.getHeight(), BufferedImage.SCALE_DEFAULT));
icon.setImageObserver(newLabel);
newLabel.setIcon(icon);
            return newLabel;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JLabel("");
    }

    private JLabel createBlackLabel(String pass) {
        ImageIcon icon = new ImageIcon(pass);

        return new JLabel(icon);
    }

}

