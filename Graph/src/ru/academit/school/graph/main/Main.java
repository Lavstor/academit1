package ru.academit.school.graph.main;

import ru.academit.school.graph.graph.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        int[][] array1 = {{0, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 1, 0, 1}, {0, 0, 1, 0, 0, 1, 0}};
        Graph graph1 = new Graph(array1);

        System.out.println("Обход в ширину");

        Consumer<Integer> consumer1 = element1 -> System.out.print(element1 + 1 + " ");
        graph1.widthBypass(consumer1);

        System.out.println();

        System.out.println("Обход в глубину");
        graph1.deepBypass(consumer1);
        System.out.println();

        System.out.println("Обход в глубину рекурсивный");
        graph1.deepRecursionBypass(consumer1);
        System.out.println();
    }
}
