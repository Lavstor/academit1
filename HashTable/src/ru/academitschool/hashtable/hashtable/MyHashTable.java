package ru.academitschool.hashtable.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection {
    private ArrayList<T>[] hashTable;
    private int modCount = 0;
    private int size = 0;

    public MyHashTable() {
        hashTable = new ArrayList[0];
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер должен быть больше 0");
        }

        hashTable = new ArrayList[size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            hashTable[i] = new ArrayList<>();
        }
    }

    private int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        if (size <= 0) {
            throw new NoSuchElementException("Матирица путсая");
        }

        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = 0;
        private int modCount = getModCount();
        private int notEmptySize = getSize();
        private Iterator<T> listIterator = hashTable[currentIndex].iterator();

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < notEmptySize || listIterator.hasNext();
        }

        private int getSize() {
            int newSize = size;

            for (int i = 0; i < size; i++) {
                if (hashTable[i].isEmpty()) {
                    newSize--;
                }
            }

            return newSize;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != getModCount()) {
                throw new NoSuchElementException("Коллекция изменилась");
            }

            while (hashTable[currentIndex].isEmpty() || !listIterator.hasNext()) {
                listIterator = hashTable[++currentIndex].iterator();
            }

            return listIterator.next();
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            if (!hashTable[i].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(Object o) {
        return hashTable[code(o)].contains(o);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(hashTable, size);
    }

    private int code(Object object) {
        return Math.abs(object.hashCode() % size);
    }

    @Override
    public boolean add(Object o) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Размер таблицы должен быть больше 0");
        }

        hashTable[code(o)].add((T) o);
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (hashTable[code(o)].remove(o)) {
            modCount++;
        }

        return hashTable[code(o)].remove(o);
    }

    @Override
    public boolean addAll(Collection collection) {
        for (Object element : collection) {
            add(element);
            modCount++;
        }

        return true;
    }

    @Override
    public void clear() {
        if (size <= 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            hashTable[i].clear();

            modCount++;
        }
    }

    @Override
    public boolean retainAll(Collection collection) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Размер таблицы должен быть больше 0");
        }

        int i = 0;

        for (ArrayList<T> list : hashTable) {
            if (list.retainAll(collection)) {
                i++;
                modCount++;
            }
        }

        return i > 0;
    }

    @Override
    public boolean removeAll(Collection collection) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Размер таблицы должен быть больше 0");
        }

        int i = 0;

        for (Object element : collection) {
            int index = code(element);

            //noinspection SuspiciousMethodCalls
            while (hashTable[index].remove(element)) {
                i++;
                modCount++;
            }
        }

        return i > 0;
    }

    @Override
    public boolean containsAll(Collection collection) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Размер таблицы должен быть больше 0");
        }

        boolean contains = false;

        for (Object element : collection) {
            contains = contains(element);
        }

        return contains;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        if (objects.length < hashTable.length) {
            return Arrays.copyOf(hashTable, size);
        }

        Object[] newArray = Arrays.copyOf(hashTable, objects.length);
        System.arraycopy(objects, size, newArray, size, objects.length - size);

        return newArray;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();

        for (int i = 0; i < size; i++) {
            list.append(hashTable[i]);
            list.append(" ");
        }

        return list.toString();
    }
}
