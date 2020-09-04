package FractionTree;

public class Fraction {

    private int a;
    private int b;

    public Fraction() {
        a = 0;
        b = 0;
    }

    public Fraction(int numerator, int denominator) {
        this.a = numerator;
        this.b = denominator;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return a + "/" + b;
    }
}
