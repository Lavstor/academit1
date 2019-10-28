package ru.academit.school.graph.main;

import ru.academit.school.graph.graph.Graph;

public class Main {
    public static void main(String[] args) {

        int[][] array1 = {{0, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 1, 0, 1}, {0, 0, 1, 0, 0, 1, 0}};
        Graph graph1 = new Graph(array1);
      //  System.out.println(graph1);
      //  System.out.println(graph1.getValue());
        graph1.widthBypass();
    }
}
