package ru.academit.school.graph.graph;

import java.util.Arrays;
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

    public void widthBypass(Consumer<Integer> consumer) {
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

                    consumer.accept(index);
                }
            }
        }
    }

    public void deepBypass(Consumer<Integer> consumer) {
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
                    consumer.accept(index);

                    visited[index] = true;
                }
            }
        }
    }

    public void deepRecursionBypass(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[graph.length];

        deepRecursionBypass(0, visited, consumer);
    }

    private void deepRecursionBypass(int index, boolean[] visited, Consumer<Integer> consumer) {
        visited[index] = true;

        consumer.accept(index);

        for (int i = 0; i < graph.length; i++) {
            if (graph[index][i] != 0 && !visited[i]) {
                deepRecursionBypass(i, visited, consumer);
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
