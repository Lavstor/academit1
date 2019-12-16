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
    private BufferedImage krestImage;
    private ImageIcon pintagramm;
    private ImageIcon cryGirl;
    private ImageIcon sukkubaImage;
    private BufferedImage flagImage;
    private double scoree;
    private int clicks;
    private int cells;
    private static JButton menu;
    private static JButton topPanelNewGame;

    private JLabel score;
    private JPanel gamePanel;
    private JPanel topPanel;
    private JLabel[][] cellLabels;
    private Model m;
    private Cell[][] map;
    private Player player;
    private static List<JButton> buttons = new LinkedList<>();
    private boolean gameOver = false;
    private static JButton updatePlayer;

    private String krest = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/cells/krest3.png";
    private String pint = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/cells/pint40.gif";
    private String flag = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/cells/krest2.png";
    private String sukkuba = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/cells/sukkuba.jpg";
    private String cry = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/cells/cryingGirl.jpg";


    BattleField(int width, int height, int mines, Player player) {
        this.cells = (width * height) - mines;

        topPanel = new JPanel();
        gamePanel = new JPanel();

        score = new JLabel("Your score: ");
        this.player = player;

        GridBagConstraints c1 = new GridBagConstraints();

        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(0, 5, 1, 0);
        c2.weightx = 4;
        c2.weighty = 1;
        c2.anchor = GridBagConstraints.WEST;

        c2.gridx = 0;

        topPanel.add(new JLabel(player.getName()), c2);

        c2.weightx = 10;
        c2.gridx = 5;
        topPanel.add(score, c2);

        c2.insets = new Insets(0, 70, 0, 0);
        c2.fill = GridBagConstraints.BOTH;
        c2.gridx = 9;
        topPanel.add(topPanelNewGame, c2);

        c2.gridx = 10;
        topPanel.add(menu, c2);


        c1.weightx = 1;
        c1.weighty = 1;
        gamePanel.setLayout(new GridBagLayout());

        c1.anchor = GridBagConstraints.CENTER;
        c1.insets = new Insets(1, 1, 1, 1);
        cellLabels = new JLabel[height][width];

        String cellSkinPass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/cells/cellSkin.jpg";
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(cellSkinPass));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < height; i++) {
            c1.gridy = i;

            for (int j = 0; j < width; j++) {
                c1.gridx = j;
                cellLabels[i][j] = createGifLabel(img);

                gamePanel.add(cellLabels[i][j], c1);
            }
        }

        BufferedImage krest = null;
        BufferedImage flag = null;
        pintagramm = new ImageIcon(pint);
        sukkubaImage = new ImageIcon(sukkuba);
        cryGirl = new ImageIcon(cry);

        try {
            krest = ImageIO.read(new File(this.krest));
            flag = ImageIO.read(new File(this.flag));
        } catch (IOException e) {
            e.printStackTrace();
        }
        krestImage = krest;
        flagImage = flag;

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameOver) {
                    try {
                        int i = e.getY() / (gamePanel.getHeight() / height);
                        int j = e.getX() / (gamePanel.getWidth() / width);

                        if (clicks == 0) {
                            m = new Model(height, width, mines, i, j);
                            map = m.getCell();

                            repaint();
                        }
                        clicks++;

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (map[i][j].isHidden()) {
                                if (map[i][j].isMine()) {
                                    changeImage(cellLabels[i][j], pint);

                                    showBombs();
                                    topPanel.setVisible(false);

                                    remove(gamePanel);
                                    add(lostPanel(), BorderLayout.CENTER);
                                } else if (map[i][j].getMines() == 0) {
                                    openAllZero(i, j);

                                    score.setText("Your score: " + scoree);
                                } else {
                                    changeImg(cellLabels[i][j], krestImage);

                                    JLabel centerLabel = new JLabel(String.valueOf(map[i][j].getMines()), JLabel.CENTER);

                                    cellLabels[i][j].add(centerLabel);
                                    cellLabels[i][j].getComponent(0).setForeground(Color.RED);
                                    cellLabels[i][j].updateUI();
                                    cellLabels[i][j].setBackground(Color.BLACK);
                                    scoree++;

                                    score.setText("Your score: " + scoree);

                                    if (scoree == cells) {
                                        topPanel.setVisible(false);
                                        remove(gamePanel);

                                        add(winPanel("SCORE: " + scoree), BorderLayout.CENTER);
                                    }
                                }
                                map[i][j].setHidden(true);
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (map[i][j].isHidden()) {
                                changeImg(cellLabels[i][j], flagImage);
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

    private JLabel createGifLabel(BufferedImage img) {
        JLabel newLabel = new JLabel();
        newLabel.setSize(40, 40);
        newLabel.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(img.getScaledInstance(newLabel.getWidth(), newLabel.getHeight(), BufferedImage.SCALE_DEFAULT));
        newLabel.setIcon(icon);

        return newLabel;
    }

    private void changeImg(JLabel label, BufferedImage img) {
        ImageIcon icon = new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), BufferedImage.SCALE_DEFAULT));
        label.setIcon(icon);
    }


    private void changeImage(JLabel label, String pass) {
        Image img = Toolkit.getDefaultToolkit().getImage(pass);
        ImageIcon icon = new ImageIcon(img);
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
                        if (map[height + i][weight + j].isHidden()) {
                            if (map[height + i][weight + j].getMines() == 0) {
                                queueHeight.add(height + i);
                                queueWeight.add(weight + j);
                            } else {
                                changeImg(cellLabels[height + i][weight + j], krestImage);

                                JLabel centerLabel = new JLabel(String.valueOf(map[height + i][weight + j].getMines()), JLabel.CENTER);

                                cellLabels[height + i][weight + j].add(centerLabel);
                                cellLabels[height + i][weight + j].getComponent(0).setForeground(Color.RED);
                                cellLabels[height + i][weight + j].updateUI();
                            }
                            scoree++;

                            if (scoree == cells) {
                                topPanel.setVisible(false);
                                remove(gamePanel);

                                add(winPanel("SCORE: " + scoree), BorderLayout.CENTER);
                            }
                        }

                        map[height + i][weight + j].setHidden(true);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
            changeImg(cellLabels[height][weight], krestImage);

            map[height][weight].setHidden(true);
        }
    }

    private JPanel winPanel(String text) {
        JPanel winPanel = new JPanel();

        JLabel image = new JLabel();
        image.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        changeImage(image, sukkuba);

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
            }
        });

        return winPanel;
    }

    private void setScore() {
        if (player.getScore() < scoree) {
            player.setScore(scoree);
        }
    }

    private JPanel lostPanel() {
        remove(gamePanel);
        JPanel winPanel = new JPanel();

        JLabel image = new JLabel();
        image.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        changeImage(image, cry);

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
            }
        });
        gameOver = true;

        return winPanel;
    }

    static List<JButton> getButtons() {
        return buttons;
    }

    static void createButtons() {
        menu = new JButton("MENU");
        menu.setActionCommand("MENU");
        menu.setForeground(Color.BLACK);

        topPanelNewGame = new JButton("NEW GAME");
        topPanelNewGame.setActionCommand("BACK TO OPTIONS");

        updatePlayer = new JButton();
        updatePlayer.setActionCommand("UPDATE");

        buttons.add(menu);
        buttons.add(topPanelNewGame);
        buttons.add(updatePlayer);
    }
}

