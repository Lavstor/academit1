package ru.academitschool.arraylisthome.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list1 = readLines(args[0]);
        System.out.println("Прочитали " + list1);

        ArrayList<Integer> list2 = new ArrayList<>();

        list2.add(2);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(2);

        System.out.println(list2);
        deleteEvenNumbers(list2);
        System.out.println(list2);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(2);
        list3.add(3);
        list3.add(3);
        list3.add(4);

        System.out.println(list3);

        ArrayList<Integer> numbers3 = getListWithoutRepetitions(list3);
        System.out.println(numbers3);
    }

    private static ArrayList<String> readLines(String path) {
        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }

        return list;
    }

    private static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);

                i--;
            }
        }
    }

    private static ArrayList<Integer> getListWithoutRepetitions(ArrayList<Integer> list) {
        ArrayList<Integer> newNumbers = new ArrayList<>();

        for (Integer element : list) {
            if (!newNumbers.contains(element)) {
                newNumbers.add(element);
            }
        }

        return newNumbers;
    }
}
