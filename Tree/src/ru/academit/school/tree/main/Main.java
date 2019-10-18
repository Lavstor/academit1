package ru.academit.school.tree.main;

import ru.academit.school.tree.ru.academit.school.Tree.Tree;

public class Main {
    public static void main(String[] args) {

        Tree tree1 = new Tree();
        tree1.add(10);
        tree1.add(10);
        tree1.add(10);
        tree1.add(22);
        tree1.add(4);
        tree1.add(6);
        tree1.add(1);
        tree1.add(0);
        tree1.add(4);
        tree1.add(16);
        tree1.add(19);
        tree1.add(7);
        tree1.add(5);
        tree1.add(9);
        System.out.println(tree1);
        System.out.println(tree1.findX(10));
    }
}
