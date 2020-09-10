package FractionTree;

import java.math.BigInteger;

public class Fraction {

    private BigInteger numerator;
    private BigInteger denominator;

    public Fraction() {
        numerator = new BigInteger(String.valueOf(0));
        denominator = new BigInteger(String.valueOf(0));
    }

    public Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

}
