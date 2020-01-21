package ru.academit.school.myskin.minesweeper.gui;

import ru.academit.school.myskin.minesweeper.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Records {
    private JPanel recordsPanel;
    private static JButton back;

    private final JLabel[] exitGifArray;
    private final String imagesDirectory = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/";

    Records(List<User> userList) {
        recordsPanel = new JPanel();

        String pentagram = imagesDirectory + "highScoresImages/pentagram.png";
        String first = imagesDirectory + "highScoresImages/first.png";
        String second = imagesDirectory + "highScoresImages/second.gif";
        String third = imagesDirectory + "highScoresImages/third.gif";
        String fourth = imagesDirectory + "highScoresImages/fourth.png";
        String fifth = imagesDirectory + "highScoresImages/fifth.png";
        String sixth = imagesDirectory + "highScoresImages/sixth.png";
        String seventh = imagesDirectory + "highScoresImages/seventh.png";
        String eighth = imagesDirectory + "highScoresImages/eighth.png";
        String ninth = imagesDirectory + "highScoresImages/ninth.png";
        String tenth = imagesDirectory + "highScoresImages/tenth.png";
        ImageIcon blackImage = new ImageIcon(imagesDirectory + "buttonsImages/black.jpg");

        String[] imagesPassArray = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth};
        JLabel[] topPanel = {new JLabel("###"), new JLabel("NAME"), new JLabel("SCORE"), createGifLabel(pentagram)};

        GridBagConstraints constraints = new GridBagConstraints();

        recordsPanel.setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.CENTER;
        constraints.weighty = 1;
        constraints.weightx = 5;
        constraints.gridy = 0;

        for (int i = 0; i < 4; i++) {
            constraints.gridx = i;

            recordsPanel.add(topPanel[i], constraints);
        }

        userList = userList.stream().sorted(Comparator.comparingDouble(User::getScore).reversed()).collect(Collectors.toList());

        for (int i = 1; i <= 10; i++) {
            constraints.gridy = i;
            constraints.gridx = 0;

            recordsPanel.add(new JLabel("#" + i), constraints);

            constraints.gridx = 1;
            recordsPanel.add(new JLabel(userList.get(i - 1).getName()), constraints);

            constraints.gridx = 2;
            recordsPanel.add(new JLabel(String.valueOf(userList.get(i - 1).getScore())), constraints);

            constraints.gridx = 3;
            recordsPanel.add(createGifLabel(imagesPassArray[i - 1]), constraints);
        }

        constraints.gridy++;
        constraints.gridx = 0;

        exitGifArray = new JLabel[]{createGifLabel(), createGifLabel()};

        constraints.anchor = GridBagConstraints.EAST;

        recordsPanel.add(exitGifArray[0], constraints);
        recordsPanel.add(new JLabel(blackImage), constraints);
        constraints.gridx = 3;

        constraints.anchor = GridBagConstraints.WEST;
        recordsPanel.add(exitGifArray[1], constraints);
        recordsPanel.add(new JLabel(blackImage), constraints);
        constraints.gridx = 1;

        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        recordsPanel.add(back, constraints);

        recordsPanel.setSize(350, 700);
        recordsPanel.setMinimumSize(new Dimension(350, 700));

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                exitGifArray[0].setVisible(true);
                exitGifArray[1].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                exitGifArray[0].setVisible(false);
                exitGifArray[1].setVisible(false);
            }
        });

        recordsPanel.setVisible(false);
    }

    static JButton getBackButton() {
        return back;
    }

    private JLabel createGifLabel() {
        ImageIcon icon = new ImageIcon(imagesDirectory + "buttonsImages/pentagramGif.gif");

        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);

        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JLabel createGifLabel(String pass) {
        ImageIcon icon = new ImageIcon(pass);

        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);

        newLabel.setIcon(icon);

        return newLabel;
    }

    static void createButtons() {
        back = new JButton("BACK");
        back.setBorderPainted(false);
        back.setActionCommand("BACK TO MENU FROM RECORDS");
    }

    JPanel getRecordsPanel() {
        return recordsPanel;
    }
}