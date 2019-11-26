package ru.academit.school.myskin.minesweeper.gui;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private double score;
    private int password;

    public Player(String name, double score){
        this.name = name;
        this.score = score;
    }

    public Player(String name, int password){
        this.name = name;
        this.password = password;
    }

    public boolean checkPassword(int password){
        return this.password == password;
    }

    public String getName(){
        return name;
    }

    public double getScore(){
        return score;
    }

    public void setScore(double score){
        this.score = score;
    }
}
