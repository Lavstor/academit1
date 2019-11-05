package ru.academit.school.tree.main;

import ru.academit.school.tree.ru.academit.school.Tree.Tree;

import java.util.function.Consumer;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Object x = 9;

        Tree tree1 = new Tree(x);

        tree1.add(134);
        tree1.add(125);
        tree1.add(144);
        tree1.add(144);
        tree1.add(148);
        tree1.add(125);
        tree1.add(125);
        tree1.add(146);
        tree1.add(147);
        tree1.add(145);

        Consumer consumer = c1 -> System.out.print(c1 + " ");

        System.out.println("Дерево");
        System.out.println(tree1);
        System.out.println();


        System.out.println("Пустое?");
        System.out.println(tree1.isEmpty());
        System.out.println();

        System.out.println("Кол-во вершин");
        System.out.println(tree1.getValue());
        System.out.println();

        System.out.println("Присутсвие эелемента");
        System.out.println(tree1.findData(144));
        System.out.println();

        System.out.println("Удаление");
        System.out.println(tree1.delete(144));
        System.out.println();

        System.out.println("Дерево");
        System.out.println(tree1);
        System.out.println();

        System.out.println("Обход в глубину с рекурисей");
        tree1.deepRecursionBypass(consumer);
        System.out.println();

        System.out.println("Обход в глубину");
        tree1.deepBypass(consumer);
        System.out.println();

        System.out.println("Обход в ширину");
        tree1.widthBypass(consumer);
        System.out.println();
    }
}
