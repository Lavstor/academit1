package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Records extends JPanel implements Serializable {
    private static JButton back;
    private JLabel[][] users;
    private JLabel[] exitGif;
    private static List<Player> players;

    Records(List<Player> players) {
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        String topLabel = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot7.png";
        String firstPlace = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot10.png";
        String loser = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\loser.png";
        String second = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\animeS.gif";
        String third = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\234.gif";
        String forth = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot.png";
        String fiv = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot2.png";
        String six = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot3.png";
        String seven = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot4.png";
        String eight = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot5.png";
        String nine = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot8.png";
        String ten = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\hot9.png";
        String[] gifs2 = {firstPlace, second, third, forth, fiv, six, seven, eight, nine, ten, loser};

        JLabel[] upperPanel = {new JLabel("###"), new JLabel("NAME"), new JLabel("SCORE"), createGifs(topLabel)};
        this.users = new JLabel[10][4];

        JButton back = new JButton("BACK");
        Records.back = back;
        back.setBorderPainted(false);

        GridBagConstraints c1 = new GridBagConstraints();

       setLayout(new GridBagLayout());

        c1.fill = GridBagConstraints.CENTER;
        c1.weighty = 1;
        c1.weightx = 5;
        c1.gridy = 0;

        for (int i = 0; i < 4; i++) {
            c1.gridx = i;

            add(upperPanel[i], c1);
        }

        Records.players = players;
        players =  players.stream().sorted(Comparator.comparingDouble(Player::getScore).reversed()).collect(Collectors.toList());

        setDefaultUsers();
        setDefaultRecords();

        for (int i = 1; i <= 10; i++) {
            c1.gridy = i;
            c1.gridx = 0;
            add(new JLabel("#" + i), c1);

            c1.gridx = 1;
           add(new JLabel(players.get(i - 1).getName()), c1);

            c1.gridx = 2;
            add(new JLabel(String.valueOf(players.get(i - 1).getScore())), c1);

            c1.gridx = 3;
            add(createGifs(gifs2[i - 1]), c1);

        }
        c1.gridy++;
        c1.gridx = 0;

        exitGif = new JLabel[]{createGifLabel(), createGifLabel()};

       add(exitGif[0], c1);
       add(createBlackLabel(), c1);
        c1.gridx = 3;

       add(exitGif[1], c1);
        add(createBlackLabel(), c1);
        c1.gridx = 1;

        c1.gridwidth = GridBagConstraints.RELATIVE;
        c1.fill = GridBagConstraints.HORIZONTAL;

        add(back, c1);

        setSize(350, 700);
        setMinimumSize(new Dimension(350, 700));

        back.addActionListener(actionEvent -> setVisible(false));

        addMouseListener(new MouseAdapter() {
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

       setVisible(false);

    }

    static JButton getBackButton() {
        return back;
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


    private JLabel createBlackLabel() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\Black.jpg");

        return new JLabel(icon);
    }

    private JLabel createGifs(String pass) {
        ImageIcon icon = new ImageIcon(pass);
        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);

        newLabel.setIcon(icon);

        return newLabel;
    }

    public static void updatePlayers(LinkedList<Player> players) {
        Records.players = players;
    }
}