package FractionTree;

import java.math.BigInteger;

public class Fraction {

    private int a;
    private int b;

    private BigInteger equation;

    public Fraction() {
        a = 0;
        b = 0;
    }

    public Boolean calculateEquation(BigInteger M, BigInteger N) {
        BigInteger asquared = new BigInteger(String.valueOf(a));
        asquared = asquared.pow(2);
        BigInteger bsquared = new BigInteger(String.valueOf(b));
        BigInteger b = bsquared;
        bsquared = bsquared.pow(2);

        equation = N.multiply(asquared).subtract(M.multiply(bsquared));
        equation = equation.abs();

        if (equation.compareTo(b) < 0) {
            return true;
        }
        else {
            return false;
        }
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
