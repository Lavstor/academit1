package ru.academit.school.myskin.minesweeper.cell;

public class DefaultCell implements Cell {
    private boolean isMine;
    private boolean isHidden = false;
    private int nearMinesCount;

    public DefaultCell(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isMine() {
        return isMine;
    }

    public void increaseNearMines(int count) {
        nearMinesCount += count;
    }

    public void setAsMine() {
        this.isMine = true;
    }

    public int getMines() {
        return nearMinesCount;
    }

    public boolean isHidden() {
        return !isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
