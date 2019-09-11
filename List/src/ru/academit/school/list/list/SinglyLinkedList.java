package ru.academit.school.list.list;

import ru.academit.school.list.ListElement.ListElement;

public class SinglyLinkedList<T> {
    private ListElement<T> head;
    private int count;

    public SinglyLinkedList(ListElement<T> listElement) {
        head = listElement;

        for (listElement = head; listElement != null; listElement = listElement.getNext()) {
            count++;
        }
    }

    public int getSinglyLinkedListLength() {
        return count;
    }

    public T getHeadData() {
        return head.getData();
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }
       return head.getElement(index);
    }
}
