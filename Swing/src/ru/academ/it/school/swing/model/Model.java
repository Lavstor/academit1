package ru.academ.it.school.swing.model;

public class Model {
    private int from = 0;
    private int to = 0;

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getAnswer(String text) {
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e) {

            return "Ошибка!";
        }

        double answer = Double.parseDouble(text);

        if (to == 0) {
            if (from == 1) {
                answer = (answer - 32) * 5 / 9;

                return String.valueOf("= " + answer);
            }
            if (from == 2) {
                answer = answer - 273.15;

                return String.valueOf("= " + answer);
            }

            return String.valueOf("= " + answer);
        }
        if (to == 1) {
            if (from == 0) {
                answer = answer * 1.8 + 32;

                return String.valueOf("= " + answer);
            }
            if (from == 2) {
                answer = (answer - 273.15) * 1.8 + 32;

                return String.valueOf("= " + answer);
            }

            return String.valueOf("= " + answer);
        }
        if (from == 0) {
            answer = answer + 273.15;
            return String.valueOf("= " + answer);

        }
        if (from == 1) {
            answer = (answer + 459.67) * 5 / 9;

            return String.valueOf("= " + answer);
        }

        return String.valueOf("= " + answer);
    }
}
