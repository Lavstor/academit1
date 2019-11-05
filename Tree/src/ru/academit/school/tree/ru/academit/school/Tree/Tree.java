package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class Tree<T> {
    private BinaryTreeElement root;
    private int value;
    private Comparator<T> comparator;

    public Tree(T data) {
        root = new BinaryTreeElement();
        root.data = data;

        comparator = (o1, o2) -> {
            Comparable object1 = (Comparable) o1;
            Comparable object2 = (Comparable) o2;

            return object1.compareTo(object2);
        };
    }

    public Tree(Comparator<T> comparator, T data) {
        this.comparator = comparator;

        root = new BinaryTreeElement();
        root.data = data;
    }

    public void add(T data) {
        value++;

        add(data, root);
    }

    private BinaryTreeElement add(T data, BinaryTreeElement element) {
        if (element == null) {
            element = new BinaryTreeElement();
            element.data = data;

            return element;
        }

        if (comparator.compare(element.data, data) < 0) {
            element.rightChild = add(data, element.rightChild);

            return element;
        }

        element.leftChild = add(data, element.leftChild);

        return element;
    }

    public void deepRecursionBypass(Consumer<T> consumer) {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        deepRecursionBypass(root, consumer);
    }

    private void deepRecursionBypass(BinaryTreeElement root, Consumer<T> consumer) {
        Deque<BinaryTreeElement> stack = new LinkedList<>();

        for (BinaryTreeElement left = root; left != null; left = left.leftChild) {
            consumer.accept(left.data);

            if (left.rightChild != null) {
                stack.addLast(left.rightChild);
            }
        }

        while (!stack.isEmpty()) {
            deepRecursionBypass(stack.removeLast(), consumer);
        }
    }

    public void deepBypass(Consumer<T> consumer) {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        deepBypass(root, consumer);
    }

    private void deepBypass(BinaryTreeElement root, Consumer<T> consumer) {
        LinkedList<BinaryTreeElement> stack = new LinkedList<>();

        stack.addLast(root);

        while (!stack.isEmpty()) {
            consumer.accept(stack.getLast().data);

            int size = stack.size() - 1;

            if (stack.get(size).rightChild != null) {
                stack.addLast(stack.get(size).rightChild);
            }

            if (stack.get(size).leftChild != null) {
                stack.addLast(stack.get(size).leftChild);
            }

            stack.remove(size);
        }
    }

    public void widthBypass(Consumer<T> consumer) {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        widthBypass(root, consumer);
    }

    private void widthBypass(BinaryTreeElement root, Consumer<T> consumer) {
        Deque<BinaryTreeElement> stack = new LinkedList<>();

        stack.addLast(root);

        while (!stack.isEmpty()) {
            consumer.accept(stack.element().data);

            if (stack.element().leftChild != null) {
                stack.addLast(stack.element().leftChild);
            }

            if (stack.element().rightChild != null) {
                stack.addLast(stack.element().rightChild);
            }

            stack.remove();
        }
    }

    public boolean findData(T element) {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        return findData(element, root) != null;
    }

    private BinaryTreeElement findData(T data, BinaryTreeElement element) {
        if (element == null) {
            return null;
        }

        if (comparator.compare(element.data, data) == 0) {
            return element;
        }

        if (comparator.compare(element.data, data) < 0) {
            return findData(data, element.rightChild);
        }

        return findData(data, element.leftChild);
    }

    public int getValue() {
        return value;
    }

    public boolean delete(T element) {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        if (delete(element, root) != null) {
            value--;

            return true;
        }

        return false;
    }

    private BinaryTreeElement delete(T data, BinaryTreeElement element) {
        if (comparator.compare(root.data, data) == 0) {
            if (root.rightChild != null) {
                changeElement(root, findMinLeft(root.rightChild));

                return element;
            }

            if (root.leftChild != null) {
                changeElement(root, findMinLeft(root));

                return element;
            }

            root = null;

            return element;
        }

        if (element.rightChild != null && comparator.compare(element.rightChild.data, data) == 0) {
            if (element.rightChild.rightChild == null) {
                element.rightChild = element.rightChild.leftChild;

                return element;
            }

            if (element.rightChild.leftChild == null) {
                element.rightChild = element.rightChild.rightChild;
            }

            changeElement(element.rightChild, findMinLeft(element.rightChild.rightChild));

            return element;
        }

        if (element.leftChild != null && comparator.compare(element.leftChild.data, data) == 0) {
            if (element.leftChild.rightChild == null) {
                element.leftChild = element.leftChild.leftChild;

                return element;
            }

            if (element.leftChild.leftChild == null) {
                element.leftChild = element.leftChild.rightChild;

                return element;
            }

            changeElement(element.leftChild, findMinLeft(element.leftChild.rightChild));

            return element;
        }

        if (element.rightChild != null && comparator.compare(element.data, data) < 0) {
            return delete(data, element.rightChild);
        }

        if (element.leftChild != null && comparator.compare(element.data, data) > 0) {
            return delete(data, element.leftChild);
        }

        return null;
    }

    private void changeElement(BinaryTreeElement element1, BinaryTreeElement element2) {
        if (element2.leftChild != null) {
            element1.data = element2.leftChild.data;
            element2.leftChild = element2.leftChild.rightChild;
        } else {
            element1.data = element2.data;
            element1.rightChild = element2.rightChild;
        }
    }

    private BinaryTreeElement findMinLeft(BinaryTreeElement element) {
        if (element.leftChild == null || element.leftChild.leftChild == null) {
            return element;
        }

        return findMinLeft(element.leftChild);
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
        int maxSize = 1;
        int rowCount = 0;

        while (maxSize < value) {
            rowCount++;
            maxSize *= 2;
        }
        maxSize *= 2;

        if (rowCount % 2 == 0) {
            maxSize -= 1;
        }

        while (!queue.isEmpty()) {
            if (queue.element() != null) {
                queue.add(queue.element().leftChild);
                queue.add(queue.element().rightChild);
            } else if (currentLineIndex < value) {
                queue.add(null);
                queue.add(null);
            }

            for (int j = currentLineIndex; j <= maxSize / lineSize + currentLineIndex; j++) {
                line.append(" ");
            }

            if (queue.element() != null) {
                line.append(queue.remove().data);
            } else {
                queue.remove();
            }

            line.append(" ");

            currentLineIndex++;

            if (currentLineIndex >= lineSize) {
                lineSize *= 2;
                line.append(System.lineSeparator());
                currentLineIndex = 0;
            }
        }

        return line.toString();
    }

    private class BinaryTreeElement {
        private T data;
        private BinaryTreeElement leftChild = null;
        private BinaryTreeElement rightChild = null;
    }
}
