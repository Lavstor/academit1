package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<T> {
    private BinaryTreeNode root;
    private int nodeCount;
    private Comparator<T> comparator;

    public Tree() {
        comparator = (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }

            if (o1 == null) {
                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            //noinspection unchecked
            Comparable<T> object1 = (Comparable<T>) o1;

            return object1.compareTo(o2);
        };
    }

    public Tree(T data) {
        root = new BinaryTreeNode(data);
        nodeCount++;

        comparator = (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }

            if (o1 == null) {
                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            //noinspection unchecked
            Comparable<T> object1 = (Comparable<T>) o1;

            return object1.compareTo(o2);
        };
    }

    public Tree(Comparator<T> comparator, T data) {
        this.comparator = comparator;

        root = new BinaryTreeNode(data);
        nodeCount++;
    }

    public void add(T data) {
        nodeCount++;

        if (root != null) {
            add(data, root);
        } else {
            root = new BinaryTreeNode(data);
        }
    }

    private BinaryTreeNode add(T data, BinaryTreeNode element) {
        if (element == null) {
            element = new BinaryTreeNode(data);

            return element;
        }

        if (comparator.compare(element.getData(), data) < 0) {
            element.setRight(add(data, element.getRightChild()));

            return element;
        }

        element.setLeft(add(data, element.getLeftChild()));

        return element;
    }

    public void deepRecursionBypass(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        deepRecursionBypass(root, consumer);
    }

    private void deepRecursionBypass(BinaryTreeNode root, Consumer<T> consumer) {
        Deque<BinaryTreeNode> stack = new LinkedList<>();

        for (BinaryTreeNode left = root; left != null; left = left.getLeftChild()) {
            consumer.accept(left.getData());

            if (left.getRightChild() != null) {
                stack.addLast(left.getRightChild());
            }
        }

        while (!stack.isEmpty()) {
            deepRecursionBypass(stack.removeLast(), consumer);
        }
    }

    public void deepBypass(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        deepBypass(root, consumer);
    }

    private void deepBypass(BinaryTreeNode root, Consumer<T> consumer) {
        LinkedList<BinaryTreeNode> stack = new LinkedList<>();

        stack.addLast(root);

        while (!stack.isEmpty()) {
            consumer.accept(stack.getLast().getData());

            int size = stack.size() - 1;

            if (stack.get(size).getRightChild() != null) {
                stack.addLast(stack.get(size).getRightChild());
            }

            if (stack.get(size).getLeftChild() != null) {
                stack.addLast(stack.get(size).getLeftChild());
            }

            stack.remove(size);
        }
    }

    public void widthBypass(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        widthBypass(root, consumer);
    }

    private void widthBypass(BinaryTreeNode root, Consumer<T> consumer) {
        Deque<BinaryTreeNode> stack = new LinkedList<>();

        stack.addLast(root);

        while (!stack.isEmpty()) {
            consumer.accept(stack.element().getData());

            if (stack.element().getLeftChild() != null) {
                stack.addLast(stack.element().getLeftChild());
            }

            if (stack.element().getRightChild() != null) {
                stack.addLast(stack.element().getRightChild());
            }

            stack.remove();
        }
    }

    public boolean findData(T element) {
        if (root == null) {
            return false;
        }

        return findData(element, root) != null;
    }

    private BinaryTreeNode findData(T data, BinaryTreeNode element) {
        if (element == null) {
            return null;
        }

        if (comparator.compare(element.getData(), data) == 0) {
            return element;
        }

        if (comparator.compare(element.getData(), data) < 0) {
            return findData(data, element.getRightChild());
        }

        return findData(data, element.getLeftChild());
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public boolean delete(T element) {
        if (root == null) {
            return false;
        }

        if (delete(element, root) != null) {
            nodeCount--;

            return true;
        }

        return false;
    }

    private BinaryTreeNode delete(T data, BinaryTreeNode element) {
        if (comparator.compare(root.getData(), data) == 0) {
            if (root.getRightChild() != null) {
                changeElement(root, findMinLeft(root.getRightChild()));

                return element;
            }

            if (root.getLeftChild() != null) {
                changeElement(root, findMinLeft(root));

                return element;
            }

            root = null;

            return element;
        }

        if (element.getRightChild() != null && comparator.compare(element.getRightChild().getData(), data) == 0) {
            if (element.getRightChild().getRightChild() == null) {
                element.setRight(element.getRightChild().getLeftChild());

                return element;
            }

            if (element.getRightChild().getLeftChild() == null) {
                element.setRight(element.getRightChild().getRightChild());
            }

            changeElement(element.getRightChild(), findMinLeft(element.getRightChild().getRightChild()));

            return element;
        }

        if (element.getLeftChild() != null && comparator.compare(element.getLeftChild().getData(), data) == 0) {
            if (element.getLeftChild().getRightChild() == null) {
                element.setLeft(element.getLeftChild().getLeftChild());

                return element;
            }

            if (element.getLeftChild().getLeftChild() == null) {
                element.setLeft(element.getLeftChild().getRightChild());

                return element;
            }

            changeElement(element.getLeftChild(), findMinLeft(element.getLeftChild().getRightChild()));

            return element;
        }

        if (element.getRightChild() != null && comparator.compare(element.getData(), data) < 0) {
            return delete(data, element.getRightChild());
        }

        if (element.getLeftChild() != null && comparator.compare(element.getData(), data) > 0) {
            return delete(data, element.getLeftChild());
        }

        return null;
    }

    private void changeElement(BinaryTreeNode element1, BinaryTreeNode element2) {
        if (element2.getLeftChild() != null) {
            element1.setData(element2.getLeftChild().getData());

            element2.setLeft(element2.getLeftChild().getRightChild());
        } else {
            element1.setData(element2.getData());
            element1.setRight(element2.getRightChild());
        }
    }

    private BinaryTreeNode findMinLeft(BinaryTreeNode element) {
        if (element.getLeftChild() == null || element.getLeftChild().getLeftChild() == null) {

            return element;
        }

        return findMinLeft(element.getLeftChild());
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        Queue<BinaryTreeNode> queue = new LinkedList<>();

        BinaryTreeNode element = root;
        queue.add(element);

        int currentLineIndex = 0;
        int lineSize = 1;
        int maxSize = 1;
        int rowCount = 0;

        while (maxSize < nodeCount) {
            rowCount++;
            maxSize *= 2;
        }
        maxSize *= 2;

        if (rowCount % 2 == 0) {
            maxSize -= 1;
        }

        while (!queue.isEmpty()) {
            if (queue.element() != null) {
                queue.add(queue.element().getLeftChild());
                queue.add(queue.element().getRightChild());
            } else if (currentLineIndex < nodeCount) {
                queue.add(null);
                queue.add(null);
            }

            for (int j = currentLineIndex; j <= maxSize / lineSize + currentLineIndex; j++) {
                line.append(" ");
            }

            if (queue.element() != null) {
                line.append(queue.remove().getData());
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

    private class BinaryTreeNode {
        private T data;
        private BinaryTreeNode leftChild;
        private BinaryTreeNode rightChild;

        private BinaryTreeNode() {

        }

        private BinaryTreeNode(T data) {

            this.data = data;
        }

        private T getData() {

            return data;
        }

        private void setData(T data) {

            this.data = data;
        }

        private void setLeft(BinaryTreeNode left) {

            leftChild = left;
        }

        private void setRight(BinaryTreeNode right) {

            rightChild = right;
        }

        private BinaryTreeNode getLeftChild() {

            return leftChild;
        }

        private BinaryTreeNode getRightChild() {

            return rightChild;
        }
    }
}
