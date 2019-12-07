package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Records extends JPanel implements Serializable {
    private JButton back;
    private JDialog records;
    JLabel[][] users;
    JLabel[] exitGif;
    private List<Player> players = new LinkedList<>();

    Records() {
        readRecords();

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

        JPanel recordPanel = new JPanel();
        JLabel[] upperPanel = {new JLabel("###"), new JLabel("NAME"), new JLabel("SCORE"), createGifs(topLabel)};
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

        players = players.stream().sorted(Comparator.comparingDouble(Player::getScore).reversed()).collect(Collectors.toList());

        setDefaultUsers();
        setDefaultRecords();

        for (int i = 1; i <= 10; i++) {
            c1.gridy = i;
            c1.gridx = 0;
            recordPanel.add(new JLabel("#" + i), c1);

            c1.gridx = 1;
            recordPanel.add(new JLabel(players.get(i - 1).getName()), c1);

            c1.gridx = 2;
            recordPanel.add(new JLabel(String.valueOf(players.get(i - 1).getScore())), c1);

            c1.gridx = 3;
            recordPanel.add(createGifs(gifs2[i - 1]), c1);

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

        JDialog rec = new JDialog();
        this.records = rec;

        rec.setSize(350, 700);
        rec.setMinimumSize(new Dimension(350, 700));
        rec.setIconImage(img);

        rec.add(recordPanel);

        back.addActionListener(actionEvent -> {
            records.dispose();
        });

        records.addMouseListener(new MouseAdapter() {
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

        rec.setVisible(true);

    }

    public JButton getScoreBack() {
        return back;
    }

    public JDialog getRecordsFrame() {
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

    private JLabel createGifs(String pass) {
        ImageIcon icon = new ImageIcon(pass);
        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);

        newLabel.setIcon(icon);

        return newLabel;
    }

    private void readRecords() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Players.txt"))) {
            players = (LinkedList<Player>) in.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
                players.add(new Player("Jesus Christ", 65.0));
                players.add(new Player("Mr. Hankey", 94.0));
                players.add(new Player("Leopold Stotch", 81.0));
                players.add(new Player("Jack Tenorman", 76.0));
                players.add(new Player("John Connor", 61.0));
                players.add(new Player("Kenny McCormick", 1.0));
                players.add(new Player("Eric Cartman", 499.0));
                players.add(new Player("God", 33.0));
                players.add(new Player("Satan", 34.0));
                players.add(new Player("Mr. Herbert Garrison", 31.0));

                out.writeObject(players);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}