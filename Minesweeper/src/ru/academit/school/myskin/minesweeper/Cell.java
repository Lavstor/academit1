package ru.academit.school.myskin.minesweeper;

public class Cell {
    private boolean isMine;
    private boolean isHidden = false;
    private int nearMinesCount = 0;

    public Cell(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isMine() {
        return isMine;
    }

    public void increaseNearMines(int count) {
        nearMinesCount += count;
    }

    public void setAsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public int getMines() {
        return nearMinesCount;
    }

    public boolean isHidden() {
        return !isHidden;
    }

    public void setHidden(boolean b) {
        isHidden = b;
    }
}
