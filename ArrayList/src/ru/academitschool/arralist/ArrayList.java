package ru.academitschool.arralist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public ArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер должен быть больше, либо равна 0");
        }
        //noinspection unchecked
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
        items = Arrays.copyOf(items, items.length * 2 + 1);
    }

    private void ensureCapacity(int minCapacity) {
       if (minCapacity > items.length) {
           items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (size != items.length) {
            items = Arrays.copyOf(items, size);
        }
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
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean add(T data) {
        if (items.length + 1 >= size) {
            increaseCapacity();
        }

        items[size] = data;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object element) {
        int index = indexOf(element);

        if (index == -1) {
            return false;
        }

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        size--;
        modCount++;

        return true;
    }

    @Override
    public T remove(int elementIndex) {
        if (elementIndex < 0 || elementIndex >= size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        T data = items[elementIndex];
        System.arraycopy(items, elementIndex + 1, items, elementIndex, size - elementIndex - 1);

        size--;
        modCount++;

        return data;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        if (collection.size() == 0) {
            return false;
        }

        if (collection.size() + size > items.length) {
            ensureCapacity(collection.size() + size);
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + collection.size(), size - index);
        }

        size += collection.size();

        for (T element : collection) {
            items[index] = element;

            index++;
        }
        modCount++;

        return true;
    }

    @Override
    public void clear() {
        size = 0;

        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы списка");
        }

        return items[index];
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы списка");
        }

        T oldData = items[index];
        items[index] = data;

        return oldData;
    }

    @Override
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Выход за границы списка!");
        }

        if (size == index) {
            add(data);
        } else {
            if (size + 1 > items.length) {
                increaseCapacity();
            }
            System.arraycopy(items, index, items, index + 1, size - index);

            items[index] = data;
            size++;
            modCount++;
        }
    }

    @Override
    public int indexOf(Object elementToFind) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementToFind, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int i = size - 1; i > -1; i--) {
            if (Objects.equals(element, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isCleared = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                isCleared = true;

                i--;
            }
        }

        return isCleared;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isCleared = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                isCleared = true;
                i--;
            }
        }

        return isCleared;
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

    @SuppressWarnings("TypeParameterHidesVisibleType")
    @Override
    public <T> T[] toArray(T[] objects) {
        if (objects.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, objects.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, objects, 0, size);

        if (objects.length > size) {
            objects[size] = null;
        }

        return objects;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("[");

        for (Iterator iterator = iterator(); iterator.hasNext(); ) {
            list.append(iterator.next());
            if (iterator.hasNext()) {
                list.append(", ");
            }
        }
        list.append("]");

        return list.toString();
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

