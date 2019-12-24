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
    final private JLabel[] exitGifArray;

    Records(List<User> userList) {
        recordsPanel = new JPanel();
        String pentagram = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/pentagram.png";
        String first = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/first.png";
        String second = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/second.gif";
        String third = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/third.gif";
        String fourth = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/fourth.png";
        String fifth = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/fifth.png";
        String sixth = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/sixth.png";
        String seventh = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/seventh.png";
        String eighth = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/eighth.png";
        String ninth = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/ninth.png";
        String tenth = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/highScoresImages/tenth.png";
        ImageIcon blackImage = new ImageIcon("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/buttonsImages/black.jpg");

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
        ImageIcon icon = new ImageIcon("Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/buttonsImages/pentagramGif.gif");

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

    JPanel getRecordsPanel(){
        return recordsPanel;
    }
}