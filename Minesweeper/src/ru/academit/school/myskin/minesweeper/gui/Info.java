package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Info extends JPanel {
    private static JButton back;

    Info() {
        setLayout(new BorderLayout());

        String text =  "<html>MINESWEEPER<br><br>Game was crated by Myshkin Nikita Alekseevich." +
                "<br><br>Some tips how to beat this:<br><br> 1:" +
                " Click a square, you get a number.<br> That number is the number " +
                "of how many mines<br> are surrounding it.<br><br>2: Mark all the mines<" +
                "that are downright OBVIOUS. <br>Such as eight 1's surrounding a unopened<br>square, it's obviously a mine." +
                "<br><br>3: Finding the mines in 1 blocks helps a lot,<br> because it opens many squares and<br> good hints to 2's and 3's." +
                "<br><br> Have fun!</html>";

        add(new JLabel(text, JLabel.CENTER), BorderLayout.CENTER);
        add(returnPanel(), BorderLayout.SOUTH);

        setVisible(false);
    }

    private JLabel createGifLabel() {
        String pass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/555.gif";
        ImageIcon icon = new ImageIcon(pass);

        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);

        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JPanel returnPanel() {
        ImageIcon icon = new ImageIcon("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/Black.jpg");
        JPanel panelExit = new JPanel();

        panelExit.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();

        JLabel[] exitGif = {createGifLabel(), createGifLabel()};
        JLabel[] blackLabel = {new JLabel(icon), new JLabel(icon)};

        c1.gridy = 0;
        c1.gridx = 0;

        panelExit.add(exitGif[0], c1);
        panelExit.add(blackLabel[0], c1);

        c1.gridx = 1;
        panelExit.add(back, c1);
        c1.gridx = 2;

        panelExit.add(exitGif[1], c1);
        panelExit.add(blackLabel[1], c1);

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

        setVisible(true);

        return panelExit;
    }

    static JButton getButton(){
        return back;
    }

    static void createButtons(){
        back = new JButton("BACK");
        back.setBorderPainted(false);

        back.setActionCommand("BACK INFO");
    }
}
