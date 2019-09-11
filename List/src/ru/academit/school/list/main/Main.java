package ru.academit.school.list.main;

import ru.academit.school.list.list.List;

public class Main {
    public static void main(String[] args){
        List arrayList = new List(args[0]);

        System.out.println("ArrayList: " + arrayList);
        System.out.println("Элемент первый: " + arrayList.getFirstElement());
        System.out.println("Размер списка: " + arrayList.getListSize());
        System.out.println("17 элемент : " + arrayList.getValue(0));


    }
}