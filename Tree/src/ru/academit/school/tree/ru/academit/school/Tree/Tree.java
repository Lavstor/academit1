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
        public BinaryTreeElement<T> leftChild = null;
        public BinaryTreeElement<T> rightChild = null;

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
                    return findX(x, element.rightChild);
                } else {
                    return findX(x, element.leftChild);
                }
            }
        }

        private BinaryTreeElement<T> delete(T x, BinaryTreeElement<T> element) {
            if (element.data == x) {
                BinaryTreeElement<T> el = findMinLeft(element.rightChild);
                element.data = el.leftChild.data;
                el.leftChild = el.leftChild.rightChild;

                return element;
            }
            if (element.rightChild!= null && element.rightChild.data == x) {
                if (element.rightChild.rightChild == null) {
                    element.rightChild = element.rightChild.leftChild;
                    return element.rightChild;
                } else if (element.rightChild.leftChild == null) {
                    element.rightChild = element.rightChild.rightChild;
                    return element.rightChild;
                }
                BinaryTreeElement<T> el = findMinLeft(element.rightChild.rightChild);

                if(element.rightChild.leftChild != null){
                    el.leftChild = element.leftChild.leftChild;
                }
                element.rightChild = el;

                if (el.rightChild != null) {
                    delete(el.data, el);
                }

                return element;
            }

            if (element.leftChild.data == x) {
                if (element.leftChild.rightChild == null) {
                    element.leftChild = element.leftChild.leftChild;

                    return element.leftChild;
                }
                if (element.leftChild.leftChild == null) {
                    element.leftChild = element.leftChild.rightChild;

                    return element.leftChild;
                }
                BinaryTreeElement<T> el = findMinLeft(element.leftChild.rightChild);

                if(element.leftChild.leftChild != null){
                    el.leftChild = element.leftChild.leftChild;
                }
                element.leftChild = el;

                if (el.rightChild!= null) {
                    delete(el.data, el);
                }

                return element;
            }

            if (x.compareTo(element.data) > 0) {
                return delete(x, element.rightChild);
            } else {
                return delete(x, element.leftChild);
            }
        }

        private BinaryTreeElement findMinLeft(BinaryTreeElement<T> element) {
            if (element.leftChild == null || element.leftChild.leftChild == null) {
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
