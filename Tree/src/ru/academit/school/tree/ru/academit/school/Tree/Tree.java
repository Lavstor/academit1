package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<T> {
    private BinaryTreeNode root;
    private int nodesCount;
    private Comparator<T> comparator = (o1, o2) -> {
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

    public Tree() {
    }

    public Tree(T data) {
        root = new BinaryTreeNode(data);
        nodesCount++;
    }

    public Tree(Comparator<T> comparator, T data) {
        this.comparator = comparator;

        root = new BinaryTreeNode(data);
        nodesCount++;
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;

        nodesCount++;
    }

    public void add(T data) {
        nodesCount++;

        if (root != null) {
            add(data, root);
        } else {
            root = new BinaryTreeNode(data);
        }
    }

    private void add(T data, BinaryTreeNode element) {
        while (element != null) {
            if (comparator.compare(element.getData(), data) < 0) {
                if (element.getRightChild() == null) {
                    element.setRight(new BinaryTreeNode(data));

                    return;
                }
                element = element.getRightChild();
            } else {
                if (element.getLeftChild() == null) {
                    element.setLeft(new BinaryTreeNode(data));

                    return;
                }
                element = element.getLeftChild();
            }
        }
    }

    public void deepRecursionBypass(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        deepRecursionBypass(root, consumer);
    }

    private void deepRecursionBypass(BinaryTreeNode root, Consumer<T> consumer) {
        consumer.accept(root.getData());

        if (root.getLeftChild() != null) {
            deepRecursionBypass(root.getLeftChild(), consumer);
        }
        if (root.getRightChild() != null) {
            deepRecursionBypass(root.getRightChild(), consumer);
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
            BinaryTreeNode node = stack.removeLast();

            consumer.accept(node.getData());

            if (node.getRightChild() != null) {
                stack.addLast(node.getRightChild());
            }
            if (node.getLeftChild() != null) {
                stack.addLast(node.getLeftChild());
            }
        }
    }

    public void widthBypass(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        widthBypass(root, consumer);
    }

    private void widthBypass(BinaryTreeNode root, Consumer<T> consumer) {
        Deque<BinaryTreeNode> deque = new LinkedList<>();

        deque.addLast(root);

        while (!deque.isEmpty()) {
            BinaryTreeNode node = deque.remove();

            consumer.accept(node.getData());

            if (node.getLeftChild() != null) {
                deque.addLast(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                deque.addLast(node.getRightChild());
            }
        }
    }

    public boolean findData(T element) {
        if (root == null) {
            return false;
        }

        return findData(element, root);
    }

    private boolean findData(T data, BinaryTreeNode element) {
        while (element != null) {
            int compared = comparator.compare(element.getData(), data);

            if (compared == 0) {
                return true;
            }
            if (compared < 0) {
                element = element.getRightChild();
            } else {
                element = element.getLeftChild();
            }
        }

        return false;
    }

    public int getNodeCount() {
        return nodesCount;
    }

    public boolean delete(T element) {
        if (root == null) {
            return false;
        }

        return delete(element, root);
    }

    private boolean delete(T data, BinaryTreeNode element) {
        int compared = comparator.compare(element.getData(), data);

        if (compared == 0) {
            if (root.getRightChild() != null) {
                changeElement(root, findMinLeft(root.getRightChild()));

                return true;
            }
            if (root.getLeftChild() != null) {
                changeElement(root, findMinLeft(root));

                return true;
            }

            root = null;

            return true;
        }

        while (compared != 0) {
            if (compared < 0 && element.getRightChild() != null) {
                if (comparator.compare(element.getRightChild().data, data) != 0) {
                    element = element.getRightChild();

                    compared = comparator.compare(element.getData(), data);
                } else {
                    if (element.getRightChild().getRightChild() == null) {
                        element.setRight(element.getRightChild().getLeftChild());

                        return true;
                    }
                    if (element.getRightChild().getLeftChild() == null) {
                        element.setRight(element.getRightChild().getRightChild());

                        return true;
                    }
                    changeElement(element.getRightChild(), findMinLeft(element.getRightChild().getRightChild()));

                    return true;
                }
            } else if (element.getLeftChild() != null) {
                if (comparator.compare(element.getLeftChild().data, data) != 0) {
                    element = element.getLeftChild();

                    compared = comparator.compare(element.getData(), data);
                } else {
                    if (element.getLeftChild().getRightChild() == null) {
                        element.setLeft(element.getLeftChild().getLeftChild());

                        return true;
                    }
                    if (element.getLeftChild().getLeftChild() == null) {
                        element.setLeft(element.getLeftChild().getRightChild());

                        return true;
                    }
                    changeElement(element.getLeftChild(), findMinLeft(element.getLeftChild().getRightChild()));

                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
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

        while (!queue.isEmpty()) {
            if (queue.element() != null) {
                queue.add(queue.element().getLeftChild());
                queue.add(queue.element().getRightChild());
            } else if (currentLineIndex < nodesCount) {
                queue.add(null);
                queue.add(null);
            }

            if (queue.element() != null) {
                line.append(queue.remove().getData());
            } else {
                line.append("   ");
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
