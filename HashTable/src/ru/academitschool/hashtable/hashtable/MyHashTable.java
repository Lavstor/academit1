package ru.academitschool.hashtable.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTable;
    private int modCount = 0;
    private int size = 0;

    public MyHashTable() {
        hashTable = new ArrayList[10];
    }

    public MyHashTable(int tableLength) {
        if (tableLength <= 0) {
            throw new IllegalArgumentException("Размер должен быть больше 0");
        }

        hashTable = new ArrayList[tableLength];
    }

    @Override
    public Iterator<T> iterator() {
        if (size <= 0) {
            throw new NoSuchElementException("Матирица путсая");
        }

        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = getNextIndex(0);
        private int changeCount = modCount;
        private int max = getNotEmptySize();
        private Iterator<T> listIterator = hashTable[currentIndex].iterator();

        @Override
        public boolean hasNext() {
            return currentIndex < max - 1 || listIterator.hasNext();
        }

        private int getNextIndex(int index) {
            while (index < hashTable.length && (hashTable[index] == null || hashTable[index].isEmpty())) {
                index++;
            }

            return index;
        }

        private int getNotEmptySize() {
            int index = 0;
            int emptyCount = 0;

            for (ArrayList<T> list : hashTable) {
                if (list == null || list.isEmpty()) {
                    emptyCount++;
                } else {
                    index += emptyCount + 1;
                    emptyCount = 0;
                }
            }

            return index;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (changeCount != modCount) {
                throw new NoSuchElementException("Коллекция изменилась");
            }

            if (listIterator.hasNext()) {
                return listIterator.next();
            }

            currentIndex++;
            currentIndex = getNextIndex(currentIndex);
            listIterator = hashTable[currentIndex].iterator();

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
    public boolean contains(Object element) {
        if (element == null) {
            return false;
        }

        int index = getHashCode(element);

        if (hashTable[index] == null) {
            return false;
        }

        return hashTable[index].contains(element);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (T element : this) {
            array[i] = element;
            i++;
        }

        return array;
    }

    private int getHashCode(Object object) {
        return Math.abs(object.hashCode() % hashTable.length);
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }

        int index = getHashCode(element);

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }
        hashTable[getHashCode(element)].add(element);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        }

        int index = getHashCode(element);

        if (hashTable[index] == null) {
            return false;
        }

        if (hashTable[getHashCode(element)].remove(element)) {
            modCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T element : collection) {
            if (!add(element)) {
                return false;
            }
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
    public boolean retainAll(Collection<?> collection) {
        int i = 0;

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                int oldSize = list.size();

                if (list.retainAll(collection)) {
                    i++;
                    modCount++;
                    size -= oldSize - list.size();
                }
            }
        }

        return i > 0;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean removed = false;

        for (Object element : collection) {
            if (element != null) {
                int index = getHashCode(element);

                //noinspection SuspiciousMethodCalls
                while (hashTable[index] != null && hashTable[index].remove(element)) {
                    removed = true;
                    modCount++;
                    size--;
                }
            }
        }

        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() == 0) {
            throw new IllegalArgumentException("Передан пустой список!");
        }

        for (Object element : collection) {
            if (!contains(element) && element != null) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray(Object[] objects) {
        if (objects.length <= hashTable.length) {
            return toArray();
        }

        return Arrays.copyOf(toArray(), objects.length);
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();

        for (ArrayList<T> list : hashTable) {
            table.append(list);
            table.append(" ");
        }

        return table.toString();
    }
}
