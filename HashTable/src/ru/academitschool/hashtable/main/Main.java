package ru.academitschool.hashtable.main;

import ru.academitschool.hashtable.hashtable.MyHashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

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

        MyHashTable<Integer> hashTable1 = new MyHashTable<>(10);
        hashTable1.addAll(list1);
        hashTable1.addAll(list2);
        hashTable1.addAll(list3);
        System.out.println("Наша: " + hashTable1);
        System.out.println();

        System.out.println("Пустая?: " + hashTable1.isEmpty());
        System.out.println();

        //noinspection MismatchedQueryAndUpdateOfCollection
        MyHashTable<Integer> hashTable2 = new MyHashTable<>();
        System.out.println("Пустая: " + hashTable2);
        System.out.println();

        System.out.println("Пустая?: " + hashTable2.isEmpty());
        System.out.println();

        System.out.println("Содержит 2?: " + hashTable1.contains(5));
        System.out.println();

        System.out.println(hashTable1.add(200));
        System.out.println("+ 200");
        System.out.println("Новая хэш таблица: " + hashTable1);
        System.out.println();

        hashTable1.remove(200);
        System.out.println("- 200");
        System.out.println("Новая хэш таблица: " + hashTable1);
        System.out.println();

        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(121);
        list4.add(73);
        list4.add(2);
        list4.add(511);

        hashTable1.addAll(list4);
        System.out.println("Добавили список");
        System.out.println("Новая хэш таблица: " + hashTable1);
        System.out.println();

        System.out.println("Лист: " + list1);
        System.out.println("Таблица: " + hashTable1);
        System.out.println("Убрали все несовпадения? " + hashTable1.retainAll(list1));
        System.out.println("Убираем все несовпадения: " + hashTable1);
        System.out.println();

        System.out.println("Лист: " + list4);
        System.out.println("Таблица: " + hashTable1);
        System.out.println("Убираем все совпадения: " + hashTable1.removeAll(list4));
        System.out.println("Убираем все совпадения: " + hashTable1);
        System.out.println();

        ArrayList<Integer> list5 = new ArrayList<>();
        // list5.add(2);
        list5.add(3);
        list5.add(4);
        list5.add(5);
        //list5.add(30);

        System.out.println("Лист: " + list5);
        System.out.println("Таблица: " + hashTable1);
        System.out.println("Содержит все: " + hashTable1.containsAll(list5));
        System.out.println();

        hashTable1.addAll(list2);
        hashTable1.addAll(list3);
        hashTable1.addAll(list2);

        hashTable1.add(334234235);

        System.out.println("Таблица: " + hashTable1);
        System.out.println("Итератор: ");

        for (Iterator iterator = hashTable1.iterator(); iterator.hasNext(); ) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        MyHashTable hashTable3 = new MyHashTable<>(2);
        System.out.println(hashTable3);
        System.out.println("Пустая? " + hashTable3.isEmpty());
        System.out.println();

        hashTable3.add(2);
        hashTable3.add(200012);
        hashTable3.add(9);
        System.out.println(hashTable3);
        hashTable3.clear();
        System.out.println(hashTable3);
        System.out.println();

        Objects[] objects = new Objects[15];
        System.out.println(hashTable1);
        //noinspection SuspiciousToArrayCall
        System.out.println(Arrays.toString(hashTable1.toArray(objects)));
    }
}
