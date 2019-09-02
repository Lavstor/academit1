package ru.academitschool.arraylisthome.arraylist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    ArrayList<Integer> names;

    public ArrayListHome(String path) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(path);
             Scanner scanner = new Scanner(new FileInputStream(path))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (int j = 0; j < line.length(); j++) {
                    if (j == 0 && count1 % 2 == 0) {
                        writer.print("<tr><td>");
                    }
                    if (line.charAt(j) == '"') {
                        count1++;

                        if (j < line.length() - 1 && count1 % 2 == 0) {
                            if (line.charAt(j) == '"' && line.charAt(j + 1) == '"') {
                                writer.print('"');

                                count1--;
                                j++;
                            }
                        }
                        if (j == line.length() - 1) {
                            writer.println("</td></tr>");
                        }
                        continue;
                    }
                    if (line.charAt(j) == ',' && count1 % 2 == 0) {
                        count1 = 0;
                        if (j == line.length() - 1) {
                            writer.println("</td><td></td></tr>");
                        } else {
                            writer.print("</td><td>");
                        }
                        continue;
                    }
                    writer.print(replaceSpecialChar(line.charAt(j)));

                    if (j == line.length() - 1) {
                        if (count1 % 2 == 0) {
                            writer.println("</td></tr>");
                        } else {
                            writer.print("<br/>");
                        }
                    }
                }
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
