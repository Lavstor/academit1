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

        ListElement<T> element = head;

        for (int i = 0; i < count; i++) {
            if (deleteData == element.getData()) {
                deleteElement(i);

                deleted = true;

                i--;
            }
            element = element.getNext();
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
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }
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

    public SinglyLinkedList copy() {
        return new SinglyLinkedList<>(new ListElement<>(head));
    }

    public void reverse() {
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

        for (ListElement<T> element = head; element != null; element = element.getNext()) {
            if (element.getNext() != null) {
                list.append(element.getData()).append(", ");
            } else {
                list.append(element.getData());
            }
        }

        return list.toString();
    }
}