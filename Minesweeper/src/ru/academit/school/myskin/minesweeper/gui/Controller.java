package ru.academit.school.myskin.minesweeper.gui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class Controller {
    private View view;
    private Menu menu;

    public Controller(Menu menu, View view) {
        this.view = view;
        this.menu = menu;

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
    }

    private void buttonsEvents() {
        menu.getButtons()[3].addActionListener(actionEvent -> {
            menu.getMenu().setVisible(false);

            if (menu.massageDialog() == 0) {
                menu.getMenu().dispatchEvent(new WindowEvent(menu.getMenu(), WindowEvent.WINDOW_CLOSING));
            }
            menu.getMenu().setVisible(true);
        });

        menu.getButtons()[2].addActionListener(actionEvent -> {
            menu.getMenu().setVisible(false);
            menu.info();
        });
    }
}