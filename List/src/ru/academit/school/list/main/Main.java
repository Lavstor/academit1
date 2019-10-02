package ru.academit.school.list.main;

import ru.academit.school.list.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirstElement(6);
        list.insertFirstElement(5);
        list.insertFirstElement(4);
        list.insertFirstElement(3);
        list.insertFirstElement(2);
        list.insertFirstElement(1);
        System.out.println("Список " + list);

        System.out.println("Размер " + list.getLength());
        System.out.println();

        System.out.println("Данные первого элемента " + list.getHeadData());
        System.out.println();

        System.out.println("Данные элемента по индексу 1 " + list.getData(1));
        System.out.println();

        int data = list.setData(0, 4);
        System.out.println("Установка новых данных по индексу 0 " + list);
        System.out.println(data);
        System.out.println();

        int data2 = list.deleteElement(5);
        System.out.println("Удаление элемента по индексу 5 " + list);
        System.out.println(data2);
        System.out.println();

        list.insertFirstElement(4);
        System.out.println("Вставка первго элемента " + list);
        System.out.println();

        list.insertElement(99, 2);
        System.out.println("Вставка элемента по индексу 2" + list);
        System.out.println();

        System.out.println(list.deleteFirstElement());
        System.out.println("Удаление первого элемента " + list);
        System.out.println();

        System.out.println(list.deleteElementByData(99));
        System.out.println("Удаление элемента с определенными данными (99) " + list);
        System.out.println();

        SinglyLinkedList copy = list.copy();
        System.out.println("Копия " + copy);
        System.out.println();

        list.reverse();
        System.out.println("Разворот " + list);
        System.out.println();

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        System.out.println("Пустой список " + list2);
        System.out.println();

        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>();
        list3.insertFirstElement(6);
        list3.insertFirstElement(5);
        list3.insertFirstElement(4);
        list3.insertFirstElement(3);
        list3.insertFirstElement(2);
        list3.insertFirstElement(1);

        System.out.println(list3);
        System.out.println(list3.getLength());
        list3.deleteElementByData(2);
        System.out.println(list3);
        System.out.println(list3.getLength());
        System.out.println();
    }
}