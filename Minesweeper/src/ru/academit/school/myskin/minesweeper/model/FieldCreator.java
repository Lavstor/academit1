package ru.academit.school.myskin.minesweeper.model;

import ru.academit.school.myskin.minesweeper.cell.Cell;

import java.util.LinkedList;
import java.util.Random;

public class FieldCreator {
    private Cell[][] battlefieldMap;
    private int countOfMines;
    private int currentScore;

    final private int height;
    final private int width;

    public FieldCreator(int height, int width, int countOfMines, int firstX, int firstY) {
        this.height = height;
        this.width = width;
        this.countOfMines = countOfMines;
        battlefieldMap = new Cell[height][width];

        generateCellMap(firstY, firstX);
    }

    private void generateCellMap(int height, int width) {
        Random rnd = new Random();

        battlefieldMap = new Cell[this.height][this.width];

        int minesCount = 0;

        int[][] counts = new int[this.height][this.width];

        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                battlefieldMap[x][y] = new Cell(false);
            }
        }

        while (minesCount != countOfMines) {
            int x = rnd.nextInt(this.height);
            int y = rnd.nextInt(this.width);

            if (minesCount != countOfMines && x != height && y != width && !battlefieldMap[x][y].isMine()) {
                minesCount++;

                battlefieldMap[x][y].setAsMine();

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
                battlefieldMap[i][j].increaseNearMinesCount(counts[i][j]);
            }
        }
    }

    public LinkedList<Integer[]> openCell(int height, int width) {
        if (battlefieldMap[height][width].isHidden()) {
            if (battlefieldMap[height][width].getMines() == 0) {
                return openAllZero(height, width);
            }

            if (battlefieldMap[height][width].isMine()) {
                return showBombs();
            }

            battlefieldMap[height][width].setHidden(true);

            currentScore++;

            LinkedList<Integer[]> cellList = new LinkedList<>();
            cellList.add(new Integer[]{height, width});

            return cellList;
        }

        return null;
    }

    public LinkedList<Integer[]> massPush(int height, int width) {
        LinkedList<Integer[]> cellList = new LinkedList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (width + j < battlefieldMap[0].length && i + height < battlefieldMap.length && j + width >= 0 && i + height >= 0
                        && battlefieldMap[height + i][width + j].isHidden() && battlefieldMap[height + i][width + j].isMarked()) {
                    cellList.addAll(openCell(height + i, width + j));
                }
            }
        }

        return cellList;
    }

    public void markCell(int x, int y) {
        if (battlefieldMap[x][y].isMarked()) {
            battlefieldMap[x][y].setMarked(true);
        } else {
            battlefieldMap[x][y].setMarked(false);
        }
    }

    private LinkedList<Integer[]> showBombs() {
        LinkedList<Integer[]> cellList = new LinkedList<>();

        for (int i = 0; i < battlefieldMap.length; i++) {
            for (int j = 0; j < battlefieldMap[i].length; j++) {
                if (battlefieldMap[i][j].isMine()) {
                    battlefieldMap[i][j].setHidden(true);

                    cellList.add(new Integer[]{i, j});
                }
            }
        }

        return cellList;
    }

    private LinkedList<Integer[]> openAllZero(int height, int weight) {
        int currentScore = 0;

        LinkedList<Integer[]> cellQueue = new LinkedList<>();

        cellQueue.add(new Integer[]{height, weight});

        int currentListIndex = 0;

        while (currentListIndex < cellQueue.size()) {
            height = cellQueue.get(currentListIndex)[0];
            weight = cellQueue.get(currentListIndex)[1];

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (height + i >= 0 && weight + j >= 0 && height + i < battlefieldMap.length && weight + j < battlefieldMap[0].length) {
                        if (battlefieldMap[height + i][weight + j].isHidden()) {
                            if (battlefieldMap[height + i][weight + j].getMines() == 0) {
                                cellQueue.add(new Integer[]{height + i, weight + j});
                            } else {
                                cellQueue.addFirst(new Integer[]{height + i, weight + j});
                                currentListIndex++;
                            }
                            currentScore++;
                        }

                        battlefieldMap[height + i][weight + j].setHidden(true);
                    }
                }
            }

            currentListIndex++;
            battlefieldMap[height][weight].setHidden(true);
        }

        this.currentScore += currentScore;
        return cellQueue;
    }

    public boolean cellIsMineCheck(int x, int y) {
        return battlefieldMap[x][y].isMine();
    }

    public boolean cellIsHiddenCheck(int x, int y) {
        return battlefieldMap[x][y].isHidden();
    }

    public boolean cellIsMarkedCheck(int x, int y) {
        return battlefieldMap[x][y].isMarked();
    }

    public int getNearMines(int x, int y) {
        return battlefieldMap[x][y].getMines();
    }

    public int getScore() {
        return currentScore;
    }
}
