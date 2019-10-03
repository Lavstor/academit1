package ru.academitschool.arralist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;

    private  int modCount = 0;

    public ArrayList() {
        size = 0;
        items = (T[]) new Object[10];
    }

    public ArrayList(int size) {
        this.size = size;
        items = (T[]) new Object[size];
    }

    public ArrayList(T[] array) {
        size = array.length;
        items = Arrays.copyOf(array, array.length);
    }

    private int getMod() {
        return modCount;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCount = getMod();

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != getMod()) {
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
    public boolean contains(Object elementToDelete) {
        for (T element : this) {
            if (elementToDelete.equals(element)) {
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
    public boolean remove(Object element) {
        for (int index = 0; index < size; index++) {
            if (element.equals(items[index])) {
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
    public T remove(int elementIndex) {
        if (elementIndex < 0 || elementIndex >= size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        for (int index = 0; index < size; index++) {
            if (elementIndex == index) {
                System.arraycopy(items, index + 1, items, index, size - index - 1);
            }
        }
        size--;
        modCount++;

        return items[elementIndex];
    }

    @Override
    public boolean addAll(Collection collection) {
        while (collection.size() + size >= items.length) {
            increaseCapacity();
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(collection.toArray(), 0, items, size, collection.size());

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection collection) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        while (collection.size() + size >= items.length) {
            increaseCapacity();
        }
        T[] temp = Arrays.copyOfRange(items, index, size);

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(collection.toArray(), 0, items, index, collection.size());
        size += collection.size();

        System.arraycopy(temp, 0, items, size - temp.length, temp.length);

        modCount++;

        return true;
    }

    @Override
    public void clear() {
        size = 0;
        trimToSize();

        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Выход за пределы списка");
        }

        return items[index];
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Выход за пределы списка");
        }

        T oldData = items[index];
        items[index] = data;

        modCount++;

        return oldData;
    }

    @Override
    public void add(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        while (1 + size >= items.length) {
            increaseCapacity();
        }
        if (index == size - 1) {
            T temp = items[index];
            items[index] = data;
            size++;
            items[index + 1] = temp;
            modCount++;

            return;
        }
        T[] temp = Arrays.copyOfRange(items, index, size);

        items[index] = data;
        size++;

        System.arraycopy(temp, 0, items, size - temp.length, temp.length);

        modCount++;
    }

    @Override
    public int indexOf(Object elementToFind) {
        if (!contains(elementToFind)) {
            return -1;
        }
        int index = 0;

        for (T element : this) {
            if (elementToFind.equals(element)) {
                return index;
            }
            index++;
        }

        return 0;
    }

    @Override
    public int lastIndexOf(Object element) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (element.equals(items[i])) {
                index = i;
            }
        }

        return index;
    }

    @Override
    public boolean retainAll(Collection collection) {
        boolean isCleared = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                isCleared = true;
                modCount++;
                i--;
            }
        }

        return isCleared;
    }

    @Override
    public boolean removeAll(Collection collection) {
        boolean isCleared = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                isCleared = true;
                i--;
                modCount++;
            }
        }
        trimToSize();

        return isCleared;
    }

    @Override
    public boolean containsAll(Collection collection) {
        boolean containsAll = true;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                containsAll = false;
            }
        }

        return containsAll;
    }

    @SuppressWarnings("TypeParameterHidesVisibleType")
    @Override
    public <T> T[] toArray(T[] objects) {
        if (size <= 0) {
            throw new IllegalArgumentException("Этот список пуст!");
        }

        if (objects.length < size) {
            //noinspection unchecked

            return (T[]) Arrays.copyOf(items, size);
        }
        //noinspection MismatchedReadAndWriteOfArray
        T[] newArray = Arrays.copyOf(objects, objects.length);

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, newArray, 0, size);

        return newArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, size));
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator listIterator() {
        //noinspection ConstantConditions
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator listIterator(int i) {
        //noinspection ConstantConditions
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List subList(int i, int i1) {
        //noinspection ConstantConditions
        return null;
    }
}
