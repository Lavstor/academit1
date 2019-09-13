package ru.academitschool.arraylisthome.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = readLines(args[0]);
        System.out.println("Наш список: " + list);
        System.out.println();

        ArrayList<Integer> numbers1 = new ArrayList<>();
        numbers1.add(2);
        numbers1.add(2);
        numbers1.add(3);
        numbers1.add(4);
        numbers1.add(2);

        System.out.println("Список чисел: " + numbers1);
        deleteEvenNumbers(numbers1);

        System.out.println("Удаляем целые: " + numbers1);
        System.out.println();

        ArrayList<Integer> numbers2 = new ArrayList<>();
        numbers2.add(2);
        numbers2.add(2);
        numbers2.add(3);
        numbers2.add(3);
        numbers2.add(4);

        System.out.println("Еще один список чисел: " + numbers2);

        ArrayList<Integer> numbers3 = getRepeatNumbersDeleted(numbers2);
        System.out.println("Удалили повторения: " + numbers3);
    }

    private static ArrayList<String> readLines(String path) {
        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static void deleteEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0){
                numbers.remove(i);

                i--;
            }
        }
    }

    private static ArrayList<Integer> getRepeatNumbersDeleted(ArrayList<Integer> numbers) {
        ArrayList<Integer> newNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (!newNumbers.contains(number)) {
                newNumbers.add(number);
            }
        }

        return newNumbers;
    }
}
