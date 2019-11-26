package ru.academit.school.myskin.minesweeper;

import ru.academit.school.myskin.minesweeper.gui.Controller;
import ru.academit.school.myskin.minesweeper.gui.Player;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
       // new Controller();
        Player[] players = new Player[10];
    /*    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("RECORDS.txt"))) {
            players[0] = new Player("Jesus Christ", 5000000.0);
            players[1] = new Player("Mr. Hankey", 900000.0);
            players[2] = new Player("Leopold Stotch", 500000.0);
            players[3] = new Player("Jack Tenorman", 100000.0);
            players[4] = new Player("John Connor", 90000.0);
            players[5] = new Player("Kenny McCormick", 10000.0);
            players[6] = new Player("Eric Cartman", 9000.0);
            players[7] = new Player("God", 6000.0);
            players[8] = new Player("Satan", 100.0);
            players[9] = new Player("Mr. Herbert Garrison", 60.0);

            out.writeObject(players);
        }*/
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("RECORDS.txt"))) {
            players = (Player[]) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(players[0].getName());
        System.out.println(players[0].getScore());
    }
}
