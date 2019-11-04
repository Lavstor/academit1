package ru.academit.school.graph.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private int[][] graph;

    public Graph(int[][] array) {
        for (int[] row : array) {
            if (row.length != array.length) {
                throw new IllegalArgumentException("Массив должен быть квадратным");
            }
        }

        graph = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            graph[i] = Arrays.copyOf(array[i], array.length);
        }
    }

    public void widthBypass() {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }

            queue.add(i);

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
        }
    }

    public void deepBypass() {
        boolean[] visited = new boolean[graph.length];
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }

            stack.add(i);

            while (!stack.isEmpty()) {
                int index = stack.removeLast();

                if (!visited[index]) {
                    for (int j = graph.length - 1; j >= 0; j--) {
                        if (graph[index][j] != 0) {
                            stack.add(j);
                        }
                    }

                    System.out.print(index + 1 + " ");
                    visited[index] = true;
                }
            }
        }
    }

    public int deepRecursionBypass(int index) {
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < visited.length; i++) {
            if(!visited[index]){
               for(int j = graph.length - 1; j > 0; j--) {
                   stack.add(j);
               }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();

        for (int[] row : graph) {
            line.append(Arrays.toString(row));
            line.append(System.lineSeparator());
        }

        return line.toString();
    }
}
