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
            return true;
        }
        root.add(data, root);

        return true;
    }

    public boolean findX(Comparable x) {
        if (root == null) {
            return false;
        }

        return root.findX(x, root) != null;
    }

    public int getValue() {
        return value;
    }

    public boolean delete(Comparable x) {
        return root.delete(x, root) != null;
    }

    private class BinaryTreeElement<T extends Comparable<T>> {
        public T data;
        public BinaryTreeElement<T> leftChild;
        public BinaryTreeElement<T> rightChild;

        private BinaryTreeElement() {

        }
        private BinaryTreeElement(T data) {
            this.data = data;
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

        private BinaryTreeElement<T> findX(T x, BinaryTreeElement<T> element) {
            if (element == null) {
                return null;
            }
            if (element.data == x) {
                return element;
            } else {
                if (x.compareTo(element.data) > 0) {
                    return findX(data, element.rightChild);
                } else {
                    return findX(data, element.leftChild);
                }
            }
        }

        private BinaryTreeElement<T> delete(T x, BinaryTreeElement element) {
            if (element.data != x) {
              element = findX(x, element);
            }
            if (element.rightChild == null) {
                element = element.leftChild;
            } else if (element.leftChild == null) {
                element = element.rightChild;
            } else {
                BinaryTreeElement el = findMinLeft(element);
                element.data = el.leftChild.data;
                el.leftChild = el.leftChild.rightChild;
            }

            return element;
        }

        private BinaryTreeElement<T> findMinLeft(BinaryTreeElement<T> element) {
            if (element.leftChild.leftChild == null) {
                return element;
            } else {
                return findMinLeft(element.leftChild);
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

        int currentLineIndex = 0;
        int lineSize = 1;
        value = 16;

        while (!queue.isEmpty()) {
            if (queue.element() != null) {
                queue.add(queue.element().leftChild);
                queue.add(queue.element().rightChild);
            } else if (currentLineIndex < value) {
                queue.add(null);
                queue.add(null);
            }

            for (int j = currentLineIndex; j <= value / lineSize + currentLineIndex; j++) {
                line.append(" ");
            }

            if (queue.element() != null) {
                line.append(queue.remove().data);
            } else {
                queue.remove();
            }
            currentLineIndex++;

            if (currentLineIndex >= lineSize) {
                lineSize *= 2;
                line.append("\n");

                currentLineIndex = 0;
            }
        }

        return line.toString();
    }
}
