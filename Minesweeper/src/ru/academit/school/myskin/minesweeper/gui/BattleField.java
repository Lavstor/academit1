package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.Cell;
import ru.academit.school.myskin.minesweeper.Model;
import ru.academit.school.myskin.minesweeper.Player;

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
    private BufferedImage crossImage;
    private BufferedImage flagImage;
    private double currentScore;
    private int clicks;
    private int cells;
    private static JButton menu;
    private static JButton topPanelNewGame;
    private JLabel score;
    private JPanel gamePanel;
    private JPanel topPanel;
    private JLabel[][] cellLabels;
    private Model model;
    private Cell[][] map;
    private Player player;
    private static List<JButton> buttons = new LinkedList<>();
    private boolean gameOver = false;
    private static JButton updatePlayer;
    private String pentagramPass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/mine.gif";

    BattleField(int width, int height, int mines, Player player) {
        this.cells = (width * height) - mines;

        topPanel = new JPanel();
        gamePanel = new JPanel();

        score = new JLabel("Your score: ");
        this.player = player;

        GridBagConstraints constraints1 = new GridBagConstraints();

        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.insets = new Insets(0, 5, 1, 0);
        constraints2.weightx = 4;
        constraints2.weighty = 1;
        constraints2.anchor = GridBagConstraints.WEST;

        constraints2.gridx = 0;

        topPanel.add(new JLabel(player.getName()), constraints2);

        constraints2.weightx = 10;
        constraints2.gridx = 5;
        topPanel.add(score, constraints2);

        constraints2.insets = new Insets(0, 70, 0, 0);
        constraints2.fill = GridBagConstraints.BOTH;
        constraints2.gridx = 9;
        topPanel.add(topPanelNewGame, constraints2);

        constraints2.gridx = 10;
        topPanel.add(menu, constraints2);

        constraints1.weightx = 1;
        constraints1.weighty = 1;

        gamePanel.setLayout(new GridBagLayout());

        constraints1.anchor = GridBagConstraints.CENTER;
        constraints1.insets = new Insets(1, 1, 1, 1);
        cellLabels = new JLabel[height][width];

        String cellSkinPass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/cellSkin.jpg";

        try {
            BufferedImage img = ImageIO.read(new File(cellSkinPass));

            for (int i = 0; i < height; i++) {
                constraints1.gridy = i;

                for (int j = 0; j < width; j++) {
                    constraints1.gridx = j;

                    cellLabels[i][j] = createCellLabel(img);

                    gamePanel.add(cellLabels[i][j], constraints1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage krest = null;
        BufferedImage flag = null;

        try {
            String crossImagePass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/notMine.png";
            krest = ImageIO.read(new File(crossImagePass));
            String flag1 = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/flag.png";
            flag = ImageIO.read(new File(flag1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        crossImage = krest;
        flagImage = flag;

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (!gameOver) {
                    try {
                        int i = mouseEvent.getY() / (gamePanel.getHeight() / height);
                        int j = mouseEvent.getX() / (gamePanel.getWidth() / width);

                        if (clicks == 0) {
                            model = new Model(height, width, mines, i, j);
                            map = model.getCells();

                            repaint();
                        }
                        clicks++;

                        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                            if (map[i][j].isHidden()) {
                                if (map[i][j].isMine()) {
                                    setGifIcon(cellLabels[i][j], pentagramPass);

                                    showBombs();
                                    topPanel.setVisible(false);

                                    remove(gamePanel);
                                    add(lostPanel(), BorderLayout.CENTER);
                                } else if (map[i][j].getMines() == 0) {
                                    openAllZero(i, j);

                                    score.setText("Your score: " + currentScore);
                                } else {
                                    setIcon(cellLabels[i][j], crossImage);

                                    JLabel centerLabel = new JLabel(String.valueOf(map[i][j].getMines()), JLabel.CENTER);

                                    cellLabels[i][j].add(centerLabel);
                                    cellLabels[i][j].getComponent(0).setForeground(Color.RED);
                                    cellLabels[i][j].updateUI();
                                    cellLabels[i][j].setBackground(Color.BLACK);
                                    currentScore++;

                                    score.setText("Your score: " + currentScore);

                                    if (currentScore == cells) {
                                        topPanel.setVisible(false);
                                        remove(gamePanel);

                                        add(winPanel("SCORE: " + currentScore), BorderLayout.CENTER);
                                    }
                                }
                                map[i][j].setHidden(true);
                            }
                        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                            if (map[i][j].isHidden()) {
                                setIcon(cellLabels[i][j], flagImage);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        });

        setVisible(true);

        gamePanel.setBackground(Color.black);

        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        repaint();
    }

    private JLabel createCellLabel(BufferedImage img) {
        JLabel newLabel = new JLabel();

        newLabel.setSize(40, 40);
        newLabel.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(img.getScaledInstance(newLabel.getWidth(), newLabel.getHeight(), BufferedImage.SCALE_DEFAULT));
        newLabel.setIcon(icon);

        return newLabel;
    }

    private void setIcon(JLabel label, BufferedImage img) {
        ImageIcon icon = new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), BufferedImage.SCALE_DEFAULT));
        label.setIcon(icon);
    }

    private void setGifIcon(JLabel label, String pass) {
        Image img = Toolkit.getDefaultToolkit().getImage(pass);
        ImageIcon icon = new ImageIcon(img);

        icon.setImageObserver(label);
        label.setIcon(icon);
    }

    private void showBombs() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].isMine()) {
                    setGifIcon(cellLabels[i][j], pentagramPass);

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
                        if (map[height + i][weight + j].isHidden()) {
                            if (map[height + i][weight + j].getMines() == 0) {
                                queueHeight.add(height + i);
                                queueWeight.add(weight + j);
                            } else {
                                setIcon(cellLabels[height + i][weight + j], crossImage);

                                JLabel centerLabel = new JLabel(String.valueOf(map[height + i][weight + j].getMines()), JLabel.CENTER);

                                cellLabels[height + i][weight + j].add(centerLabel);
                                cellLabels[height + i][weight + j].getComponent(0).setForeground(Color.RED);
                                cellLabels[height + i][weight + j].updateUI();
                            }
                            currentScore++;

                            if (currentScore == cells) {
                                topPanel.setVisible(false);
                                remove(gamePanel);

                                add(winPanel("SCORE: " + currentScore), BorderLayout.CENTER);
                            }
                        }

                        map[height + i][weight + j].setHidden(true);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
            setIcon(cellLabels[height][weight], crossImage);

            map[height][weight].setHidden(true);
        }
    }

    private JPanel winPanel(String text) {
        JPanel winPanel = new JPanel();

        JLabel image = new JLabel();
        image.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        setGifIcon(image, "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/winImage.jpg");

        JLabel centerLabel = new JLabel(text, JLabel.CENTER);
        centerLabel.setFont(new Font("Arial Black", Font.BOLD, 20));

        centerLabel.setSize(600, 200);
        centerLabel.setForeground(Color.BLACK);

        image.add(centerLabel);


        winPanel.add(image);
        winPanel.setBackground(Color.RED);
        image.updateUI();

        winPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                topPanel.setVisible(true);
                remove(winPanel);

                add(gamePanel, BorderLayout.CENTER);
                gamePanel.updateUI();

                setScore();
                updatePlayer.doClick();

                gameOver = true;
            }
        });

        return winPanel;
    }

    private void setScore() {
        if (player.getScore() < currentScore) {
            player.setScore(currentScore);
        }
    }

    private JPanel lostPanel() {
        remove(gamePanel);
        JPanel winPanel = new JPanel();

        JLabel image = new JLabel();
        image.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        String cry = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/looseImage.jpg";
        setGifIcon(image, cry);

        JLabel centerLabel = new JLabel("                          YOU LOST", JLabel.CENTER);
        centerLabel.setFont(new Font("Arial Black", Font.BOLD, 40));

        centerLabel.setSize(600, 200);
        centerLabel.setForeground(Color.RED);

        image.add(centerLabel);


        winPanel.add(image);
        winPanel.setBackground(Color.BLACK);
        image.updateUI();

        winPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                topPanel.setVisible(true);
                remove(winPanel);

                add(gamePanel, BorderLayout.CENTER);
                gamePanel.updateUI();

                setScore();
                updatePlayer.doClick();

                gameOver = true;
            }
        });

        return winPanel;
    }

    static List<JButton> getButtons() {
        return buttons;
    }

    static void createButtons() {
        menu = new JButton("MENU");
        menu.setActionCommand("BACK TO MENU FROM BATTLEFIELD");
        menu.setForeground(Color.BLACK);

        topPanelNewGame = new JButton("NEW GAME");
        topPanelNewGame.setActionCommand("BACK TO OPTIONS FROM BATTLEFIELD");

        updatePlayer = new JButton();
        updatePlayer.setActionCommand("UPDATE");

        buttons.add(menu);
        buttons.add(topPanelNewGame);
        buttons.add(updatePlayer);
    }
}

