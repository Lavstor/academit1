package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Info {
    private JPanel infoPanel;
    private static JButton back;

    Info() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        String text = "<html>MINESWEEPER<br><br>Game was crated by Myshkin Nikita Alekseevich." +
                "<br><br>Some tips how to beat this:<br><br> 1:" +
                " Click a square, you get a number.<br> That number is the number " +
                "of how many mines<br> are surrounding it.<br><br>2: Mark all the mines<" +
                "that are downright OBVIOUS. <br>Such as eight 1's surrounding a unopened<br>square, it's obviously a mine." +
                "<br><br>3: Finding the mines in 1 blocks helps a lot,<br> because it opens many squares and<br> good hints to 2's and 3's." +
                "<br><br> Have fun!</html>";

        infoPanel.add(new JLabel(text, JLabel.CENTER), BorderLayout.CENTER);
        infoPanel.add(returnPanel(), BorderLayout.SOUTH);

        infoPanel.setVisible(false);
    }

    private JLabel createGifLabel() {
        String pass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/buttonsImages/pentagramGif.gif";
        ImageIcon icon = new ImageIcon(pass);

        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);

        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JPanel returnPanel() {
        ImageIcon icon = new ImageIcon("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/buttonsImages/black.jpg");
        JPanel panelExit = new JPanel();

        panelExit.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel[] exitGif = {createGifLabel(), createGifLabel()};
        JLabel[] blackLabel = {new JLabel(icon), new JLabel(icon)};

        constraints.gridy = 0;
        constraints.gridx = 0;

        panelExit.add(exitGif[0], constraints);
        panelExit.add(blackLabel[0], constraints);

        constraints.gridx = 1;
        panelExit.add(back, constraints);
        constraints.gridx = 2;

        panelExit.add(exitGif[1], constraints);
        panelExit.add(blackLabel[1], constraints);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                exitGif[0].setVisible(true);
                exitGif[1].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                exitGif[0].setVisible(false);
                exitGif[1].setVisible(false);
            }
        });

        infoPanel.setVisible(true);

        return panelExit;
    }

    static JButton getButton() {
        return back;
    }

    JPanel getPanel() {
        return infoPanel;
    }

    static void createButtons() {
        back = new JButton("BACK");
        back.setBorderPainted(false);
        back.setActionCommand("BACK TO MENU FROM INFO");
    }
}
