package RecursionCounting;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner inputFile;
    private static ArrayList<nNumber> nNumbers = new ArrayList<>();
    private static ArrayList<GCD> gcdProblems = new ArrayList<>();

    private static boolean reachedEuclidian;

    static void getScore(GCD gcdProblem) {
        // add the base case
        if (gcdProblem.getA().compareTo(BigInteger.ZERO) == 0 || gcdProblem.getB().compareTo(BigInteger.ZERO) == 0 || (gcdProblem.getA().compareTo(BigInteger.ZERO) == 0 && gcdProblem.getB().compareTo(BigInteger.ZERO) == 0)) {
            return;
        }

        for (int i = 0; i < nNumbers.size(); i++) {
            // get nNumber from ArrayList
            BigInteger nNumberBigInt = new BigInteger(String.valueOf(nNumbers.get(i).getNumber()));

            // if a AND b are divisible by an nNumber
            if (gcdProblem.getA().mod(nNumberBigInt).compareTo(BigInteger.ZERO) == 0 && gcdProblem.getB().mod(nNumberBigInt).compareTo(BigInteger.ZERO) == 0) {
                nNumbers.get(i).addScore(2);
                // parameters from constructor have to be a string
                getScore(new GCD(gcdProblem.getA().divide(nNumberBigInt), gcdProblem.getB().divide(nNumberBigInt)));
            }
            else if (gcdProblem.getA().compareTo(BigInteger.ONE) == 0 || gcdProblem.getB().compareTo(BigInteger.ONE) == 0 && reachedEuclidian) {
                return;
            }
            // if a is divisible by an nNumber and b isn't
            else if (gcdProblem.getA().mod(nNumberBigInt).compareTo(BigInteger.ZERO) == 0 && gcdProblem.getB().mod(nNumberBigInt).compareTo(BigInteger.ZERO) != 0) {
                nNumbers.get(i).addScore(1);
                getScore(new GCD(gcdProblem.getA().divide(nNumberBigInt), gcdProblem.getB()));
            }
            // if b is divisible by an nNumber and a isn't
            else if (gcdProblem.getB().mod(nNumberBigInt).compareTo(BigInteger.ZERO) == 0 && gcdProblem.getA().mod(nNumberBigInt).compareTo(BigInteger.ZERO) != 0) {
                nNumbers.get(i).addScore(1);
                getScore(new GCD(gcdProblem.getB().divide(nNumberBigInt), gcdProblem.getA()));
            }
        }

        // then add Euclidian's rule
        reachedEuclidian = true;
        BigInteger max = gcdProblem.getA().max(gcdProblem.getB());
        BigInteger min = gcdProblem.getA().min(gcdProblem.getB());
        getScore(new GCD(max.subtract(min), min));
    }

    public static void main(String[] args) {

        try {

            inputFile = new Scanner(new File("inputTest.txt"));

            int numOfNNumbers = inputFile.nextInt();

            // add the n numbers into an ArrayList
            for (int i = 0; i < numOfNNumbers; i++) {
                nNumbers.add(new nNumber(inputFile.nextInt(), 0));
            }

            inputFile.nextLine();

            // take remaining lines in file and add GCD problems into ArrayList
            while (inputFile.hasNextLine()) {
                String s = inputFile.nextLine();
                String[] arr = s.split(" ");
                GCD gcdProblem = new GCD(new BigInteger(arr[0]), new BigInteger(arr[1]));
                gcdProblems.add(gcdProblem);
            }

            getScore(gcdProblems.get(0));

//            for (GCD problem : gcdProblems) {
//                reachedEuclidian = false;
//                getScore(problem);
//            }

//            for (nNumber num : nNumbers) {
//                System.out.println(num.getNumber() + " " + num.getScore());
//            }

        }

        catch(IOException except) {
            System.out.println("File not found!");
        }

    }

    static class nNumber {

        private int number;
        private int score;

        public nNumber(int number, int score) {
            this.number = number;
            this.score = score;
        }

        public int getNumber() {
            return number;
        }

        public int getScore() {
            return score;
        }

        public void addScore(int number) {
            if (!reachedEuclidian) {
                score += number;
            }
        }
    }

    static class GCD {

        private BigInteger a;
        private BigInteger b;

        private String aString;
        private String bString;

        public GCD(BigInteger a, BigInteger b) {
            this.a = a;
            this.b = b;

            aString = a.toString();
            bString = b.toString();
        }

        public BigInteger getA() {
            return a;
        }

        public BigInteger getB() {
            return b;
        }

        public String getaString() {
            return aString;
        }

        public String getbString() {
            return bString;
        }

        @Override
        public String toString() {
            return a + "/" + b;
        }

    }
}
