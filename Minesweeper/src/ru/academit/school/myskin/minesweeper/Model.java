package ru.academit.school.myskin.minesweeper;

import ru.academit.school.myskin.minesweeper.gui.Cell;

import java.util.Random;

public class Model {
    private Cell[][] cell;
    private int HEIGHT;
    private int WIDTH;
    private int countOfMines;

    public Model(int height, int width, int countOfMines, int firstY, int firstX) {
        HEIGHT = height;
        WIDTH = width;
        this.countOfMines = countOfMines;
        cell = new Cell[height][width];

        generateMap(firstY, firstX);
    }

    public void generateMap(int height, int width) {
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

                map[x][y].setAsMine(true);

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

        this.cell = map;
    }

    public Cell[][] getCell() {
        return cell;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                sb.append(cell[i][j].isMine());
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
