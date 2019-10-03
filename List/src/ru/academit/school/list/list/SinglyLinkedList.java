package ru.academit.school.list.list;

import ru.academit.school.list.ListElement.ListElement;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListElement<T> head;
    private int count = 0;

    public SinglyLinkedList() {
    }

    private SinglyLinkedList(ListElement<T> listElement) {
        if (listElement == null) {
            return;
        }

        head = new ListElement<>(listElement.getData());
        count++;

        for (ListElement<T> nextElement = listElement.getNext(), head = this.head; nextElement != null; nextElement = nextElement.getNext()) {
            head.setNext(new ListElement<>(nextElement.getData()));
            head = head.getNext();

            count++;
        }
    }

    public int getLength() {
        return count;
    }

    public T getHeadData() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private ListElement<T> getElement(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }

        ListElement<T> element = head;

        for (int i = 0; element != null; element = element.getNext(), i++) {
            if (i == index) {
                return element;
            }
        }

        return head;
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }

        return getElement(index).getData();
    }

    public T setData(int index, T newData) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }

        ListElement<T> element = getElement(index);

        T dataOld = element.getData();
        element.setData(newData);

        return dataOld;
    }

    public T deleteElement(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }

        if (index == 0) {
            return deleteFirstElement();
        }

        ListElement<T> element = getElement(index - 1);
        T data = element.getNext().getData();

        element.setNext(element.getNext().getNext());
        count--;

        return data;
    }

    public boolean deleteElementByData(T deleteData) {
        if (Objects.equals(head.getData(), deleteData)) {
            head = head.getNext();
            count--;

            return true;
        }

        ListElement<T> element2 = head.getNext();

        for (ListElement<T> element = head; element2 != null; element = element.getNext(), element2 = element.getNext()) {
            if (Objects.equals(element2.getData(), deleteData)) {
                element.setNext(element2.getNext());
                count--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirstElement() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        T data = head.getData();
        head = head.getNext();
        count--;

        return data;
    }

    public void insertFirstElement(T data) {
        head = new ListElement<>(data, head);
        count++;
    }

    public void insertElement(T data, int index) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и не больше размера списка");
        }

        if (index == 0) {
            insertFirstElement(data);
            return;
        }

        ListElement<T> element = getElement(index - 1);
        element.setNext(new ListElement<>(data, element.getNext()));
        count++;
    }

    public SinglyLinkedList copy() {
        return new SinglyLinkedList<>(head);
    }

    public void reverse() {
        if (count < 2) {
            return;
        }

        for (ListElement<T> nextElement = null, nextHead = head.getNext(); nextHead != null; nextHead = nextHead.getNext()) {
            head.setNext(nextElement);

            nextElement = head;
            head = nextHead;

            if (nextHead.getNext() == null) {
                head.setNext(nextElement);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();

        list.append("{");

        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            if (element.getNext() != null) {
                list.append(element.getData()).append(", ");
            } else {
                list.append(element.getData());
            }
        }
        list.append("}");

        return list.toString();
    }
}