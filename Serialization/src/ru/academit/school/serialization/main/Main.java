package ru.academit.school.serialization.main;

import ru.academit.school.serialization.matrix.Matrix;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //int[][] array = {{1, 2, 3}, {2, 1, 4}, {3, 4, 1}};
        int[][] array = {{1, 0, 9, 6}, {0, 1, 6, 7}, {9, 6, 1, 5}, {6, 7, 5, 1}};

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]))) {
            out.writeObject(new Matrix(array));
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(args[1]))) {
            Matrix matrix1 = (Matrix) in.readObject();

            System.out.println(matrix1);
        }
    }
}
