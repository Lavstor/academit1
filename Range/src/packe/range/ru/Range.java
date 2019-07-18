package packe.range.ru;


class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getRange() {
        return from - to;
    }
}
class Main {
    public static void main(String[] args) {
        Range range = new Range(3, 2);

    }

}

