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

        BigInteger numerator = left.getNumerator().add(right.getNumerator());
        BigInteger denominator = left.getDenominator().add(right.getDenominator());

        Fraction fraction = new Fraction(numerator, denominator);

        BigInteger aSquared = numerator.multiply(numerator);
        BigInteger bSquared = denominator.multiply(denominator);

        BigInteger equation = (N.multiply(aSquared)).subtract(M.multiply(bSquared));
        BigInteger absEquation = equation.abs();

        BigInteger negateEquation = equation.negate();

        if (absEquation.compareTo(denominator) < 0) {
            outputFileNumerator = numerator;
            outputFileDenominator = denominator;
            return;
        }

        Fraction l;
        Fraction r;

        if (negateEquation.compareTo(absEquation) == 0) {
            l = fraction;
            r = right;
        }
        else {
            l = left;
            r = fraction;
        }

        buildTree(l, r);

    }

    public static void main(String[] args) {

        try {

            Scanner inputFile = new Scanner(new File("input4.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            BigInteger m = new BigInteger(inputFile.next());
            BigInteger n = new BigInteger(inputFile.next());

            M = m;
            N = n;

            Fraction startFraction1 = new Fraction(new BigInteger(String.valueOf(0)), new BigInteger(String.valueOf(1)));
            Fraction startFraction2 = new Fraction(new BigInteger(String.valueOf(1)), new BigInteger(String.valueOf(0)));

            buildTree(startFraction1, startFraction2);

            outputFile.println(outputFileNumerator);
            outputFile.println(outputFileDenominator);
            outputFile.close();

        }

        catch(IOException excpt) {
            System.out.println("File Not Found!");
        }

    }

}
