package ru.academit.school.myskin.minesweeper.model;

import ru.academit.school.myskin.minesweeper.user.Player;
import ru.academit.school.myskin.minesweeper.user.User;

import java.io.*;
import java.util.LinkedList;

public class HighScoresReader {
    private static LinkedList<User> users;
    private static final String playersListPass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/userData/Players.txt";

    public static LinkedList<User> readPlayers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(playersListPass))) {
            //noinspection unchecked
            users = (LinkedList<User>) in.readObject();

            return users;
        } catch (ClassNotFoundException | IOException ignored) {
        }

        return createDefaultPlayerList();
    }

    public static void writeUsers(User user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            assert users != null;

            users.add(user);

            out.writeObject(users);
        } catch (IOException ignored) {
        }
    }

    private static void updateList() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            out.writeObject(users);
        } catch (IOException ignored) {
        }
    }

    public static void updatePlayerData(User player, int newScore) {
        if (player.getScore() < newScore) {
            player.setScore(newScore);
            updateList();
        }
    }

    private static LinkedList<User> createDefaultPlayerList() {
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
