package ru.academit.school.list.ListElement;

public class ListElement<T> {
    private T data; // полезные данные
    private ListElement<T> next; //ссылка на сл

    public ListElement() {

    }

    public ListElement(T data) {
        this.data = data;
    }

    public ListElement(T data, ListElement<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListElement<T> getNext() {
        return next;
    }

    public void setNext(ListElement<T> next) {
        this.next = next;
    }

    public ListElement<T> getElement(int index) {
        ListElement<T> data = getNext();

        for(int i = 0; i < index; i++){
           data =  getNext();
        }
        return data;
    }
}
