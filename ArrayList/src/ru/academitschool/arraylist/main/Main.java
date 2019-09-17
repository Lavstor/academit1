package ru.academitschool.arraylist.main;

import ru.academitschool.arralist.Arraylist;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Double[] array1 = {1.0, 2.4, 6.2};
        Arraylist<Double> list1 = new Arraylist<>(array1);

        System.out.println("Наш список на массиве: " + list1);
        System.out.println("Его длина: " + list1.size());
        System.out.println("Пустой?: " + list1.isEmpty());
        System.out.println("Возвращаем массив из метода " + Arrays.toString(list1.toArray()));
        System.out.println("Проверка на содержание эл-та в списке: " + list1.contains(2.0));

    }
}
