package ru.academit.school.tree.main;

import ru.academit.school.tree.ru.academit.school.Tree.Tree;

public class Main {
    public static void main(String[] args) {

        Tree tree1 = new Tree();
        tree1.add(10);
        tree1.add(10);
        tree1.add(9);
        tree1.add(10);
        tree1.add(110);
        tree1.add(12);
        tree1.add(130);
        tree1.add(100);
        tree1.add(150);
        tree1.add(140);


        System.out.println(tree1);
        tree1.delete(130);
        System.out.println(tree1);
        System.out.println();

    }
}
