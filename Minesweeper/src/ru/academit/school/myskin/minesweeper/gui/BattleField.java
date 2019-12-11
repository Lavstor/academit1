package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BattleField extends JPanel {
    private double scoree;
    private int clicks;
    private int cells;
    private static JButton endGame;
    private static JButton newGame;
    private static JButton menu;

    private JLabel score;
    private JPanel allPanel;
    private JPanel gamePanel;
    private JPanel topPanel;
    private JLabel[][] cellLabels;
    private Model m;
    private Cell[][] map;
    private Player player;
    private static List<JButton> buttons = new LinkedList<>();

    private String krest = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cells\\krest3.png";
    private String pint = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cells\\pint 50.gif";
    private String flag = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cells\\krest2.png";


    BattleField(int width, int height, int mines, Player player) {
        this.cells = (width * height) - mines;

        topPanel = new JPanel();
        gamePanel = new JPanel();
        allPanel = new JPanel();

        score = new JLabel("Your score: ");
        JLabel timer = new JLabel("Timer: ");
        this.player = player;

        GridBagConstraints c1 = new GridBagConstraints();

        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(0, 5, 0, 0);
        c2.weightx = 4;
        c2.weighty = 1;
        c2.anchor = GridBagConstraints.WEST;

        c2.gridx = 0;
        System.out.println(player);
        topPanel.add(new JLabel(player.getName()), c2);

        c2.weightx = 10;
        c2.gridx = 5;
        topPanel.add(score, c2);

        c2.gridx = 6;
        topPanel.add(timer, c2);

        c2.anchor = GridBagConstraints.EAST;
        c2.gridx = 10;
        topPanel.add(menu, c2);


        c1.weightx = 1;
        c1.weighty = 1;
        gamePanel.setLayout(new GridBagLayout());

        c1.anchor = GridBagConstraints.CENTER;
        c1.insets = new Insets(0, 0, 0, 0);
        cellLabels = new JLabel[height][width];

        for (int i = 0; i < height; i++) {
            c1.gridy = i;

            for (int j = 0; j < width; j++) {
                c1.gridx = j;
                String cellSkinPass = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cellSkin.jpg";
                cellLabels[i][j] = createGifLabel(cellSkinPass);

                gamePanel.add(cellLabels[i][j], c1);
            }
        }

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = e.getY() / (gamePanel.getHeight() / height);
                int j = e.getX() / (gamePanel.getWidth() / width);

                if (clicks == 0) {
                    m = new Model(height, width, mines, i, j);
                    map = m.getCell();

                    repaint();
                }
                clicks++;

                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (!map[i][j].isHidden) {
                        if (map[i][j].isMine()) {
                            changeImage(cellLabels[i][j], pint);

                            showBombs();
                            endGame("U LOSE");
                            setScore(scoree);

                        } else if (map[i][j].getMines() == 0) {
                            openAllZero(i, j);

                            score.setText("Your score: " + scoree);
                        } else {
                            changeImg(cellLabels[i][j], krest);

                            JLabel centerLabel = new JLabel(String.valueOf(map[i][j].getMines()), JLabel.CENTER);

                            cellLabels[i][j].add(centerLabel);
                            cellLabels[i][j].getComponent(0).setForeground(Color.RED);
                            cellLabels[i][j].updateUI();
                            cellLabels[i][j].setBackground(Color.BLACK);
                            scoree++;

                            score.setText("Your score: " + scoree);

                            if (scoree == cells) {
                                topPanel.setVisible(false);
                                gamePanel.setVisible(false);

                                allPanel.add(winPanel("SCORE: " + scoree));

                                setScore(scoree);
                            }
                        }

                        map[i][j].setHidden(true);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (!map[i][j].isHidden) {
                        changeImg(cellLabels[i][j], flag);
                    }
                }
            }
        });

        setVisible(true);

        gamePanel.setBackground(Color.black);
        allPanel.setLayout(new BorderLayout());

        allPanel.add(gamePanel, BorderLayout.CENTER);
        allPanel.add(topPanel, BorderLayout.NORTH);

        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        repaint();
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

    private void changeImg(JLabel label, String pass) {
        try {
            BufferedImage img = ImageIO.read(new File(pass));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), BufferedImage.SCALE_DEFAULT));
            icon.setImageObserver(label);
            label.setIcon(icon);
        } catch (IOException e) {
            System.out.println("dfsfs");
        }
    }

    private void changeImage(JLabel label, String pass) {
        ImageIcon icon = new ImageIcon(pass);
        icon.setImageObserver(label);
        label.setIcon(icon);

    }

    private void showBombs() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].isMine()) {
                    changeImage(cellLabels[i][j], pint);

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
                                changeImg(cellLabels[height + i][weight + j], krest);

                                JLabel centerLabel = new JLabel(String.valueOf(map[height + i][weight + j].getMines()), JLabel.CENTER);

                                cellLabels[height + i][weight + j].add(centerLabel);
                                cellLabels[height + i][weight + j].getComponent(0).setForeground(Color.RED);
                                cellLabels[height + i][weight + j].updateUI();
                            }
                            scoree++;

                            if (scoree == cells) {
                                topPanel.setVisible(false);
                                gamePanel.setVisible(false);

                                allPanel.add(winPanel("SCORE: " + scoree));

                                setScore(scoree);
                            }
                        }

                        map[height + i][weight + j].setHidden(true);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
            changeImg(cellLabels[height][weight], krest);

            map[height][weight].setHidden(true);
        }
    }

    private JPanel winPanel(String text) {
        JPanel winPanel = new JPanel();

        JLabel image = new JLabel();
        image.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        String sukkuba = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\cells\\sukkuba.jpg";
        changeImage(image, sukkuba);

        JLabel centerLabel = new JLabel(text, JLabel.CENTER);
        centerLabel.setFont(new Font("Arial Black", Font.BOLD, 20));

        centerLabel.setSize(600, 200);
        centerLabel.setForeground(Color.BLACK);

        image.add(centerLabel);


        winPanel.add(image);
        winPanel.setBackground(Color.RED);
        image.updateUI();

        return winPanel;
    }

    private JDialog endGame(String string) {
        JDialog dialog = new JDialog();
        JLabel label = new JLabel(string);
        JPanel infoPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttons.add(newGame);
        buttons.add(endGame);

        label.setFont(new Font("Arial Black", Font.BOLD, 20));


        dialog.setLayout(new BorderLayout());

        dialog.add(infoPanel, BorderLayout.CENTER);


        infoPanel.add(label);

        buttonPanel.setLayout(new GridLayout(1, 2, 5, 0));
        buttonPanel.add(newGame);
        buttonPanel.add(endGame);

        flow.add(buttonPanel);
        dialog.add(flow, BorderLayout.SOUTH);

        dialog.setVisible(true);
        dialog.setSize(450, 150);


        return dialog;
    }

    private void setScore(double score) {
        player.setScore(score);
    }

    static List<JButton> getButtons() {
        return buttons;
    }

    static void createButtons(){
        endGame = new JButton("MENU");
        endGame.setActionCommand("END GAME");

        newGame = new JButton("NEW GAME");
        newGame.setActionCommand("OPTIONS");

        menu = new JButton("MENU");
        menu.setActionCommand("MENU");
        menu.setForeground(Color.BLACK);

        buttons.add(menu);
        buttons.add(newGame);
        buttons.add(endGame);
    }
}

