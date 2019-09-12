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
        int i = 0;

        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            if (i == index) {
                return element.getData();
            }
            i++;
        }

        return null;
    }

    public T setData(int index, T newData) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }
        int i = 0;
        T dataOld = null;

        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            if (i == index) {
                dataOld = element.getData();
                element.setData(newData);
            }
            i++;
        }

        return dataOld;
    }

    public T deleteElement(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }
        T data = null;

        if (index == 0) {
            return deleteFirstElement();
        }
        int i = 1;


        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            if (i == index) {
                data = element.getData();

                if (i < count) {
                    element.setNext(element.getNext().getNext());
                }

                count--;
            }
            i++;
        }

        return data;
    }

    public boolean deleteElementByData(T deleteData) {

        boolean deleted = false;

        int i = 0;

        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            while (deleteData == element.getData() && i < count) {
                deleteElement(i);
                element = element.getNext();
                deleted = true;
            }
            i++;

        }

        return deleted;
    }

    public T deleteFirstElement() {
        T data = head.getData();
        head = head.getNext();
        count--;

        return data;
    }

    public void insertFirstElement(ListElement<T> listElement) {
        listElement = new ListElement<>(listElement.getData(), head);
        head = listElement;

        count++;
    }

    public void insertElement(ListElement<T> listElement, int index) {
        if (index == 0) {
            insertFirstElement(listElement);

            return;
        }
        int i = 0;

        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            i++;

            if (i == index) {
                listElement = new ListElement<>(listElement.getData(), element.getNext());
                element.setNext(listElement);
            }
        }

        count++;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();

        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            list.append(element.getData()).append(" ");
        }

        return list.toString();
    }
}