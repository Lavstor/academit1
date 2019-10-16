package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    private BinaryTreeElement root;
    private static int value;

    public boolean add(Comparable data) {
        value++;
        if (root == null) {
            root = new BinaryTreeElement(data);
        }
        root.add(data, root);

        return true;
    }

    private class BinaryTreeElement<T extends Comparable<T>> {
        public T data;
        public BinaryTreeElement<T> leftChild;
        public BinaryTreeElement<T> rightChild;

        private BinaryTreeElement(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        private BinaryTreeElement<T> add(T data, BinaryTreeElement<T> element) {
            if (element == null) {
                element = new BinaryTreeElement<>(data);

                return element;
            } else {
                if (data.compareTo(this.data) > 0) {
                    //добавить справа
                    element.rightChild = add(data, element.rightChild);
                    return element;
                } else {
                    //добавить слева
                    element.leftChild = add(data, element.leftChild);
                    return element;
                }
            }
        }

    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        Queue<BinaryTreeElement> queue = new LinkedList<>();

        BinaryTreeElement element = root;
        queue.add(element);

        while (!queue.isEmpty()) {
            line.append(queue.remove(element));

            queue.add(element.leftChild);
            queue.add(element.rightChild);

        }

        return line.toString();
    }
}
