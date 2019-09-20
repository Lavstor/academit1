package ru.academit.school.list.list;

import ru.academit.school.list.ListElement.ListElement;

public class SinglyLinkedList<T> {
    private ListElement<T> head;
    private int count;

    public SinglyLinkedList() {
        count++;

        head = null;
    }

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

    private ListElement<T> getElement(int index) {
        ListElement<T> element = head;

        for (int i = 0; element != null; element = element.getNext(), i++) {
            if (i == index) {
                return element;
            }
        }

        return head;
    }

    public T getData(int index) {
        if (head == null) {
            throw new NullPointerException("Список пуст");
        }

        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }

        return getElement(index).getData();
    }

    public T setData(int index, T newData) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }

        if (head == null) {
            head = new ListElement<>(newData);
        }

        T dataOld = getElement(index).getData();
        getElement(index).setData(newData);

        return dataOld;
    }

    public T deleteElement(int index) {
        if (head == null) {
            throw new NullPointerException("Список и так пуст");
        }

        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен лежать в диапазоне от 0 и до размера списка");
        }

        if (index == 0) {
            return deleteFirstElement();
        }

        T data = getElement(index).getData();

        try {
            getElement(index - 1).setNext(getElement(index).getNext());
        } catch (NullPointerException e) {
            getElement(index - 1).setNext(null);
        }

        count--;

        return data;
    }

    public boolean deleteElementByData(T deleteData) {
        if (head == null) {
            throw new NullPointerException("Список пуст");
        }

        boolean deleted = false;

        ListElement<T> element = head;

        for (int i = 0; i < count; i++) {
            try {
                if (element.getData().equals(deleteData)) {
                    deleteElement(i);

                    deleted = true;

                    break;
                }
            } catch (NullPointerException e) {
                if (deleteData == null) {
                    deleteElement(i);
                    deleted = true;

                    break;
                }
            }
            element = element.getNext();
        }

        return deleted;
    }

    public T deleteFirstElement() {
        if (head == null) {
            throw new NullPointerException("Список пуст");
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
        getElement(index - 1).setNext(new ListElement<>(data, getElement(index - 1).getNext()));

        count++;
    }

    public SinglyLinkedList copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();


        for (int i = 0; i < count; i++) {
            copy.insertElement(getElement(i).getData(), i);
        }

        return copy;
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