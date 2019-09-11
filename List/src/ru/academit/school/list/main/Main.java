package ru.academit.school.list.main;

import ru.academit.school.list.ListElement.ListElement;
import ru.academit.school.list.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        ListElement<Integer> element0 = new ListElement<>();
        ListElement<Integer> element1 = new ListElement<>(4, element0);
        ListElement<Integer> element2 = new ListElement<>(3, element1);
        ListElement<Integer> element3 = new ListElement<>(2, element2);
        ListElement<Integer> element4 = new ListElement<>(1, element3);

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(element4);
        System.out.println(list.getSinglyLinkedListLength());
        System.out.println();

        System.out.println(list.getHeadData());
        System.out.println();

        System.out.println(list.getData(0));
}