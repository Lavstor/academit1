package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;

public class Menu {
    JPanel panel;
    JFrame menu;
    JButton[] buttons;
    JLabel[] gifLabelArray;
    String pass = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\555.gif";
    String pass3 = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\EXIT.gif";


    public Menu(String title) {
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\1d90af957291ec212de2735e65345a40_i-3.jpg");
        JFrame frame = new JFrame(title);
        menu = frame;
        JPanel panel = new JPanel();
        this.panel = panel;
        JButton exit = new JButton("EXIT");
        exit.setForeground(Color.BLACK);

        JButton info = new JButton("INFO");
        info.setForeground(Color.BLACK);

        JButton newGame = new JButton("NEW GAME");
        newGame.setForeground(Color.BLACK);

        JButton records = new JButton("RECORDS");
        records.setForeground(Color.BLACK);

        panel.setLayout(new GridBagLayout());
        JLabel newLabel = new JLabel();

        String pass2 = "C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\Black.jpg";

        ImageIcon icon = new ImageIcon("C:\\Users\\Nikita\\Downloads\\gs-messaging-stomp-websocket-master\\academit2\\Minesweeper\\src\\ru\\academit\\school\\myskin\\minesweeper\\resources\\555.gif");
        icon.setImageObserver(newLabel);

        newLabel.setIcon(icon);
        newLabel.setBackground(Color.RED);
        newLabel.setIcon(icon);

        GridBagConstraints c1 = new GridBagConstraints();

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.ipadx = 40;
        c1.weighty = 1;
        c1.weightx = 5;
        c1.gridx = 1;
        c1.gridy = 0;

        c1.gridx = 0;

        JLabel[] gifLabelArray = new JLabel[8];
        JLabel[] blackLabelArray = new JLabel[8];
        JButton[] buttons = {newGame, records, info, exit};

        this.gifLabelArray = gifLabelArray;
        this.buttons = buttons;
        this.menu = frame;

        for (int i = 0; i < 8; i++) {
            gifLabelArray[i] = createGifLabel(pass);
            blackLabelArray[i] = createBlackLabel(pass2);
        }
        int j = 0;

        for (int i = 0; i < 4; i++) {
            c1.gridy = i;

            c1.gridx = 0;

            panel.add(gifLabelArray[j], c1);
            panel.add(blackLabelArray[j], c1);
            j++;

            c1.gridx = 1;
            panel.add(buttons[i], c1);

            c1.gridx = 2;
            panel.add(gifLabelArray[j], c1);
            panel.add(blackLabelArray[j], c1);
            j++;
        }

        panel.setVisible(true);
        frame.setSize(235, 260);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(img);
        panel.setBackground(Color.black);

        frame.add(panel);

        ArrayList<Object> gradients = new ArrayList<>();
        gradients.add(0.3);
        gradients.add(0.0);
        gradients.add(Color.RED);
        gradients.add(Color.RED);
        gradients.add(Color.BLACK);

        for (JButton button : buttons) {
            button.setBorderPainted(false);
        }

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("Button.select", Color.red);
        UIManager.put("Button.gradient", gradients);
        UIManager.put("Button.foreground", new ColorUIResource(Color.BLACK));
        UIManager.put("OptionPane.background", new ColorUIResource(Color.BLACK));
        UIManager.put("OptionPane.noButtonText", "NOPE");
        UIManager.put("OptionPane.yesButtonText", "YUP");
        UIManager.put("OptionPane.massage", new ColorUIResource(Color.RED));
        UIManager.put("Panel.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Label.foreground", new ColorUIResource(Color.RED));
    }

    private JLabel createGifLabel(String pass) {
        ImageIcon icon = new ImageIcon(pass);
        JLabel newLabel = new JLabel();
        icon.setImageObserver(newLabel);
        newLabel.setVisible(false);
        newLabel.setIcon(icon);

        return newLabel;
    }

    private JLabel createBlackLabel(String pass) {
        ImageIcon icon = new ImageIcon(pass);

        return new JLabel(icon);
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public JLabel[] getLables() {
        return gifLabelArray;
    }

    public JFrame getMenu() {
        return menu;
    }

    public int massageDialog() {
        ImageIcon icon = new ImageIcon(pass3);

        return JOptionPane.showConfirmDialog(menu, "           YOU SHURE?", "EXIT", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
    }

    public void info() {
        JFrame info = new JFrame("INFO");
        JPanel panelInfo = new JPanel();
        StringBuilder text = new StringBuilder();
        text.append("<html>MINESWEEPER<br><br>Game was crated by Myshkin Nikita Alekseevich.");
        text.append("<br><br>Some tips how to beat this:<br> 1:");
        text.append(" Click a square, you get a number.<br> That number is the number ");
        text.append("of how many mines<br> are surrounding it.<br><br>2: Mark all the mines<");
        text.append("that are downright OBVIOUS. <br>Such as eight 1's surrounding a unopened<br>square, it's obviously a mine.");
        text.append("<br><br>3: Finding the mines in 1 blocks helps a lot,<br> because it opens many squares and<br> good hints to 2's and 3's.");
        text.append("<br><br> Have fun!</html>");
        JLabel textArea = new JLabel(text.toString());

        panelInfo.add(textArea);

        info.add(panelInfo);
        info.setSize(350, 400);
        info.setResizable(false);
        info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        info.setVisible(true);
        JButton back = new JButton();

        info.add(buttons[3])
    }
}
