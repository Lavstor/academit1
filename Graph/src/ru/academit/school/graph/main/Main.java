package ru.academit.school.graph.main;

import ru.academit.school.graph.graph.Graph;

public class Main {
    public static void main(String[] args) {

        Integer[][] array1= {{1,3,5,6}, {22,73,1}, {1}, {255,2}};
        Graph graph1 = new Graph(array1);
        System.out.println(graph1);
        System.out.println(graph1.getValue());
    }
}
