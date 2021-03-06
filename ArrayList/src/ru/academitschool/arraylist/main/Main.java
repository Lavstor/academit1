package ru.academitschool.arraylist.main;

import ru.academitschool.arralist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Double[] array1 = {66.3, 33.53, 6.7, 52236.77};
        ArrayList<Double> list1 = new ArrayList<>(array1);

        System.out.println("Наш список на массиве: " + list1);
        System.out.println();

        System.out.println("Его длина: " + list1.size());
        System.out.println();

        System.out.println("Пустой?: " + list1.isEmpty());
        System.out.println();

        System.out.println("Возвращаем массив из метода " + Arrays.toString(list1.toArray()));
        System.out.println();

        System.out.println("Проверка на содержание эл-та в списке: " + list1.contains(2.0));
        System.out.println();

        System.out.print("Добавляем элемент 2.3: " + list1.add(2.3));
        System.out.println(" Новый список: " + list1);
        System.out.println("Проверка на содержание эл-та в списке: " + list1.contains(2.3));
        System.out.println();

        System.out.println("Убираем 0");
        System.out.println(list1.remove(0));
        System.out.println("Убрали элемент: " + list1);
        System.out.println();

        System.out.println("Убираем 2.3");
        System.out.println(list1.remove(2.3));
        System.out.println("Убрали элемент: " + list1);
        System.out.println();

        ArrayList<Double> list2 = new ArrayList<>(array1);
        list2.add(3.3);
        list2.add(3.9);

        System.out.println("Добавляем этот список в конец: " + list2);
        System.out.println("Добавляем сюда: " + list1);

        list1.addAll(list2);
        System.out.println("Добавили список в конец" + list1);
        System.out.println();

        list2.add(3.2);
        // list2.add(3.9);

        System.out.println("Вставляем: " + list2);
        System.out.println("Вставляем сюда: " + list1);
        list1.addAll(1, list2);
        System.out.println("Вставили по индексу 1 " + list1);
        System.out.println();

        System.out.println("Элемент по индексу 4: " + list1.get(4));
        System.out.println();

        System.out.println("Заменили элемент по индексу 4: " + list1.set(4, 0.0));
        System.out.println("Новый список: " + list1);
        System.out.println();

        list1.clear();
        System.out.println("Очистили список: " + list1);
        System.out.println();

        list1.add(0, 9.2);
        System.out.println("Добавили элемент по индексу 0 в список: " + list1);
        System.out.println();

        list1.add(99.9);
        System.out.println("Первый индекс элемента 99.9: " + list1.indexOf(99.9));
        System.out.println();

        System.out.println("Последний индекс элемента 3.9: " + list1.lastIndexOf(3.9));
        System.out.println();

        list1.add(66.3);
        System.out.println("Коллекция из которой удаляем " + list1);
        System.out.println("Коллекция по которой сверяемся " + list2);
        list1.retainAll(list2);
        System.out.println("Очищенная коллекция " + list1);
        System.out.println("--------------------");

        ArrayList list3 = new ArrayList<>(array1);

        list1.add(67.3);
        System.out.println("Коллекция по которой сверяемся " + list3);
        System.out.println("Коллекция из которой удаляем " + list1);

        list1.removeAll(list2);
        System.out.println("Очищенная коллекция " + list1);
        System.out.println();

        System.out.println("Коллекция по которой сверяемся " + list3);
        System.out.println("Проверям: " + list1);
        System.out.println("Совпадение? : " + list1.containsAll(list3));
        System.out.println();

        Double[] array = {23.4, 21.5, 25.7, 89.3, 34.32, 3.4, 21.5, 25.7, 89.3, 34.3};

        System.out.println("Массив до: " + Arrays.toString(array));
        System.out.println("Скопировали список в массив " + Arrays.toString(list1.toArray(array)));
        System.out.println();

        System.out.println(list2);
        list2.add(3, 3.3);
        System.out.println(list2);
    }
}
