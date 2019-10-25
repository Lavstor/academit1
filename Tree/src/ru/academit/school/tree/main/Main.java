package ru.academit.school.tree.main;

import ru.academit.school.tree.ru.academit.school.Tree.Tree;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Tree tree1 = new Tree();

        tree1.add(124);
        tree1.add(134);
        tree1.add(125);
        tree1.add(144);
        tree1.add(144);
        tree1.add(145);

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
