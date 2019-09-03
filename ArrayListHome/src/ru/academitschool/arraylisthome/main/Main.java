package ru.academitschool.arraylisthome.main;

import ru.academitschool.arraylisthome.arraylist.ArrayListHome;

public class Main {
    public static void main(String[] args){
        ArrayListHome arrayList = new ArrayListHome(args[0]);
        
        System.out.println(arrayList);
        System.out.println(arrayList.sortArrayList());
    }
}
