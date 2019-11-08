package ru.academit.stream.main;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] res2 = new byte[100000];
        int off = 0;

        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(args[0]))) {
            int read;

            while ((read = stream.read(res2, off, res2.length - off)) != -1) {
                off += read;
            }
        }

        try (BufferedOutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(args[1]))) {
            outputStream2.write(res2, 0, off);
        }

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(args[2]))) {
            for (int i = 1; i <= 100; i++) {
                printWriter.println("Строка " + i);
            }
            printWriter.println("Это println");
            printWriter.printf(" Это %S! %n", "printf");
            printWriter.print(" Это print");
        }
    }
}
