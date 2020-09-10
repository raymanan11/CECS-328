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

    static void buildTree(Fraction left, Fraction right) {

        // add numerator from left and right fractions
        BigInteger numerator = left.getNumerator().add(right.getNumerator());
        // add denominator from left and right fractions
        BigInteger denominator = left.getDenominator().add(right.getDenominator());

        // create fraction object for newly added numerator and denominator
        Fraction fraction = new Fraction(numerator, denominator);

        // get asquared
        BigInteger aSquared = numerator.multiply(numerator);
        // get bsquared
        BigInteger bSquared = denominator.multiply(denominator);

        // equation that is used to determine whether to go left or right depending if negative or positive
        BigInteger equation = (N.multiply(aSquared)).subtract(M.multiply(bSquared));
        // absolute value of equation to use for base case so you know when to stop and output to output fle
        BigInteger absEquation = equation.abs();

        // negating equatio so that this can be used to tell whether equation is positive or negative
        BigInteger negateEquation = equation.negate();

        // if |Na^2 - Mb^2| < b then that means we got a and b and can output it to output file
        if (absEquation.compareTo(denominator) < 0) {
            System.out.println(numerator);
            System.out.println(denominator);
            outputFileNumerator = numerator;
            outputFileDenominator = denominator;
            return;
        }

        Fraction l;
        Fraction r;

        // if the equation is negative then we go right
        if (negateEquation.compareTo(absEquation) == 0) {
            l = fraction;
            r = right;
        }
        // else we go left
        else {
            l = left;
            r = fraction;
        }

        buildTree(l, r);

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

            buildTree(startFraction1, startFraction2);

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
