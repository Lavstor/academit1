package ru.academitschool.hashtable.hashtable;

import ru.academitschool.arralist.ArrayList;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashTable<T> implements Collection {
    private ArrayList<T>[] hashTable;
    private static int modCount = 0;
    private int size = 0;

    public MyHashTable(ArrayList<T>[] arrayLists) {
       hashTable = arrayLists;
    }

    @Override
    public Iterator<T> iterator() {
        return MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCount = MyHashTable.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != ArrayList.modCount) {
                throw new NoSuchElementException("коллекция изменилась");
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public int size() {
        return hashTable.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }
}
