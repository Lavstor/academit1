package ru.academitschool.hashtable.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection {
    private ArrayList<T>[] hashTable;
    private int modCount = 0;
    private int size = 0;

    public MyHashTable() {
        hashTable = new ArrayList[10];


    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int tableLength) {
        if (tableLength <= 0) {
            throw new IllegalArgumentException("Размер должен быть больше 0");
        }

        hashTable = new ArrayList[tableLength];

        for (int i = 0; i < tableLength; i++) {
            hashTable[i] = new ArrayList<>();
        }
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
        private int changes = modCount;
        private int notEmptySize = getSize();
        private Iterator<T> listIterator = hashTable[getFirstIndex(0)].iterator();

        @Override
        public boolean hasNext() {
            return currentIndex  < hashTable.length - hashTable.length % 2 - 1 || listIterator.hasNext();
        }

        private int getSize() {
            int newSize = hashTable.length - 1;

            for (int i = 0; i < hashTable.length; i++) {
                if (hashTable[i] == null || hashTable[i].isEmpty()) {
                    newSize--;
                }
            }

            return newSize;
        }

        private int getFirstIndex(int index) {
            while (hashTable[index] == null || hashTable[index].isEmpty()) {
                index++;
            }

            return index;
        }


        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (changes != modCount) {
                throw new NoSuchElementException("Коллекция изменилась");
            }

            while (hashTable[currentIndex] == null ||  !listIterator.hasNext()) {
                currentIndex++;
                currentIndex = getFirstIndex(currentIndex);

                listIterator = hashTable[currentIndex].iterator();
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
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = code(o);

        if (hashTable[index] == null) {
            return false;
        }

        return hashTable[index].contains(o);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (Iterator iterator = iterator(); iterator.hasNext(); ) {
            array[i] = iterator.next();
            i++;
        }
        return array;
    }

    private int code(Object object) {
        return Math.abs(object.hashCode() % hashTable.length);
    }

    @Override
    public boolean add(Object o) {
        int index = code(o);

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }
        hashTable[code(o)].add((T) o);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = code(o);

        if (hashTable[index] == null) {
            return false;
        }
        modCount++;
        size--;


        return hashTable[code(o)].remove(o);
    }

    @Override
    public boolean addAll(Collection collection) {
        for (Object element : collection) {
            add(element);
        }

        return true;
    }

    @Override
    public void clear() {
        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                list.clear();
                modCount++;
            }
        }

        size = 0;
    }

    @Override
    public boolean retainAll(Collection collection) {
        int i = 0;

        for (ArrayList<T> list : hashTable) {
            if (list != null && list.retainAll(collection)) {
                i++;
                modCount++;
            }
        }

        return i > 0;
    }

    @Override
    public boolean removeAll(Collection collection) {
        boolean removed = false;

        for (Object element : collection) {
            int index = code(element);

            //noinspection SuspiciousMethodCalls
            while (hashTable[index] != null && hashTable[index].remove(element)) {
                removed = true;
                modCount++;
            }
        }

        return removed;
    }

    @Override
    public boolean containsAll(Collection collection) {
        if (collection.size() == 0) {
            throw new IllegalArgumentException("Передан пустой список!");
        }

        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
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

        for (ArrayList<T> aHashTable : hashTable) {
            list.append(aHashTable);
            list.append(" ");
        }

        return list.toString();
    }
}
