package ru.academit.school.myskin.minesweeper;

import ru.academit.school.myskin.minesweeper.cell.Cell;
import ru.academit.school.myskin.minesweeper.cell.DefaultCell;
import ru.academit.school.myskin.minesweeper.user.Player;
import ru.academit.school.myskin.minesweeper.user.User;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class Model {
    private Cell[][] defaultCells;
    private int countOfMines;

    final private int height;
    final private int width;

    public Model(int height, int width, int countOfMines, int firstX, int firstY) {
        this.height = height;
        this.width = width;
        this.countOfMines = countOfMines;
        defaultCells = new DefaultCell[height][width];

        generateCellMap(firstY, firstX);
    }

    private void generateCellMap(int height, int width) {
        Random rnd = new Random();

        DefaultCell[][] map = new DefaultCell[this.height][this.width];

        int minesCount = 0;

        int[][] counts = new int[this.height][this.width];

        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                map[x][y] = new DefaultCell(false);
            }
        }
        while (minesCount != countOfMines) {
            int x = rnd.nextInt(this.height);
            int y = rnd.nextInt(this.width);

            if (minesCount != countOfMines && x != height && y != width && !map[x][y].isMine()) {
                minesCount++;

                map[x][y].setAsMine();

                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        try {
                            counts[x + i][y + j] += 1;
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }
        }

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                try {
                    map[i][j].increaseNearMines(counts[i][j]);
                } catch (NullPointerException ignored) {
                }
            }
        }

        this.defaultCells = map;
    }

    public Cell[][] getDefaultCells() {
        return defaultCells;
    }

   /* public static LinkedList<User> readPlayers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Players.txt"))) {
            //noinspection unchecked
            return (LinkedList<User>) in.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
                LinkedList<User> newPlayers = new LinkedList<>();

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
        } catch (IOException ignored) {
        }

        return null;
    }

    public static void writeUsers(User user) {
        LinkedList<User> users = readPlayers();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
            assert users != null;

            users.add(user);

            out.writeObject(users);
        } catch (IOException ignored) {
        }
    }

    public static void updateList(LinkedList<User> players) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
            out.writeObject(players);
        } catch (IOException ignored) {
        }
    }*/
}
