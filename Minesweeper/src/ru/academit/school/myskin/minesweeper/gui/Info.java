package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

class Info extends JPanel {
    private JButton back;
    private JDialog info;
    private JLabel[] exitGifs;
    private JLabel[] blackLabel;

    Info() {
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        JDialog info = new JDialog();
        JPanel panelInfo = new JPanel();
        JPanel panelExit = new JPanel();

        String text = "<html>MINESWEEPER<br><br>Game was crated by Myshkin Nikita Alekseevich." +
                "<br><br>Some tips how to beat this:<br><br> 1:" +
                " Click a square, you get a number.<br> That number is the number " +
                "of how many mines<br> are surrounding it.<br><br>2: Mark all the mines<" +
                "that are downright OBVIOUS. <br>Such as eight 1's surrounding a unopened<br>square, it's obviously a mine." +
                "<br><br>3: Finding the mines in 1 blocks helps a lot,<br> because it opens many squares and<br> good hints to 2's and 3's." +
                "<br><br> Have fun!</html>";
        JLabel textArea = new JLabel(text);
        panelInfo.add(textArea);

        JButton back = new JButton("BACK");
        back.setBorderPainted(false);

        panelExit.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();

        JLabel[] exitGifs = {createGifLabel(), createGifLabel()};
        JLabel[] blackLabel = {createBlackLabel(), createBlackLabel()};

        c1.gridy = 0;
        c1.gridx = 0;
        panelExit.add(exitGifs[0],c1);
        panelExit.add(blackLabel[0], c1);

        c1.gridx = 1;
        panelExit.add(back,c1);
        c1.gridx = 2;

        panelExit.add(exitGifs[1],c1);
        panelExit.add(blackLabel[1],c1);

        panelInfo.add(panelExit, BorderLayout.SOUTH);

        info.add(panelInfo);
        info.setSize(350, 420);
        info.setResizable(false);
        info.setVisible(true);
        info.setIconImage(img);

        info.setVisible(false);

        this.back = back;
        this.info = info;
        this.exitGifs = exitGifs;
        this.blackLabel = blackLabel;

    }

    JDialog getInfo() {
        return info;
    }

    JButton getInfoBack() {
        return back;
    }

    private JLabel createGifLabel() {
        String pass = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\555.gif";
        ImageIcon icon = new ImageIcon(pass);
        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);
        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JLabel createBlackLabel() {
        String pass = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\Black.jpg";

        ImageIcon icon = new ImageIcon(pass);

        return new JLabel(icon);
    }

    public JLabel[] getGif() {

        return exitGifs;
    }
}
