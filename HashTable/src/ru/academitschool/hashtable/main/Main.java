package ru.academitschool.hashtable.main;

import ru.academitschool.hashtable.hashtable.MyHashTable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(24);
        list2.add(73);
        list2.add(40);
        list2.add(85);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(12);
        list3.add(31);
        list3.add(14);
        list3.add(51);

        ArrayList<Integer>[] listArray = new ArrayList[]{list1, list2, list3};


        MyHashTable<Integer> hashTable1 = new MyHashTable<>(listArray);
        System.out.println("Новая хэш таблица: " + hashTable1);
        System.out.println("Пустая?: " + hashTable1.isEmpty());

        MyHashTable<Integer> hashTable2 = new MyHashTable<>();
        System.out.println("Новая хэш таблица: " + hashTable2);
        System.out.println("Пустая?: " + hashTable2.isEmpty());
        System.out.println("Содержит 2?: " + hashTable1.contains(5));
        System.out.println(hashTable1.add(200));
        System.out.println("Новая хэш таблица: " + hashTable1);
        hashTable1.remove(200);
        System.out.println("Новая хэш таблица: " + hashTable1);
        hashTable1.add(listArray);
        System.out.println("Новая хэш таблица: " + hashTable1);
    }
}
