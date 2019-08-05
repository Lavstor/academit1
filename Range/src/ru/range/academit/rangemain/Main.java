package ru.range.academit.rangemain;

import ru.range.academit.range.Range;

class Main {
    public static void main(String[] args) {
        Range range1 = new Range(15, 22);
        Range range2 = new Range(15, 19);

        Range[] ranges1 = range1.getUnion(range2);
        Range intersection = range1.getIntersection(range2);
        Range[] ranges2 = range1.getDifference(range2);

        System.out.println(intersection);

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
        System.out.println("В отрезке: " + ranges1[0].isInside(2.4));
        System.out.println("Длинна отрезка: " + range1.getRangeLength());
    }
}
