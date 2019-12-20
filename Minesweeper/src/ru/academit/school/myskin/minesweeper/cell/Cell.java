package ru.academit.school.myskin.minesweeper.cell;

public interface Cell {
    boolean isMine();

    void increaseNearMines(int count);

    void setAsMine();

    int getMines();

    boolean isHidden();

    void setHidden(boolean hide);

    void setMarked(boolean mark);

    boolean isMarked();
}
