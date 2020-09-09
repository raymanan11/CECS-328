package FractionTree;

import java.math.BigInteger;

public class Fraction {

    private BigInteger a;
    private BigInteger b;

    private BigInteger equation;

    public Fraction() {
        a = new BigInteger(String.valueOf(0));
        b = new BigInteger(String.valueOf(0));
    }

    public Fraction(BigInteger numerator, BigInteger denominator) {
        this.a = numerator;
        this.b = denominator;
    }

    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }

    @Override
    public String toString() {
        return a + "/" + b;
    }

}
