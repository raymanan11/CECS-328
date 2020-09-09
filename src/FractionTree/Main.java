package FractionTree;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;
import java.io.*;

public class Main {

    static void buildTree(Fraction left, Fraction right, BigDecimal sqrtM, BigDecimal sqrtN, BigDecimal M, BigDecimal N, PrintWriter outputFile) {

        BigInteger numerator = left.getA().add(right.getA());
        BigInteger denominator = left.getB().add(right.getB());

        BigDecimal BDnumerator = new BigDecimal(numerator);
        BigDecimal BDdenominator = new BigDecimal(denominator);

        Fraction fraction = new Fraction(numerator, denominator);

        BigDecimal compare1 = sqrtM.multiply(BDdenominator);
        BigDecimal compare2 = sqrtN.multiply(BDnumerator);

        BigDecimal asquared = BDnumerator;
        asquared = asquared.pow(2);
        BigDecimal bsquared = BDdenominator;
        BigDecimal b = bsquared;
        bsquared = bsquared.pow(2);

        BigDecimal equation = N.multiply(asquared).subtract(M.multiply(bsquared));
        equation = equation.abs();

        if (equation.compareTo(b) < 0) {
            outputFile.println(numerator);
            outputFile.println(denominator);
            outputFile.close();
        }

        else if (compare1.compareTo(compare2) < 0) {
            buildTree(left, fraction, sqrtM, sqrtN, M, N, outputFile);
        }
        else if (compare1.compareTo(compare2) > 0) {
            buildTree(fraction, right, sqrtM, sqrtN, M, N, outputFile);
        }

    }

    public static void main(String[] args) {

        try {

            Scanner inputFile = new Scanner(new File("input7.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            BigDecimal m = new BigDecimal(inputFile.next());
            BigDecimal n = new BigDecimal(inputFile.next());

            BigDecimal sqrtM = m.sqrt(new MathContext(200));
            BigDecimal sqrtN = n.sqrt(new MathContext(200));

            Fraction startFraction1 = new Fraction(new BigInteger(String.valueOf(0)), new BigInteger(String.valueOf(1)));
            Fraction startFraction2 = new Fraction(new BigInteger(String.valueOf(1)), new BigInteger(String.valueOf(0)));

            buildTree(startFraction1, startFraction2, sqrtM, sqrtN, m, n, outputFile);

        }

        catch(IOException excpt) {
            System.out.println("File Not Found!");
        }

    }

}
