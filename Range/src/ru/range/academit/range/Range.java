package packe.range.ru;

import java.lang.reflect.Array;
import java.util.Arrays;

class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getRange() {

        return Math.abs(from - to);
    }

    public boolean isInside(double x) {
        return !(x < from || x > to);
    }

    public Range getIntersection(Range range) {

        if (range.from >= to || range.to <= from) {
            return null;
        }
        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (range.from > to || range.to < from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }
        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (range.from > to || range.to < from) {
            return new Range[]{};
        }
        return new Range[]{new Range(Math.max(from, range.from), Math.min(to, range.to))};
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }
}

class Main {
    public static void main(String[] args) {
        Range range = new Range(2, 3);
        Range range2 = new Range(2.5, 5);
        Range[] rangee = range.getUnion(range2);

        System.out.println(rangee[0].isInside(1000000));
    }
}
