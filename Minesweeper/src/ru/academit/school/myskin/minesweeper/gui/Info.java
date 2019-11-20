package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

class Info {
    private JButton back;
    private JFrame info;

    Info() {
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        JFrame info = new JFrame("INFO");
        JPanel panelInfo = new JPanel();
        StringBuilder text = new StringBuilder();
        text.append("<html>MINESWEEPER<br><br>Game was crated by Myshkin Nikita Alekseevich.");
        text.append("<br><br>Some tips how to beat this:<br><br> 1:");
        text.append(" Click a square, you get a number.<br> That number is the number ");
        text.append("of how many mines<br> are surrounding it.<br><br>2: Mark all the mines<");
        text.append("that are downright OBVIOUS. <br>Such as eight 1's surrounding a unopened<br>square, it's obviously a mine.");
        text.append("<br><br>3: Finding the mines in 1 blocks helps a lot,<br> because it opens many squares and<br> good hints to 2's and 3's.");
        text.append("<br><br> Have fun!</html>");
        JLabel textArea = new JLabel(text.toString());
        panelInfo.add(textArea);

        JButton back = new JButton("BACK");
        back.setBorderPainted(false);

        panelInfo.add(back, BorderLayout.SOUTH);

        info.add(panelInfo);
        info.setSize(350, 400);
        info.setResizable(false);
        info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        info.setVisible(true);
        info.setIconImage(img);

        info.setVisible(false);

        this.back = back;
        this.info = info;
    }

    JFrame getInfo() {
        return info;
    }

    JButton getInfoBack() {
        return back;
    }
}
