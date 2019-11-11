package ru.academit.school.threads.main;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
      /*  Thread t = new Thread(() -> {
            try {
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(1000);
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Исполнение продолжено");
        */
        List<Integer> numbers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> {
                for (int j = 1; j <= 100; j++) {
                    numbers.add(j);
                }
            });
            t.start();
            threads.add(t);
        }
        threads.get(0).join();
        threads.get(1).join();

        System.out.println(numbers.size());
        System.out.println(numbers);
    }
}
