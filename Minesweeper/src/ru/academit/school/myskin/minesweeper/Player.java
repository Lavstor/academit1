package ru.academit.school.myskin.minesweeper;

import java.io.Serializable;
import java.util.Arrays;

public class Player implements Serializable {
    private String name;
    private double score;
    private char[] pass;
    static final long serialVersionUID = 1L;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public  Player(String name, char[] password) {
        this.name = name;
        this.pass = password;
    }

    public boolean checkPassword(char[] password) {
        return Arrays.equals(this.pass, password);
    }

    public String getName() {
        return name;
    }

   public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (this.score < score) {
            this.score = score;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
