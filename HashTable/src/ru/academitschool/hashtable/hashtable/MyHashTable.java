package ru.academitschool.hashtable.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection {
    private ArrayList<T>[] hashTable;
    private static int modCount = 0;
    private int size = 0;

    public MyHashTable() {
        hashTable = new ArrayList[0];
    }

    public MyHashTable(int size) {
        hashTable = new ArrayList[size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            hashTable[i] = new ArrayList<>();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = 0;
        private int modCount = MyHashTable.modCount;
        private Iterator<T> listIterator = hashTable[currentIndex].iterator();

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != MyHashTable.modCount) {
                throw new NoSuchElementException("коллекция изменилась");
            }

            while (!listIterator.hasNext() && hasNext()) {
                listIterator = hashTable[++currentIndex].iterator();
            }

            while (hashTable[currentIndex].isEmpty() && hasNext()){
                currentIndex++;
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
        hashTable[code(o)].add((T) o);

        return true;
    }

    @Override
    public boolean remove(Object o) {
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
        for (int i = 0; i < size; i++) {
            hashTable[i].clear();
        }

        size = 0;
    }

    @Override
    public boolean retainAll(Collection collection) {
        int i = 0;

        for (ArrayList<T> list : hashTable) {
            if (list.retainAll(collection)) {
                i++;
            }
        }

        return i > 0;
    }

    @Override
    public boolean removeAll(Collection collection) {
        int i = 0;

        for (Object element : collection) {
            int index = code(element);

            while (hashTable[index].remove(element)) {
                i++;
            }
        }

        return i > 0;
    }

    @Override
    public boolean containsAll(Collection collection) {
        boolean contains = false;

        for (Object element : collection) {
            contains = contains(element);
        }

        return contains;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
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
