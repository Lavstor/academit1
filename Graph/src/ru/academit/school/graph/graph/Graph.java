package ru.academit.school.graph.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<T extends Comparable<T>> {
    private int[][] graph;
    private int value;

    public Graph(int[][] array) {
        graph = Arrays.copyOf(array, array.length);
        value = array.length;
    }

    public void widthBypass() {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        for (int i = 0; i < graph.length; i++) {
            while (!queue.isEmpty()) {
                int index = queue.remove();

                if (!visited[index]) {
                    for (int j = 0; j < graph.length; j++) {
                        if (graph[index][j] != 0) {
                            queue.add(j);
                        }
                    }
                    visited[index] = true;

                    System.out.print(index + 1 + " ");
                }
            }

            if (!visited[i]) {
                queue.add(i);
            }
        }
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < graph.length; i++) {
            line.append(Arrays.toString(graph[i]));
            line.append("\n");
        }

        return line.toString();
    }
}
