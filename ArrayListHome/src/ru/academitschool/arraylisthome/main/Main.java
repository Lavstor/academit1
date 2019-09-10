package ru.academitschool.arraylisthome.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> names = readLines(args[0]);
        System.out.println(names);
        System.out.println();

        ArrayList<Integer> numbers1 = new ArrayList<>();
        numbers1.add(1);
        numbers1.add(2);
        numbers1.add(3);
        numbers1.add(4);
        numbers1.add(5);

        System.out.println(numbers1);
        deleteEvenNumbers(numbers1);
        System.out.println(numbers1);
        System.out.println();

        ArrayList<Integer> numbers2 = new ArrayList<>();
        numbers2.add(2);
        numbers2.add(2);
        numbers2.add(3);
        numbers2.add(2);
        numbers2.add(4);

        System.out.println(numbers2);

        ArrayList<Integer> numbers3 = deleteRepeatNumbers(numbers2);
        System.out.println(numbers3);
    }

    private static ArrayList<String> readLines(String path) {
        ArrayList<String> names = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return names;
    }

    private static void deleteEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0)
                numbers.remove(i);
        }
    }

    private static ArrayList<Integer> deleteRepeatNumbers(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbers1 = new ArrayList<>();

        for (Integer number : numbers) {
            boolean hasTest = numbers1.contains(number);

            if (!hasTest) {
                numbers1.add(number);
            }
        }

        return numbers1;
    }
}
