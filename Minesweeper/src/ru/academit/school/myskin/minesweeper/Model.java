package ru.academit.school.myskin.minesweeper;

import ru.academit.school.myskin.minesweeper.gui.Cell;
import java.util.Random;

public class Model {
    private Cell[][] cell;
    private int HEIGHT;
    private int WIDTH;
    private int countOfMines;

    public Model(int height, int width, int countOfMines) {
        HEIGHT = height;
        WIDTH = width;
        this.countOfMines = countOfMines;
        cell = new Cell[height][width];

        generateMap();
    }

    public void generateMap() {
        Random rnd = new Random();

        Cell[][] map = new Cell[HEIGHT][WIDTH];

        int minesCount = 0;
        int[][] counts = new int[HEIGHT][WIDTH];

        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                boolean isMine = false;

                if (minesCount != countOfMines) {
                    isMine = rnd.nextInt(100) < 15;
                }

                if (isMine) {
                    minesCount++;

                    map[x][y] = new Cell(true);

                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            try {
                                counts[x + i][y + j] += 1;
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                } else {
                    map[x][y] = new Cell(false);
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

    public Cell[][] getCell(){
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
