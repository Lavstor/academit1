package ru.academitschool.arralist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;

    private static int modCount = 0;

    public ArrayList(T[] array) {
        size = array.length;
        items = array;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCount = ArrayList.modCount;

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
    public Iterator<T> iterator() {
        return new MyListIterator();
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
    public T[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public boolean contains(Object o) {
        for (Iterator<T> i = iterator(); i.hasNext(); ) {
            if (o.equals(i.next())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(T data) {
        if (items.length <= size) {
            increaseCapacity();
        }
        items[size] = data;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (o.equals(items[index])) {
                if (index < size - 1) {
                    System.arraycopy(items, index + 1, items, index, size - index - 1);
                }
                size--;
                modCount++;

                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        for (int index = 0; index < size; index++) {
            if (i == index) {
                System.arraycopy(items, index + 1, items, index, size - index - 1);
            }
        }
        size--;
        modCount++;

        return items[i];
    }

    @Override
    public boolean addAll(Collection collection) {
        if (collection.size() >= items.length) {
            increaseCapacity();
            addAll(collection);
        }
        System.arraycopy(collection.toArray(), 0, items, size, collection.size());

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        if(i == size - 1){
           return addAll(collection);
        }

        if (collection.size() >= items.length) {
            increaseCapacity();
            addAll(i, collection);
        }

        T[] temp = Arrays.copyOf(items, size);

        System.arraycopy(collection.toArray(), 0, items, i, collection.size());
        size += collection.size();
        System.arraycopy(temp, i, items, size - temp.length - 1, temp.length);


        modCount++;

        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public T set(int i, Object o) {
        return null;
    }

    @Override
    public void add(int i, Object o) {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int i) {
        return null;
    }

    @Override
    public List subList(int i, int i1) {
        return null;
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

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, size));
    }
}
