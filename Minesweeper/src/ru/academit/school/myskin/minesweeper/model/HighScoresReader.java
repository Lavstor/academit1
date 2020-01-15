package ru.academit.school.myskin.minesweeper.model;

import ru.academit.school.myskin.minesweeper.user.Player;
import ru.academit.school.myskin.minesweeper.user.User;

import java.io.*;
import java.util.LinkedList;

public class HighScoresReader {
    private LinkedList<User> users;
    private final String playersListPass;

    public HighScoresReader(String pass) throws IOException, ClassNotFoundException {
        playersListPass = pass;
        users = readPlayers();
    }

    public LinkedList<User> getUsersList(){
        return users;
    }

    private LinkedList<User> readPlayers() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("213"));

        //noinspection unchecked
       return (LinkedList<User>) in.readObject();
    }

    public  void writeUsers(User user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            users.add(user);

            out.writeObject(users);
        } catch (IOException ignored) {
        }
    }

    private  void updateList() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

    public  void updatePlayerData(User player, int newScore) {
        if (player.getScore() < newScore) {
            player.setScore(newScore);
            updateList();
        }
    }

    private  LinkedList<User> createDefaultPlayerList() {
        users = new LinkedList<>();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            users.add(new Player("Jesus Christ", 65.0));
            users.add(new Player("Mr. Hankey", 94.0));
            users.add(new Player("Leopold Stotch", 81.0));
            users.add(new Player("Jack Tenorman", 76.0));
            users.add(new Player("John Connor", 61.0));
            users.add(new Player("Kenny McCormick", 1.0));
            users.add(new Player("Eric Cartman", 499.0));
            users.add(new Player("God", 33.0));
            users.add(new Player("Satan", 34.0));
            users.add(new Player("Mr. Herbert Garrison", 31.0));
            out.writeObject(users);

        } catch (IOException ignored) {
        }

        return users;
    }
}
