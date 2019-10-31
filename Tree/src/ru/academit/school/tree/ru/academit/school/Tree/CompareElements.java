package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Comparator;

public class CompareElements<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        String object1 = o1.toString();
        String object2 = o2.toString();

        return object1.compareTo(object2);
    }
}
