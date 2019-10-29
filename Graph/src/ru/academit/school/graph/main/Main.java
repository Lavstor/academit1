package ru.academit.school.graph.main;

import ru.academit.school.graph.graph.Graph;

public class Main {
    public static void main(String[] args) {

        int[][] array1 = {{0, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 1, 0, 1}, {0, 0, 1, 0, 0, 1, 0}};
        Graph graph1 = new Graph(array1);

        System.out.println("Обход в ширину");
        graph1.widthBypass();
        System.out.println();

        System.out.println("Обход в глубину");
        graph1.deepBypass();
    }
}
