package ru.academit.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getRangeLength() {
        return to - from;
    }

    public boolean isInside(double x) {
        return x > from && x < to;
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
        if (range.from == from && range.to < to) {
            return new Range[]{new Range(range.to, to)};
        } else if (range.to == to && from < range.from) {
            return new Range[]{new Range(from, range.from)};
        }
        if (range.from > from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        } else if(range.from < from && to > range.to){
            return new Range[]{new Range(range.to, to)};
        }
        return new Range[]{};
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

    public void print() {
        System.out.println(from);
        System.out.println(to);
    }
}
