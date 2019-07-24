package main.range.academit.ru;

import packe.range.ru.Range;

class Main {
    public static void main(String[] args) {
        Range range1 = new Range(2, 3);
        Range range2 = new Range(2.5, 5);

        Range[] ranges1 = range1.getUnion(range2);
        Range intersection = range1.getIntersection(range2);
        Range[] ranges2 = range1.getDifference(range2);

        intersection.print();

        int i = 0;

        for (Range aRanges1 : ranges1) {
            System.out.println(i + 1 + " отрезок (объединение) : ");
            aRanges1.print();
            i++;
        }
        int j = 0;

        for (Range aRanges2 : ranges2) {
            System.out.println(j + 1 + " отрезок (разница) : ");
            aRanges2.print();
            j++;
        }
        System.out.println("В отрезке: " + ranges1[0].isInside(1000000));
        System.out.println("Длинна отрезка: " + range1.getRangeLength());
    }
}
