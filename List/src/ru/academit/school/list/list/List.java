package ru.academit.school.list.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class List {
    private ArrayList<String> list = new ArrayList<>();
    private int listSize;

    public List(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getListSize() {
        listSize = list.size();
        return listSize;
    }

    public String getFirstElement() {
        return list.get(0);
    }

    public char getValue(int index) {
        if(index < 0){
            throw new IllegalArgumentException("Переданный индекс меньше 0");
        }
        int i = 0;
        int elementLength = list.get(i).length();

        while (index >= elementLength) {
            if (i < getListSize() - 1) {
                i++;
            } else{
                throw new IllegalArgumentException("Переданный индекс значения не входит в список");
            }
            index -= elementLength;

            elementLength = list.get(i).length();
        }

        return list.get(i).charAt(index);
    }

    public void setValue(int index, Character character) {
        if(index < 0){
            throw new IllegalArgumentException("Переданный индекс меньше 0");
        }
        int i = 0;
        int elementLength = list.get(i).length();

        while (index >= elementLength) {
            if (i < getListSize() - 1) {
                i++;
            } else{
                throw new IllegalArgumentException("Переданный индекс значения не входит в список");
            }
            index -= elementLength;

            elementLength = list.get(i).length();
        }
        list.set()
    }

    @Override
    public String toString() {
        return Arrays.toString(list.toArray());
    }

}
