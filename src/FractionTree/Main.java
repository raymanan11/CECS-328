package FractionTree;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.*;

public class Main {

    private static BigInteger M;
    private static BigInteger N;

    private static BigInteger outputFileNumerator;
    private static BigInteger outputFileDenominator;

    private static Fraction left;
    private static Fraction right;

    static void buildTree() {

        BigInteger numerator = left.getNumerator().add(right.getNumerator());
        BigInteger denominator = left.getDenominator().add(right.getDenominator());

        Fraction fraction = new Fraction(numerator, denominator);

        BigInteger aSquared = numerator.multiply(numerator);
        BigInteger bSquared = denominator.multiply(denominator);

        BigInteger equation = (N.multiply(aSquared)).subtract(M.multiply(bSquared));
        BigInteger absEquation = equation.abs();

        BigInteger negateEquation = equation.negate();

        if (absEquation.compareTo(denominator) < 0) {
            System.out.println(numerator);
            System.out.println(denominator);
            outputFileNumerator = numerator;
            outputFileDenominator = denominator;
            return;
        }

        if (negateEquation.compareTo(absEquation) == 0) {
            left = fraction;
        }
        else {
            right = fraction;
        }

        buildTree();

    }

    public static void main(String[] args) {

        try {

            Scanner inputFile = new Scanner(new File("input11.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            BigInteger m = new BigInteger(inputFile.next());
            BigInteger n = new BigInteger(inputFile.next());

            // set current M and N as a class variable
            M = m;
            N = n;

            // generate start fractions 0/1 and 1/0
            Fraction startFraction1 = new Fraction(new BigInteger(String.valueOf(0)), new BigInteger(String.valueOf(1)));
            Fraction startFraction2 = new Fraction(new BigInteger(String.valueOf(1)), new BigInteger(String.valueOf(0)));

            left = startFraction1;
            right = startFraction2;

            buildTree();

            // write a and b to output file
            outputFile.println(outputFileNumerator);
            outputFile.println(outputFileDenominator);
            outputFile.close();

        }

        catch(IOException excpt) {
            System.out.println("File Not Found!");
        }

    }

    static class Fraction {

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

        public void changeFraction(BigInteger numerator, BigInteger denominator) {
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

}
