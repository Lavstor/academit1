package ru.academitschool.hashtable.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashTable;
    private int modCount;
    private int size;

    public MyHashTable() {
        //noinspection unchecked
        hashTable = new ArrayList[10];
    }

    public MyHashTable(int tableLength) {
        if (tableLength <= 0) {
            throw new IllegalArgumentException("Размер должен быть больше 0");
        }

        //noinspection unchecked
        hashTable = new ArrayList[tableLength];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = getNextIndex(0);
        private int changeCount = modCount;
        private int iteration;
        private Iterator<T> listIterator = elementIterator();

        @Override
        public boolean hasNext() {
            return iteration < size;
        }

        private Iterator<T> elementIterator() {
            if (size == 0) {
                return null;
            }

            return hashTable[currentIndex].iterator();
        }

        private int getNextIndex(int index) {
            while (index + 1 < hashTable.length && (hashTable[index] == null || hashTable[index].isEmpty())) {
                ++index;
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

            iteration++;

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
        int index = getIndex(element);

        if (hashTable[index] == null) {
            return false;
        }

        return hashTable[index].contains(element);
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % hashTable.length);
    }

    @Override
    public boolean add(T element) {
        int index = getIndex(element);

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }
        hashTable[index].add(element);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object element) {
        int index = getIndex(element);

        if (hashTable[index] == null) {
            return false;
        }

        if (hashTable[index].remove(element)) {
            modCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean addedAll = true;

        for (T element : collection) {
            if (!add(element)) {
                addedAll = false;
            }
        }

        return addedAll;
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
        boolean retained = false;

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                int oldSize = list.size();

                if (list.retainAll(collection)) {
                    retained = true;
                    modCount++;
                    size -= oldSize - list.size();
                }
            }
        }

        return retained;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean removed = false;

        for (Object element : collection) {
            int index = getIndex(element);

            //noinspection SuspiciousMethodCalls
            while (hashTable[index] != null && hashTable[index].remove(element)) {
                removed = true;
                modCount++;
                size--;
            }
        }

        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public T[] toArray() {
        //noinspection unchecked
        T[] array = (T[]) new Object[size];

        int i = 0;

        for (T element : this) {
            array[i] = element;
            i++;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] objects) {
        if (objects.length <= size) {
            return (T[]) Arrays.copyOf(toArray(), size, objects.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, objects, 0, size);
        objects[size] = null;

        return objects;
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
