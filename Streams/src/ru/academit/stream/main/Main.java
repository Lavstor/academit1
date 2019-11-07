package ru.academit.stream.main;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(args[0]))) {
            BufferedOutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(args[1]));


        }
    }
}
