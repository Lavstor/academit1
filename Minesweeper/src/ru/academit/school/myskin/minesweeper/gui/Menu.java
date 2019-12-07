package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import static ru.academit.school.myskin.minesweeper.gui.Password.createAndShowGUI;

class Menu extends JPanel {
    Menu() {
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 5;

        JButton[] buttons = {createButton("NEW GAME"), createButton("RECORDS"),
                createButton("INFO"), createButton("EXIT")};

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

        setVisible(true);
        setBackground(Color.black);

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

    private void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("EXIT")) {
            if (massageDialog() == 0) {
                setVisible(false);
            }
        }

        if (command.equals("RECORDS")) {
            new Records();
        }

        if (command.equals("INFO")) {
          new Info();
        }

        if (command.equals("NEW GAME")) {
            createAndShowGUI();
        }
    }

    private JLabel createGifLabel() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads" +
                "\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper" +
                "\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\555.gif");

        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);
        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JLabel createBlackLabel() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\" +
                "gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\" +
                "src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\Black.jpg");

        return new JLabel(icon);
    }

    private int massageDialog() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\" +
                "academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\EXIT.gif");

        return JOptionPane.showConfirmDialog(this, "           YOU SHURE?", "EXIT",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
    }

    private JButton createButton(String name) {
        JButton newButton = new JButton(name);

        newButton.setActionCommand(name);
        newButton.addActionListener(this::actionPerformed);
        newButton.setForeground(Color.BLACK);
        newButton.setBorderPainted(false);

        return newButton;
    }
}
