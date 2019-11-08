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

    }
}
