package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    private BinaryTreeElement root;
    private static int value;

    public boolean add(Comparable data) {
        value++;

        if (root == null) {
            root = new BinaryTreeElement(data);
            return true;
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
                if (data.compareTo(element.data) > 0) {
                    element.rightChild = add(data, element.rightChild);
                    return element;
                } else {
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

        int i = 0;
        int k = 1;
        int count = 0;

        while (!queue.isEmpty()) {
            if (queue.element() != null) {
                queue.add(queue.element().leftChild);
                queue.add(queue.element().rightChild);
            } else if (count < value) {
                queue.add(null);
                queue.add(null);
              
            }

            for (int j = i + k; j <= value; j++) {
                line.append(" ");
            }

            if (queue.element() != null) {
                line.append(queue.remove().getData());
            } else {
                line.append(" ");
                queue.remove();
            }
            count++;
            i++;

            if (i % k == 0) {
                k *= 2;
                line.append("\n");

                i = 0;
            }
        }

        return line.toString();
    }
}
