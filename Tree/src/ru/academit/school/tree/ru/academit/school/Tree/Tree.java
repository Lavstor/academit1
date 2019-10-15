package ru.academit.school.tree.ru.academit.school.Tree;

import java.util.Comparator;

public class Tree {
    private BinaryTreeElement root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(Comparable data) {
        root.add(data, root);

        return true;
    }

    private class BinaryTreeElement<T> {
        public T data;
        public BinaryTreeElement<T> leftChild;
        public BinaryTreeElement<T> rightChild;

        private BinaryTreeElement<T> add(T data, BinaryTreeElement<T> element) {

            if (element == null) {
                element = new BinaryTreeElement<>();
                element.data = data;

                return element;
            }

            while (data == this.data){
                throw new ArrayStoreException();
            }
            if (data < element.data) {
                //добавить справа
                element.rightChild = add(data, element.rightChild);
            } else {
                //добавить слева
                element.leftChild = add(data, element.leftChild);
                return element;
            }

            return null;
        }
    }

}
