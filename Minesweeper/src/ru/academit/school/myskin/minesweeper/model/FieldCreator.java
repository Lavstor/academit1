package ru.academit.school.myskin.minesweeper.model;

import ru.academit.school.myskin.minesweeper.cell.Cell;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FieldCreator {
    private Cell[][] cells;
    private int countOfMines;
    private int currentScore;

    final private int height;
    final private int width;

    public FieldCreator(int height, int width, int countOfMines, int firstX, int firstY) {
        this.height = height;
        this.width = width;
        this.countOfMines = countOfMines;
        cells = new Cell[height][width];

        generateCellMap(firstY, firstX);
    }

    private void generateCellMap(int height, int width) {
        Random rnd = new Random();

        Cell[][] map = new Cell[this.height][this.width];

        int minesCount = 0;

        int[][] counts = new int[this.height][this.width];

        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                map[x][y] = new Cell(false);
            }
        }

        while (minesCount != countOfMines) {
            int x = rnd.nextInt(this.height);
            int y = rnd.nextInt(this.width);

            if (minesCount != countOfMines && x != height && y != width && !map[x][y].isMine()) {
                minesCount++;

                map[x][y].setAsMine();

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (x + i < counts.length && x + i >= 0 && j + y < counts[0].length && j + y >= 0) {
                            counts[x + i][y + j]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                map[i][j].increaseNearMinesCount(counts[i][j]);
            }
        }

        this.cells = map;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int openCell(int height, int width){
        if (cells[height][width].getMines() == 0) {
            return openAllZero(height, width);
        }
        return 1;
    }
    private int openAllZero(int height, int weight) {
        int currentScore = 0;
        Queue<Integer> queueHeight = new LinkedList<>();
        Queue<Integer> queueWeight = new LinkedList<>();

        queueHeight.add(height);
        queueWeight.add(weight);

        while (!queueHeight.isEmpty()) {
            height = queueHeight.remove();
            weight = queueWeight.remove();

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (height + i >= 0 && weight + j >= 0 && height + i < cells.length && weight + j < cells[0].length) {
                        if (cells[height + i][weight + j].isHidden()) {
                            if (cells[height + i][weight + j].getMines() == 0) {
                                queueHeight.add(height + i);
                                queueWeight.add(weight + j);
                            } else {
                                cells[height + i][ weight + j].setHidden(true);
                            }

                            currentScore++;
                        }

                        cells[height + i][weight + j].setHidden(true);
                    }
                }
            }

            cells[height][weight].setHidden(true);
        }
        return currentScore;
    }
}
