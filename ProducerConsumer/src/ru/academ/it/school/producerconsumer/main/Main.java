package ru.academ.it.school.producerconsumer.main;

import ru.academ.it.school.producerconsumer.producerconsumer.ProducerConsumer;

public class Main {
    public static void main(String[] args){
        ProducerConsumer p1 = new ProducerConsumer();

        p1.start();
    }
}
