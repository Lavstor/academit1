package ru.academ.it.school.producerconsumer.producerconsumer;

import ru.academ.it.school.producerconsumer.consumer.Consumer;
import ru.academ.it.school.producerconsumer.producer.Producer;

import java.util.ArrayList;

public class ProducerConsumer {
    private final ArrayList<String> list = new ArrayList<>();
    private final static int CAPACITY = 5;
    private final static int PRODUCERS_COUNT = 2;
    private final static int CONSUMERS_COUNT = 2;

    public void start() {
        for (int i = 0; i < CONSUMERS_COUNT; ++i) {
            Thread consumer = new Thread(new Consumer(this));
            consumer.start();
        }
        for (int i = 0; i < PRODUCERS_COUNT; ++i) {
            Thread producer = new Thread(new Producer(this));
            producer.start();
        }
    }

    public String getItem() throws InterruptedException {
        synchronized(list) {
            while (list.size() <= 0) {
                list.wait();
            }
            String result = list.remove(0);

            list.notifyAll();

            return result;
        }
    }

    public void addItem(String item) throws InterruptedException {
        synchronized(list) {
            while (list.size() >= CAPACITY) {
                list.wait(900000);
            }
            list.add(item);

            list.notifyAll();
        }
    }
}
