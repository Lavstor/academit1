package ru.academit.school.list.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class List {
    private ArrayList<String> list = new ArrayList<>();

    public List(String path){
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                    list.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getListSize() {

        return list.size();
    }

    public String getFirstElement() {
        return list.get(0);
    }

    public char getChar(int index) {
        int i = 0;
        int length = list.get(0).length();

        while (index >= length){
            length += list.get(i).length();

            if(index >= length){
               index -= length;
           }
            i++;
        }
       return list.get(i).charAt(index);
    }

    public void setElement(int index, String string) {
       list.set(index, string);
    }

    @Override
    public String toString() {
        return Arrays.toString(list.toArray());
    }

}
