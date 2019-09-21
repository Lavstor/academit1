package ru.academitschool.arraylist.main;

import ru.academitschool.arralist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Double[] array1 = {1.0, 2.4, 6.2};
        ArrayList<Double> list1 = new ArrayList<>(array1);

        System.out.println("Наш список на массиве: " + list1);
        System.out.println("Его длина: " + list1.size());
        System.out.println("Пустой?: " + list1.isEmpty());
        System.out.println("Возвращаем массив из метода " + Arrays.toString(list1.toArray()));
        System.out.println("Проверка на содержание эл-та в списке: " + list1.contains(2.0));
        System.out.print("Добавляем элемент: " + list1.add(2.3));
        System.out.println(" " + list1);
        System.out.println("Проверка на содержание эл-та в списке: " + list1.contains(2.3));

        list1.remove(2);
        System.out.println("Убрать элемент: " + list1);

        ArrayList<Double> list2 = new ArrayList<>(array1);

        list1.addAll(list2);
        System.out.println("Добавили список в конец" + list1);

        list2.add(3.3);
        list1.addAll(2, list2);
        System.out.println("Добавили список по индексу" + list1);
    }
}
