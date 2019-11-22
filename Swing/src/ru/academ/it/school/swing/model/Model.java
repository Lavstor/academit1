package ru.academ.it.school.swing.model;

import ru.academ.it.school.swing.interfaces.Observer;

public class Model implements Observer {
    private String text;
    private int from = 0;
    private int to = 0;

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public void update(String text, String from, String to) {
       this.text = text;

        if(from.equals("1")){
           this.from = 1;
       }

        if(from.equals("2")){
            this.from = 2;
        }

        if(from.equals("3")){
            this.from = 3;
        }

        if(to.equals("1")){
            this.to = 1;
        }

        if(to.equals("2")){
            this.to = 2;
        }

        if(to.equals("3")){
            this.to = 3;
        }

       System.out.println(getAnswer(text));
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

              return "= " + answer;
            }
            if (from == 2) {
                answer = answer - 273.15;

                return "= " + answer;
            }

            return "= " + answer;
        }
        if (to == 1) {
            if (from == 0) {
                answer = answer * 1.8 + 32;

                return "= " + answer;
            }
            if (from == 2) {
                answer = (answer - 273.15) * 1.8 + 32;


                return "= " + answer;
            }

            return "= " + answer;
        }
        if (from == 0) {
            answer = answer + 273.15;

            return "= " + answer;

        }
        if (from == 1) {
            answer = (answer + 459.67) * 5 / 9;


            return "= " + answer;
        }

        return "= " + answer;
    }
}
