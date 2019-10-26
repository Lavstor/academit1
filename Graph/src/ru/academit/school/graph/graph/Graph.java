package ru.academit.school.graph.graph;

import java.util.Arrays;

public class Graph <T extends Comparable<T>>{
        private  T[][] graph;
        private int value;

        public Graph(T[][] array){
            for (T[] anArray : array) {
                value += anArray.length;
            }

            graph = Arrays.copyOf(array, array.length);
        }

    public void widthBypass() {
            boolean[] visited = new boolean[value];

            int k = 0;
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[i].length; j++){
                graph[i][j];
        }

        }
    }

    public int getValue() {
       return value;
    }


    @Override
    public String toString() {
            StringBuilder line = new StringBuilder();

            for(int i = 0; i < graph.length; i++){
                line.append(Arrays.toString(graph[i]));
                line.append("  ");
            }

        return line.toString();
    }
}
