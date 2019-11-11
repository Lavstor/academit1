package ru.academ.it.school.producerconsumer.consumer;

import ru.academ.it.school.producerconsumer.producerconsumer.ProducerConsumer;

public class Consumer implements Runnable {
    private final ProducerConsumer manager;

    public Consumer(ProducerConsumer manager) {
        this.manager = manager;
    }

    public void run() {
        while (true) {
            String item = null;
            try {
                item = manager.getItem();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(item);
        }
    }
}
