package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.model.FieldCreator;
import ru.academit.school.myskin.minesweeper.user.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

class BattleField {
    private FieldCreator fieldCreator;
    private int currentScore;
    private int cellsCount;
    private boolean gameOver = false;
    private JPanel battleFieldPanel;

    private static JButton menu;
    private static JButton topPanelNewGame;
    private static List<JButton> buttons = new LinkedList<>();
    private static JButton updatePlayer;

    final private BufferedImage cellImage;
    final private BufferedImage crossImage;
    final private BufferedImage flagImage;
    final private User user;
    final private JLabel score;
    final private JPanel gamePanel;
    final private JPanel topPanel;
    final private JLabel[][] cellLabels;

    BattleField(int width, int height, int mines, User user) {
        this.battleFieldPanel = new JPanel();
        this.cellsCount = (width * height) - mines;

        topPanel = new JPanel();
        gamePanel = new JPanel();

        score = new JLabel("Your score: ");
        this.user = user;

        GridBagConstraints constraints1 = new GridBagConstraints();

        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.insets = new Insets(0, 5, 1, 0);
        constraints2.weightx = 4;
        constraints2.weighty = 1;
        constraints2.anchor = GridBagConstraints.WEST;

        constraints2.gridx = 0;

        topPanel.add(new JLabel(user.getName()), constraints2);

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

        cellImage = getBufferedImage(cellSkinPass);

        for (int i = 0; i < height; i++) {
            constraints1.gridy = i;

            for (int j = 0; j < width; j++) {
                constraints1.gridx = j;

                assert cellImage != null;
                cellLabels[i][j] = createCellLabel(cellImage);

                gamePanel.add(cellLabels[i][j], constraints1);
            }
        }

        crossImage = getBufferedImage("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/notMine.png");
        flagImage = getBufferedImage("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/flag.png");

        gamePanel.addMouseListener(new MouseAdapter() {
            boolean fieldCreated;

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int i = mouseEvent.getY() / (gamePanel.getHeight() / height);
                int j = mouseEvent.getX() / (gamePanel.getWidth() / width);

                if (!gameOver) {
                    if (!fieldCreated) {
                        fieldCreator = new FieldCreator(height, width, mines, j, i);
                        fieldCreated = true;
                    }

                    LinkedList<Integer[]> cellCoordinatesList = new LinkedList<>();

                    if (i >= 0 && j >= 0 && i < cellLabels.length && j < cellLabels[i].length) {
                        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                            cellCoordinatesList = fieldCreator.openCell(i, j);
                        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                            fieldCreator.markCell(i, j);
                            cellCoordinatesList.add(new Integer[]{i, j});
                        } else if (mouseEvent.getButton() == MouseEvent.BUTTON2) {
                            cellCoordinatesList = fieldCreator.massPush(i, j);
                        }
                    }

                    if (cellCoordinatesList != null) {
                        paintField(cellCoordinatesList);
                    }
                }
            }
        });

        battleFieldPanel.setVisible(true);

        gamePanel.setBackground(Color.black);

        battleFieldPanel.setLayout(new BorderLayout());
        battleFieldPanel.add(gamePanel, BorderLayout.CENTER);
        battleFieldPanel.add(topPanel, BorderLayout.NORTH);

        battleFieldPanel.repaint();
    }

    private void paintField(LinkedList<Integer[]> cellCoordinatesList) {
        while (!cellCoordinatesList.isEmpty()) {
            Integer[] coordinates = cellCoordinatesList.removeFirst();

            if (!fieldCreator.cellIsHiddenCheck(coordinates[0], coordinates[1])) {
                if (fieldCreator.cellIsMineCheck(coordinates[0], coordinates[1])) {
                    String pentagramPass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/mine.gif";
                    setGifIcon(cellLabels[coordinates[0]][coordinates[1]], pentagramPass);

                    gameOver = true;
                } else {
                    paintCrossCell(coordinates[0], coordinates[1]);
                }
            } else {
                if (!fieldCreator.cellIsMarkedCheck(coordinates[0], coordinates[1])) {
                    setIcon(cellLabels[coordinates[0]][coordinates[1]], flagImage);
                } else {
                    setIcon(cellLabels[coordinates[0]][coordinates[1]], cellImage);
                }
            }
        }

        currentScore = fieldCreator.getScore();
        score.setText("Score " + currentScore);

        if (gameOver) {
            topPanel.setVisible(false);

            battleFieldPanel.remove(gamePanel);
            battleFieldPanel.add(lostPanel(), BorderLayout.CENTER);
        } else if (currentScore == cellsCount) {
            topPanel.setVisible(false);

            battleFieldPanel.remove(gamePanel);
            battleFieldPanel.add(winPanel(), BorderLayout.CENTER);

            gameOver = true;
        }
    }

    private void paintCrossCell(int i, int j) {
        assert crossImage != null;
        setIcon(cellLabels[i][j], crossImage);
        int nearMinesCount = fieldCreator.getNearMines(i, j);

        if (nearMinesCount > 0) {
            JLabel centerLabel = new JLabel(String.valueOf(nearMinesCount), JLabel.CENTER);

            cellLabels[i][j].add(centerLabel);
            cellLabels[i][j].getComponent(0).setForeground(Color.RED);
            cellLabels[i][j].updateUI();
            cellLabels[i][j].setBackground(Color.BLACK);
        }
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

    private JPanel winPanel() {
        JPanel winPanel = new JPanel();

        JLabel image = new JLabel();
        image.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        setGifIcon(image, "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/winImage.jpg");

        JLabel centerLabel = new JLabel("YOU WIN! SCORE: " + fieldCreator.getScore(), JLabel.CENTER);
        centerLabel.setFont(new Font("Arial Black", Font.BOLD, 20));

        centerLabel.setSize(600, 200);
        centerLabel.setForeground(Color.BLACK);

        image.add(centerLabel);

        winPanel.add(image);
        winPanel.setBackground(Color.RED);
        image.updateUI();

        winPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                topPanel.setVisible(true);
                battleFieldPanel.remove(winPanel);

                battleFieldPanel.add(gamePanel, BorderLayout.CENTER);
                gamePanel.updateUI();

                setScore();
                updatePlayer.doClick();
            }
        });

        return winPanel;
    }

    private JPanel lostPanel() {
        battleFieldPanel.remove(gamePanel);
        JPanel lostPanel = new JPanel();

        JLabel image = new JLabel();
        image.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        String cry = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/battlefield/looseImage.jpg";
        setGifIcon(image, cry);

        JLabel centerLabel = new JLabel("                          YOU LOST", JLabel.CENTER);
        centerLabel.setFont(new Font("Arial Black", Font.BOLD, 40));

        centerLabel.setSize(600, 200);
        centerLabel.setForeground(Color.RED);

        image.add(centerLabel);

        lostPanel.add(image);
        lostPanel.setBackground(Color.BLACK);
        image.updateUI();

        lostPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                topPanel.setVisible(true);
                battleFieldPanel.remove(lostPanel);

                battleFieldPanel.add(gamePanel, BorderLayout.CENTER);
                gamePanel.updateUI();
            }
        });

        return lostPanel;
    }

    private void setScore() {
        if (user.getScore() < currentScore) {
            user.setScore(currentScore);
        }
    }

    private BufferedImage getBufferedImage(String pass) {
        try {
            return ImageIO.read(new File(pass));
        } catch (IOException ignored) {
        }

        return null;
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

    JPanel getBattleFieldPanel() {
        return battleFieldPanel;
    }
}

