package ru.academit.school.tree.main;

import ru.academit.school.tree.ru.academit.school.Tree.Tree;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Tree tree1 = new Tree();
        System.out.println(tree1.isEmpty());

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
        tree1.add(121);

        System.out.println(tree1.isEmpty());
        System.out.println(tree1.getValue());
        System.out.println(tree1.findData(170));
        System.out.println(tree1.delete(170));

        System.out.println(tree1);
        System.out.println();

        tree1.deepRecursionBypass();
        System.out.println();

        tree1.deepBypass();
        System.out.println();

        tree1.widthBypass();
        System.out.println();
    }
}
