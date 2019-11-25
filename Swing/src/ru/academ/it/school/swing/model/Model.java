package ru.academ.it.school.swing.model;

public class Model {
    public double getAnswer(String from, String to, double answer) {
        if (to.equals("Цельсий")) {
            if (from.equals("Фаренгейт")) {
                answer = (answer - 32) * 5 / 9;

                return answer;
            }
            if (from.equals("Кельвины")) {
                answer = answer - 273.15;

                return answer;
            }
            return answer;
        }
        if (to.equals("Фаренгейт")) {
            if (from.equals("Цельсий")) {
                answer = answer * 1.8 + 32;

                return answer;
            }
            if (from.equals("Кельвины")) {
                answer = (answer - 273.15) * 1.8 + 32;

                return answer;
            }
            return answer;
        }
        if (to.equals("Кельвины")) {
            if (from.equals("Цельсий")) {
                answer = answer + 273.15;

                return answer;

            }
            if (from.equals("Фаренгейт")) {
                answer = (answer + 459.67) * 5 / 9;

                return answer;
            }
        }
        return answer;
    }
}
