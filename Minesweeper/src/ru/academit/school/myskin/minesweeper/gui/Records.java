package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

public class Records {
    private JButton back;
    private JFrame records;
    JLabel[][] users;
    JLabel[] exitGif;

    Records() {
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        JFrame records = new JFrame("RECORDS");
        JPanel recordPanel = new JPanel();
        JLabel[] upperPanel = {new JLabel("#####"), new JLabel("NAME"), new JLabel("SCORE"), new JLabel("")};
        JLabel[][] users = new JLabel[10][4];
        this.users = users;

        JButton back = new JButton("BACK");
        this.back = back;
        back.setBorderPainted(false);

        GridBagConstraints c1 = new GridBagConstraints();

        recordPanel.setLayout(new GridBagLayout());

        c1.fill = GridBagConstraints.CENTER;

        c1.weighty = 1;
        c1.weightx = 5;
        c1.gridy = 0;

        for (int i = 0; i < 4; i++) {
            c1.gridx = i;

            recordPanel.add(upperPanel[i], c1);
        }
        setDefaultUsers();
        setDefaultRecords();

        for (int i = 1; i <= 10; i++) {
            c1.gridy = i;
            c1.gridx = 0;
            recordPanel.add(new JLabel("#" + i), c1);

            c1.gridx = 3;
            recordPanel.add(new JLabel("Icon" + i), c1);
        }
        c1.gridy++;
        c1.gridx = 0;
        JLabel[] gifs = {createGifLabel(), createGifLabel()};

        exitGif = gifs;

        recordPanel.add(exitGif[0], c1);
        recordPanel.add(createBlackLabel(), c1);
        c1.gridx = 3;

        recordPanel.add(exitGif[1], c1);
        recordPanel.add(createBlackLabel(), c1);
        c1.gridx = 1;

        c1.gridwidth = GridBagConstraints.RELATIVE;
        c1.fill = GridBagConstraints.HORIZONTAL;

        recordPanel.add(back, c1);

        records.add(recordPanel);

        records.setSize(350, 550);
         records.setResizable(false);
        records.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        records.setIconImage(img);

        records.setVisible(false);
        this.records = records;
    }

    public JButton getScoreBack() {
        return back;
    }

    public JFrame getRecordsFrame() {
        return records;
    }

    private void setDefaultRecords() {
        for (int i = 1; i < 10; i++) {

            int j = 0;
            users[i][j].setText("#" + i);

            j = 1;
            users[i][j].setText("Name" + i);

            j = 2;
            users[i][j].setText("Score" + i);

            j = 3;
            users[i][j].setText(" Icon" + i);
        }
    }

    private void setDefaultUsers() {
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                users[i][j] = new JLabel();
            }
        }
    }

    private JLabel createGifLabel() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\555.gif");
        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);
        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    public JLabel[] getExitGif() {

        return exitGif;
    }

    private JLabel createBlackLabel() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\Black.jpg");

        return new JLabel(icon);
    }
}
