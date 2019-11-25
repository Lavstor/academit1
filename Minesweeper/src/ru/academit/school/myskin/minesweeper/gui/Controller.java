package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class Controller {
    private View view;
    private Menu menu;
    private Info info;
    private Records records;

    public Controller() {
        this.view = new View();
        this.menu = new Menu();
        this.info = new Info();
        this.records = new Records();

        mouseEvents();
        buttonsEvents();
    }

    private void mouseEvents() {
        JLabel[] labelArray = menu.gifLabelArray;

        menu.getButtons()[0].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                labelArray[0].setVisible(true);
                labelArray[1].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                labelArray[0].setVisible(false);
                labelArray[1].setVisible(false);
            }
        });

        menu.getButtons()[1].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                labelArray[2].setVisible(true);
                labelArray[3].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                labelArray[2].setVisible(false);
                labelArray[3].setVisible(false);
            }
        });

        menu.getButtons()[2].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                labelArray[4].setVisible(true);
                labelArray[5].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                labelArray[4].setVisible(false);
                labelArray[5].setVisible(false);
            }
        });

        menu.getButtons()[3].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                labelArray[6].setVisible(true);
                labelArray[7].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                labelArray[6].setVisible(false);
                labelArray[7].setVisible(false);
            }
        });

        records.getScoreBack().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                records.getExitGif()[0].setVisible(true);
                records.getExitGif()[1].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                records.getExitGif()[0].setVisible(false);
                records.getExitGif()[1].setVisible(false);
            }
        });

        info.getInfoBack().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                info.getGif()[0].setVisible(true);
                info.getGif()[1].setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                info.getGif()[0].setVisible(false);
                info.getGif()[1].setVisible(false);
            }
        });
    }

    private void buttonsEvents() {
        menu.getButtons()[3].addActionListener(actionEvent -> {
         //   menu.getMenu().setVisible(false);

            if (menu.massageDialog() == 0) {
                menu.getMenu().dispatchEvent(new WindowEvent(menu.getMenu(), WindowEvent.WINDOW_CLOSING));
            }
            menu.getMenu().setVisible(true);
        });

        menu.getButtons()[2].addActionListener(actionEvent -> {
         //   menu.getMenu().setVisible(false);

            info.getInfo().setVisible(true);
        });

        info.getInfoBack().addActionListener(actionEvent -> {
          //  menu.getMenu().setVisible(true);

            info.getInfo().setVisible(false);
        });

        menu.getButtons()[1].addActionListener(actionEvent -> {
           // menu.getMenu().setVisible(false);

            records.getRecordsFrame().setVisible(true);
        });

        records.getScoreBack().addActionListener(actionEvent -> {
       //     menu.getMenu().setVisible(true);

            records.getRecordsFrame().setVisible(false);
        });


    }
}