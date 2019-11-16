package ru.academit.school.myskin.minesweeper;

import ru.academit.school.myskin.minesweeper.gui.Controller;
import ru.academit.school.myskin.minesweeper.gui.Menu;
import ru.academit.school.myskin.minesweeper.gui.View;

public class Main {
    public static void main(String[] args){
        Menu menu = new Menu("123");
        View v = new View();
        Controller c = new Controller(menu, v);
    }
}
