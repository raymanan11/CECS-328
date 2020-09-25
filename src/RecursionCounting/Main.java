package RecursionCounting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            ArrayList<nNumber> nNumberz = new ArrayList<>();
            ArrayList<GCD> gcdProblems = new ArrayList<>();
            Scanner inputFile;

            inputFile = new Scanner(new File("input4.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            int numOfNNumbers = inputFile.nextInt();

            // add the n numbers into an ArrayList
            for (int i = 0; i < numOfNNumbers; i++) {
                nNumber number = new nNumber(inputFile.nextInt(), 0);
                nNumberz.add(number);
            }

            inputFile.nextLine();

            while (inputFile.hasNextLine()) {
                String s = inputFile.nextLine();
                String[] arr = s.split(" ");
                BigInteger a = new BigInteger(arr[0]);
                BigInteger b = new BigInteger(arr[1]);
                gcdProblems.add(new GCD(a, b));
            }

            int size = nNumberz.size();

            BigInteger a;
            BigInteger b;

            long start, end;

            start = System.currentTimeMillis();

            for (GCD gcdProblem : gcdProblems) {
                a = gcdProblem.a;
                b = gcdProblem.b;

                for (int i = 0; i < size; i++) {
                    nNumber nNumber = nNumberz.get(i);
                    BigInteger nNumberBigInt = nNumber.nNumberBigInt;

                    while (true) {
                        if (a.remainder(nNumberBigInt).compareTo(BigInteger.ZERO) == 0 && b.remainder(nNumberBigInt).compareTo(BigInteger.ZERO) == 0) {
                            nNumber.score += 2;
                            a = a.divide(nNumberBigInt);
                            b = b.divide(nNumberBigInt);
                        }
                        else if (a.remainder(nNumberBigInt).compareTo(BigInteger.ZERO) == 0 && b.remainder(nNumberBigInt).compareTo(BigInteger.ZERO) != 0) {
                            nNumber.score += 1;
                            a = a.divide(nNumberBigInt);
                        }
                        else if (b.remainder(nNumberBigInt).compareTo(BigInteger.ZERO) == 0 && a.remainder(nNumberBigInt).compareTo(BigInteger.ZERO) != 0) {
                            nNumber.score += 1;
                            b = b.divide(nNumberBigInt);
                        }
                        else {
                            a = gcdProblem.a;
                            b = gcdProblem.b;
                            break;
                        }
                    }
                }
            }

            for (nNumber num : nNumberz) {
                outputFile.println(num.number + " " + num.score);
            }

            outputFile.close();

            end = System.currentTimeMillis();

            System.out.println(end - start + " milliseconds");


        }

        catch(IOException except) {
            System.out.println("File not found!");
        }

    }

    static class nNumber {

        private int number;
        private int score;

        private String stringNumber;
        private BigInteger nNumberBigInt;

        public nNumber(int number, int score) {
            this.number = number;
            this.score = score;

            stringNumber = String.valueOf(number);
            nNumberBigInt = new BigInteger(stringNumber);
        }

    }

    static class GCD {
        BigInteger a;
        BigInteger b;

        public GCD(BigInteger a, BigInteger b) {
            this.a = a;
            this.b = b;
        }
    }

}
