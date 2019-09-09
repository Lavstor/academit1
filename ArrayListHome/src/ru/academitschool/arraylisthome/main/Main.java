package ru.academitschool.arraylisthome.main;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ArrayList<Integer> names = readFile(args[0]);
    }
    private static ArrayList<Integer> readFile(String path){
        ArrayList<Integer> names = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {

            while (scanner.hasNextInt()) {
                Integer number = scanner.nextInt();

                if(number % 2 != 0){
                    names.add(number);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return names;
    }
}
