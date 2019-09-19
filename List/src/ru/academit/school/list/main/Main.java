package ru.academit.school.list.main;

import ru.academit.school.list.ListElement.ListElement;
import ru.academit.school.list.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        ListElement<Integer> element0 = new ListElement<>(3);
        ListElement<Integer> element1 = new ListElement<>(null, element0);
        ListElement<Integer> element2 = new ListElement<>(3, element1);
        ListElement<Integer> element3 = new ListElement<>(2, element2);
        ListElement<Integer> element4 = new ListElement<>(1, element3);

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(element4);
        System.out.println("Размер " + list.getSinglyLinkedListLength());
        System.out.println();

        System.out.println("Данные первого элемента " + list.getHeadData());
        System.out.println();

        System.out.println("Данные элемента " + list.getData(1));
        System.out.println();

        int data = list.setData(1, 4);
        System.out.println("Установка новых данных " + list);
        System.out.println(data);
        System.out.println();

        int data2 = list.deleteElement(4);
        System.out.println("Удаление элемента " + list);
        System.out.println(data2);
        System.out.println();

        list.insertFirstElement(4);
        System.out.println("Вставка первго элемента " + list);
        System.out.println();

        list.insertElement(99, 5);
        System.out.println("Вставка элемента по индексу" + list);
        System.out.println();

        System.out.println(list.deleteFirstElement());
        System.out.println("Удаление первого элемента " + list);
        System.out.println();

        System.out.println(list.deleteElementByData(null));
        System.out.println("Удаление элемента с определенными данными " + list);
        System.out.println();

        SinglyLinkedList copy = list.copy();
        System.out.println("Копия " + copy);
        System.out.println();

        list.setData(2,90);
        System.out.println(list);
        System.out.println(copy);
        System.out.println();

        list.reverse();
        System.out.println("Разворот " + list);
        System.out.println();

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        System.out.println("Пустой список " + list2);
        System.out.println();

        list2.setData(0,2);
        list2.insertElement(1,0);

        System.out.println(list2);
    }
}