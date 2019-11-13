package ru.academit.school.tree.main;

import ru.academit.school.tree.ru.academit.school.Tree.Tree;
import java.util.function.Consumer;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Tree tree1 = new Tree();

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
        System.out.println(tree1.getNodeCount());
        System.out.println();

        System.out.println("Присутсвие эелемента");
        System.out.println(tree1.findData(144));
        System.out.println();

        System.out.println("Удаление");
        System.out.println(tree1.delete(134));
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
        System.out.println("--------------------------");

        tree1.add(137);
        tree1.add(128);
        tree1.add(127);
        tree1.add(126);
        tree1.add(128);
        tree1.add(129);
        tree1.add(129);
        tree1.add(128);
        tree1.add(130);

        Tree tree2 = new Tree((o1, o2) -> ((Comparable) o1).compareTo(o2));

        System.out.println(tree2);

        tree2.add(2);
        tree2.delete(2);
        System.out.println(tree2);
    }
}
