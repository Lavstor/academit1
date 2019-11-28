package ru.academit.school.myskin.minesweeper;

import ru.academit.school.myskin.minesweeper.gui.Cell;
import java.util.Random;

public class Model {
    private Cell[][] cell;
    private int CELLS_COUNT_X;
    private int CELLS_COUNT_Y;
    private int countOfMines;

    public Model(int x, int y, int countOfMines) {
        CELLS_COUNT_X = x;
        CELLS_COUNT_Y = y;
        this.countOfMines = countOfMines;
        cell = new Cell[x][y];

        generateMap();
    }

    public void generateMap() {
        Random rnd = new Random();

        Cell[][] map = new Cell[CELLS_COUNT_X][CELLS_COUNT_Y];

        int minesCount = 0;
        int[][] counts = new int[CELLS_COUNT_X][CELLS_COUNT_Y];

        for (int x = 0; x < CELLS_COUNT_X; x++) {
            for (int y = 0; y < CELLS_COUNT_Y; y++) {
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
        for (int i = 0; i < CELLS_COUNT_X; i++) {
            for (int j = 0; j < CELLS_COUNT_Y; j++) {
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
