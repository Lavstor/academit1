package ru.academit.school.myskin.minesweeper.model;

import ru.academit.school.myskin.minesweeper.user.Player;
import ru.academit.school.myskin.minesweeper.user.User;

import java.io.*;
import java.util.LinkedList;

public class HighScoresReader {
    private static final String playersListPass = "Minesweeper/src/ru/academit/school/myskin/minesweeper/resources/userData/Players.txt";

    public static LinkedList<User> readPlayers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(playersListPass))) {
            //noinspection unchecked
            return (LinkedList<User>) in.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            return createDefaultPlayerList();
        } catch (IOException ignored) {
        }

        return null;
    }

    public static void writeUsers(User user) {
        LinkedList<User> users = readPlayers();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            assert users != null;

            users.add(user);

            out.writeObject(users);
        } catch (IOException ignored) {
        }
    }

    public static void updateList(LinkedList<User> players) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            out.writeObject(players);
        } catch (IOException ignored) {
        }
    }

    private static LinkedList<User> createDefaultPlayerList() {
        LinkedList<User> newPlayers = new LinkedList<>();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playersListPass))) {
            newPlayers.add(new Player("Jesus Christ", 65.0));
            newPlayers.add(new Player("Mr. Hankey", 94.0));
            newPlayers.add(new Player("Leopold Stotch", 81.0));
            newPlayers.add(new Player("Jack Tenorman", 76.0));
            newPlayers.add(new Player("John Connor", 61.0));
            newPlayers.add(new Player("Kenny McCormick", 1.0));
            newPlayers.add(new Player("Eric Cartman", 499.0));
            newPlayers.add(new Player("God", 33.0));
            newPlayers.add(new Player("Satan", 34.0));
            newPlayers.add(new Player("Mr. Herbert Garrison", 31.0));
            out.writeObject(newPlayers);

            return newPlayers;
        } catch (IOException ignored) {
        }

        return newPlayers;
    }
}
