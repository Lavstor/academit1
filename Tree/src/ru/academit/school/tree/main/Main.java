package ru.academit.school.tree.main;

import ru.academit.school.tree.ru.academit.school.Tree.Tree;

public class Main {
    public static void main(String[] args) {

        Tree tree1 = new Tree();
        tree1.add(110);
        tree1.add(10);
        tree1.add(9);
        tree1.add(8);
        tree1.add(13);
        tree1.add(14);
        tree1.add(12);
        tree1.add(120);
        tree1.add(120);
        tree1.add(170);
        tree1.add(111);

        System.out.println(tree1);
        System.out.println();

      //  tree1.deepRecursion();

        tree1.deep();
    }
}
