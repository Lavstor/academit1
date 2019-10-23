package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Deque;
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

    public void deepRecursion() {
        if (root == null) {
            return;
        }

        root.deepRecursion(root);
    }

    public void deep() {
        if (root == null) {
            return;
        }
        root.deep(root);
    }

    public void width() {
        if (root == null) {
            return;
        }
        root.width(root);
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
        if (root.delete(x, root) != null) {
            value--;

            return true;
        }

        return false;
    }

    private class BinaryTreeElement<T extends Comparable<T>> {
        private T data;
        private BinaryTreeElement<T> leftChild = null;
        private BinaryTreeElement<T> rightChild = null;

        private BinaryTreeElement(T data) {
            this.data = data;
        }

        private void deepRecursion(BinaryTreeElement<T> root) {
            Deque<BinaryTreeElement<T>> stack = new LinkedList<>();

            for (BinaryTreeElement<T> left = root; left != null; left = left.leftChild) {
                System.out.println(left.data);

                if (left.rightChild != null) {
                    stack.addLast(left.rightChild);
                }
            }

            while (!stack.isEmpty()) {
                deepRecursion(stack.removeLast());
            }
        }

        private void deep(BinaryTreeElement<T> root) {
            Deque<BinaryTreeElement<T>> stack = new LinkedList<>();

            stack.addLast(root);

            while (!stack.isEmpty()) {
                System.out.println(stack.element().data);

                if (stack.element().leftChild != null) {
                    stack.add(stack.element().leftChild);
                }

                if (stack.element().rightChild != null) {
                    stack.add(stack.element().rightChild);
                }

                stack.remove();
            }
        }

        private void width(BinaryTreeElement<T> root) {
            Deque<BinaryTreeElement<T>> stack = new LinkedList<>();

            stack.addLast(root);

            while (!stack.isEmpty()) {
                System.out.println(stack.element().data);

                if (stack.element().leftChild != null) {
                    stack.addLast(stack.element().leftChild);
                }
                if (stack.element().rightChild != null) {
                    stack.addLast(stack.element().rightChild);
                }

                stack.remove();
            }
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

        private void changeElement(BinaryTreeElement<T> element1, BinaryTreeElement<T> element2) {
            if (element2.leftChild != null) {
                element1.data = element2.leftChild.data;
                element2.leftChild = element2.leftChild.rightChild;
            } else {
                element1.data = element2.data;
                element1.rightChild = element2.rightChild;
            }
        }

        private BinaryTreeElement<T> delete(T x, BinaryTreeElement<T> element) {
            if (root.data.compareTo(x) == 0) {
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

            if (element.rightChild != null && element.rightChild.data.compareTo(x) == 0) {
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

            if (element.leftChild != null && element.leftChild.data.compareTo(x) == 0) {
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

            if (element.rightChild != null && x.compareTo(element.data) > 0) {
                return delete(x, element.rightChild);
            }
            if (element.leftChild != null && x.compareTo(element.data) < 0) {
                return delete(x, element.leftChild);
            }

            return null;
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
        value = 8;

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

