package ru.academ.it.school.producerconsumer.producer;

import ru.academ.it.school.producerconsumer.producerconsumer.ProducerConsumer;

public class Producer implements Runnable {
    private final ProducerConsumer manager;

    public Producer(ProducerConsumer manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        int currentNumber = 1;

        while (!Thread.currentThread().isDaemon()) {
            try {
                Thread.sleep(2000); // имитация долгой работы
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                manager.addItem("Штучка" + currentNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentNumber++;
        }
    }
}
