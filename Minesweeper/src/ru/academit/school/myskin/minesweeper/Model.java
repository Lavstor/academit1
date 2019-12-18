package ru.academit.school.myskin.minesweeper;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class Model {
    private Cell[][] cells;
    final private int HEIGHT;
    final private int WIDTH;
    private int countOfMines;

    public Model(int height, int width, int countOfMines, int firstY, int firstX) {
        HEIGHT = height;
        WIDTH = width;
        this.countOfMines = countOfMines;
        cells = new Cell[height][width];

        generateMap(firstY, firstX);
    }

    private void generateMap(int height, int width) {
        Random rnd = new Random();

        Cell[][] map = new Cell[HEIGHT][WIDTH];

        int minesCount = 0;

        int[][] counts = new int[HEIGHT][WIDTH];

        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                map[x][y] = new Cell(false);
            }
        }
        while (minesCount != countOfMines) {
            int x = rnd.nextInt(HEIGHT);
            int y = rnd.nextInt(WIDTH);

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
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                try {
                    map[i][j].increaseNearMines(counts[i][j]);
                } catch (NullPointerException ignored) {
                }
            }
        }
        this.cells = map;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static LinkedList<Player> readPlayers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Players.txt"))) {
            //noinspection unchecked
            return (LinkedList<Player>) in.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
                LinkedList<Player> newPlayers = new LinkedList<>();
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
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writePlayers(Player player) {
        LinkedList<Player> players = readPlayers();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
            assert players != null;

            players.add(player);

            out.writeObject(players);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateList(LinkedList<Player> players) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Players.txt"))) {
            out.writeObject(players);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
