package ru.academit.school.list.main;

import ru.academit.school.list.list.List;

public class Main {
    public static void main(String[] args){
        List arrayList = new List(args[0]);

        System.out.println(arrayList);
        arrayList.setElement(0, "2");
        System.out.println(arrayList);
    }
}