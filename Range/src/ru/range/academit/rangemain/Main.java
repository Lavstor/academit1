package ru.range.academit.rangemain;

import ru.range.academit.range.Range;

class Main {
    public static void main(String[] args) {
        Range range1 = new Range(20, 24);
        Range range2 = new Range(22, 25);

        Range[] union = range1.getUnion(range2);
        Range intersection = range1.getIntersection(range2);
        Range[] difference = range1.getDifference(range2);

        System.out.println("Пересечение: " + intersection);
        System.out.println();

        for (int i = 0; i < union.length; i++) {
            System.out.println(i + 1 + " отрезок (объединение) : ");
            System.out.println(union[i]);
            System.out.println();
        }
        for (int i = 0; i < difference.length; i++) {
            System.out.println(i + 1 + " отрезок (разница) : ");
            System.out.println(difference[i]);
            System.out.println();
        }
        System.out.println("В отрезке: " + union[0].isInside(2.4));
        System.out.println("Длинна отрезка: " + range1.getLength());
    }
}
