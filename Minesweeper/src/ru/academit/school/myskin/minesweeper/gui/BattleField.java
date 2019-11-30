package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BattleField {
    private JFrame battleField;
    private JButton[] buttons;
    JLabel[][] cellLabels;
    Model m;
    Cell[][] map;
    Player player;
    String cellSkinPass = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cellSkin.jpg";
    String pint = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cells\\pint 50.gif";
    String krest = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cells\\krest40.gif";


    public BattleField(int width, int height, int mines, Player player) {
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

        m = new Model(height, width, mines);

        map = m.getCell();

        cellLabels = new JLabel[height][width];

        c1.anchor = GridBagConstraints.CENTER;
        c1.insets = new Insets(0, 0, 0, 0);

        for (int i = 0; i < height; i++) {
            c1.gridy = i;

            for (int j = 0; j < width; j++) {
                c1.gridx = j;
                cellLabels[i][j] = createGifLabel(cellSkinPass);

                gamePanel.add(cellLabels[i][j], c1);
            }
        }
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = e.getY() / (gamePanel.getHeight() / height);
                int j = e.getX() / (gamePanel.getWidth() / width);

                if (!map[i][j].isHidden) {
                    if (map[i][j].isMine()) {
                        changeImage(cellLabels[i][j], krest);

                        gameOver();
                    } else if (map[i][j].getMines() == 0) {
                        openAllZero(i, j);
                    } else {
                        changeImage(cellLabels[i][j], pint);

                        JLabel centerLabel = new JLabel(String.valueOf(map[i][j].getMines()), JLabel.CENTER);

                        cellLabels[i][j].add(centerLabel);
                        cellLabels[i][j].getComponent(0).setForeground(Color.RED);
                        cellLabels[i][j].updateUI();
                    }

                    map[i][j].setHidden(true);
                }
            }
        });

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
        newLabel.setSize(60, 60);
        newLabel.setLayout(new BorderLayout());

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

    private void changeImage(JLabel label, String pass) {
        ImageIcon icon = new ImageIcon(pass);
        icon.setImageObserver(label);
        label.setIcon(icon);

    }

    private void gameOver() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].isMine()) {
                    changeImage(cellLabels[i][j], krest);

                    map[i][j].setHidden(true);
                }
            }
        }
    }

    private void openAllZero(int height, int weight) {
        Queue<Integer> queueHeight = new LinkedList<>();
        Queue<Integer> queueWeight = new LinkedList<>();

        queueHeight.add(height);
        queueWeight.add(weight);

        while (!queueHeight.isEmpty()) {
            height = queueHeight.remove();
            weight = queueWeight.remove();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    try {
                        if (!map[height + i][weight + j].isHidden) {
                            if (map[height + i][weight + j].getMines() == 0) {
                                queueHeight.add(height + i);
                                queueWeight.add(weight + j);
                            } else {
                                changeImage(cellLabels[height + i][weight + j], pint);

                                JLabel centerLabel = new JLabel(String.valueOf(map[height + i][weight + j].getMines()), JLabel.CENTER);

                                cellLabels[height + i][weight + j].add(centerLabel);
                                cellLabels[height + i][weight + j].getComponent(0).setForeground(Color.RED);
                                cellLabels[height + i][weight + j].updateUI();
                            }
                        }

                        map[height + i][weight + j].setHidden(true);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
            changeImage(cellLabels[height][weight], pint);

            map[height][weight].setHidden(true);
        }
    }
}

