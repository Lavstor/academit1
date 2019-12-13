package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

class Menu extends JPanel {
    private static JButton[] buttons = new JButton[4];

    Menu() {
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 5;

        Menu.buttons = buttons;

        JLabel[] gifLabelArray = new JLabel[buttons.length * 2];
        JLabel[] blackLabelArray = new JLabel[gifLabelArray.length];

        for (int i = 0; i < gifLabelArray.length; i++) {
            gifLabelArray[i] = createGifLabel();
            blackLabelArray[i] = createBlackLabel();
        }
        int j = 0;

        for (int i = 0; i < buttons.length; i++) {
            gridBagConstraints.gridy = i;

            gridBagConstraints.gridx = 0;
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.EAST;

            add(gifLabelArray[j], gridBagConstraints);
            add(blackLabelArray[j], gridBagConstraints);
            j++;

            gridBagConstraints.gridx = 1;

            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            add(buttons[i], gridBagConstraints);

            gridBagConstraints.gridx = 2;

            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.anchor = GridBagConstraints.WEST;

            add(gifLabelArray[j], gridBagConstraints);
            add(blackLabelArray[j], gridBagConstraints);
            j++;
        }

        setSize(500, 600);

        buttons[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                gifLabelArray[0].setVisible(true);
                gifLabelArray[1].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                gifLabelArray[0].setVisible(false);
                gifLabelArray[1].setVisible(false);
            }
        });

        buttons[1].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                gifLabelArray[2].setVisible(true);
                gifLabelArray[3].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                gifLabelArray[2].setVisible(false);
                gifLabelArray[3].setVisible(false);
            }
        });

        buttons[2].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                gifLabelArray[4].setVisible(true);
                gifLabelArray[5].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                gifLabelArray[4].setVisible(false);
                gifLabelArray[5].setVisible(false);
            }
        });

        buttons[3].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                gifLabelArray[6].setVisible(true);
                gifLabelArray[7].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                gifLabelArray[6].setVisible(false);
                gifLabelArray[7].setVisible(false);
            }
        });
    }

    private JLabel createGifLabel() {
        ImageIcon icon = new ImageIcon("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/555.gif");
        //ImageIcon icon2 = new ImageIcon("Minesweeper/555.gif");

        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);
        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JLabel createBlackLabel() {
        ImageIcon icon = new ImageIcon("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/Black.jpg");

        return new JLabel(icon);
    }

    static void createButtons() {
        buttons = new JButton[]{new JButton("NEW GAME"), new JButton("RECORDS"), new JButton("INFO"), new JButton("EXIT")};

        Arrays.stream(buttons).forEach(b -> b.setActionCommand(b.getText()));
    }

    static JButton[] getButtons() {
        return buttons;
    }

    void continueButton(boolean b) {
        if (b) {
            buttons[0].setActionCommand("CONTINUE");
            buttons[0].setText("CONTINUE");
        } else {
            buttons[0].setActionCommand("NEW GAME");
            buttons[0].setText("NEW GAME");
        }
    }
}