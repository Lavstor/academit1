package ru.academit.school.myskin.minesweeper.gui;

import java.io.Serializable;
import java.util.Arrays;

public class Player implements Serializable {
    private String name;
    private double score;
    private char[] pass;
    static final long serialVersionUID = 1L;

    Player(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Player(String name, char[] password) {
        this.name = name;
        this.pass = password;
    }

    public boolean checkPassword(char[] password) {
        return Arrays.equals(this.pass, password);
    }

    String getName() {
        return name;
    }

    double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
