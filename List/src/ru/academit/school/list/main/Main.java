package ru.academit.school.list.main;

import ru.academit.school.list.ListElement.ListElement;
import ru.academit.school.list.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        ListElement<Integer> element0 = new ListElement<>(4);
        ListElement<Integer> element1 = new ListElement<>(2, element0);
        ListElement<Integer> element2 = new ListElement<>(4, element1);
        ListElement<Integer> element3 = new ListElement<>(4, element2);
        ListElement<Integer> element4 = new ListElement<>(1, element3);

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(element4);
        System.out.println("list.getSinglyLinkedListLength() " + list.getSinglyLinkedListLength());
        System.out.println();

        System.out.println("list.getHeadData() " + list.getHeadData());
        System.out.println();

        System.out.println("list.getData() " + list.getData(1));

        int data = list.setData(1, 4);
        System.out.println("list.setData " + list);
        System.out.println(data);
        System.out.println();

        int data2 = list.deleteElement(4);
        System.out.println("list.deleteElement " + list);
        System.out.println(data2);
        System.out.println();

        list.insertFirstElement(element2);
        System.out.println("list.insertFirstElement " + list);
        System.out.println();

        list.insertElement(element2, 4);
        System.out.println("list.insertElement " + list);
        System.out.println();

        System.out.println(list.deleteFirstElement());
        System.out.println("list.deleteFirstElement() " + list);
        System.out.println();

        System.out.println(list.deleteElementByData(4));
        System.out.println("list.deleteElementByData " + list);
        System.out.println();
    }
}