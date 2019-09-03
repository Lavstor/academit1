package ru.academitschool.arraylisthome.arraylist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArrayListHome {
    private ArrayList<Integer> names = new ArrayList<>();

    public ArrayListHome(String path){
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
    }

    public ArrayList<Integer> sortArrayList(){
        Set<Integer> set = new HashSet<>(names);

        return new ArrayList<>(set);
    }

    @Override
    public String toString() {
        return Arrays.toString(names.toArray());
    }
}
