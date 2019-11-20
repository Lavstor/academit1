package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<T> {
    private BinaryTreeNode root;
    private int nodesCount;
    private Comparator<T> comparator;

    public Tree() {
        createComparator();
    }

    public Tree(T data) {
        root = new BinaryTreeNode(data);
        createComparator();

        nodesCount++;
    }

    public Tree(Comparator<T> comparator, T data) {
        this.comparator = comparator;

        root = new BinaryTreeNode(data);
        nodesCount++;
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void createComparator() {
        this.comparator = (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            @SuppressWarnings("unchecked")
            Comparable<T> object1 = (Comparable<T>) o1;

            return object1.compareTo(o2);
        };
    }

    public void add(T data) {
        nodesCount++;

        if (root == null) {
            root = new BinaryTreeNode(data);

            return;
        }
        BinaryTreeNode element = root;

        while (true) {
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
        Queue<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove();

            consumer.accept(node.getData());

            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }
    }

    public boolean findData(T data) {
        if (root == null) {
            return false;
        }
        BinaryTreeNode element = root;

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

    public int getNodesCount() {
        return nodesCount;
    }

    public boolean delete(T data) {
        if (root == null) {
            return false;
        }
        int compared = comparator.compare(root.getData(), data);

        if (compared == 0) {
            nodesCount--;

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
        BinaryTreeNode element = root;

        while (true) {
            if (compared < 0) {
                if (element.getRightChild() == null) {
                    return false;
                }
                if (comparator.compare(element.getRightChild().data, data) != 0) {
                    element = element.getRightChild();
                } else {
                    break;
                }
            } else {
                if (element.getLeftChild() == null) {
                    return false;
                }
                if (comparator.compare(element.getLeftChild().data, data) != 0) {
                    element = element.getLeftChild();
                } else {
                    break;
                }
            }

            compared = comparator.compare(element.getData(), data);
        }
        nodesCount--;

        if (compared < 0) {
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

    private void changeElement(BinaryTreeNode element1, BinaryTreeNode element2) {
        if (element2.getLeftChild() != null) {
            element1.setData(element2.getLeftChild().getData());
            element2.setLeft(element2.getLeftChild().getRightChild());

            if (element1.getRightChild() != null && comparator.compare(element1.getData(), element1.getRightChild().getData()) == 0) {
                if (element1.getRightChild().getLeftChild() != null) {
                    findMinLeft(element1.getRightChild()).getLeftChild().setLeft(element1.getLeftChild());
                } else {
                    element1.getRightChild().setLeft(element1.getLeftChild());
                }
                element1.setLeft(element1.getRightChild());
                element1.setRight(element1.getLeftChild().getRightChild());

                element1.getLeftChild().setRight(null);
            }
        } else {
            element1.setData(element2.getData());
            element1.setRight(element2.getRightChild());
        }
    }

    private BinaryTreeNode findMinLeft(BinaryTreeNode element) {
        while (true) {
            if (element.getLeftChild() == null || element.getLeftChild().getLeftChild() == null) {
                return element;
            }

            element = element.getLeftChild();
        }
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
