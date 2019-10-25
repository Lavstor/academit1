package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Tree<T extends Comparable<T>> {
    private BinaryTreeElement root;
    private static int value;

    public void add(Comparable<T> data) {
        value++;

        if (root == null) {
            root = new BinaryTreeElement(data);

            return;
        }

        root.add(data, root);
    }

    public void deepRecursionBypass() {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        root.deepRecursionBypass(root);
    }

    public void deepBypass() {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        root.deepBypass(root);
    }

    public void widthBypass() {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        root.widthBypass(root);
    }

    public boolean findData(Comparable element) {
        if (root == null) {
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        return root.findData(element, root) != null;
    }

    public int getValue() {
        return value;
    }

    public boolean delete(Comparable element) {
        if(root == null){
            throw new IndexOutOfBoundsException("Пустое дерево");
        }

        if (root.delete(element, root) != null) {
            value--;

            return true;
        }

        return false;
    }

    private class BinaryTreeElement<E extends Tree<T>> {
        private T data;
        private BinaryTreeElement leftChild = null;
        private BinaryTreeElement rightChild = null;

        private BinaryTreeElement(T data) {
            this.data = data;
        }

        private void deepRecursionBypass(BinaryTreeElement root) {
            Deque<BinaryTreeElement> stack = new LinkedList<>();

            for (BinaryTreeElement left = root; left != null; left = left.leftChild) {
                System.out.println(left.data);

                if (left.rightChild != null) {
                    stack.addLast(left.rightChild);
                }
            }

            while (!stack.isEmpty()) {
                deepRecursionBypass(stack.removeLast());
            }
        }

        private void deepBypass(BinaryTreeElement root) {
            LinkedList<BinaryTreeElement> stack = new LinkedList<>();

            stack.addLast(root);

            while (!stack.isEmpty()) {
                System.out.println(stack.getLast().data);

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

        private void widthBypass(BinaryTreeElement root) {
            Deque<BinaryTreeElement> stack = new LinkedList<>();

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

        private BinaryTreeElement add(Comparable data, BinaryTreeElement element) {
            if (element == null) {
                element = new BinaryTreeElement(data);

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

        private BinaryTreeElement findData(Comparable data, BinaryTreeElement element) {
            if (element == null) {
                return null;
            }

            if (element.data.compareTo(data) == 0) {
                return element;
            } else {
                if (data.compareTo(element.data) > 0) {
                    return findData(data, element.rightChild);
                } else {
                    return findData(data, element.leftChild);
                }
            }
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

        private BinaryTreeElement delete(Comparable data, BinaryTreeElement element) {
            if (root.data.compareTo(data) == 0) {
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

            if (element.rightChild != null && element.rightChild.data.compareTo(data) == 0) {
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

            if (element.leftChild != null && element.leftChild.data.compareTo(data) == 0) {
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

            if (element.rightChild != null && data.compareTo(element.data) > 0) {
                return delete(data, element.rightChild);
            }

            if (element.leftChild != null && data.compareTo(element.data) < 0) {
                return delete(data, element.leftChild);
            }

            return null;
        }

        private BinaryTreeElement findMinLeft(BinaryTreeElement element) {
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

