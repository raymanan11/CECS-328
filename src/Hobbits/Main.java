package Hobbits;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner inputFile = new Scanner(new File("HobbitExamples/input.txt"));

            ArrayList<BigInteger> numbers = new ArrayList<>();

            while (inputFile.hasNextLine()) {
                numbers.add(new BigInteger(inputFile.nextLine()));
            }

            Collections.sort(numbers);

            int begin = 0;
            int end = numbers.size() - 1;
            for (int i = 0; i < numbers.size(); i++) {
                System.out.println("Current Number being checked: " + numbers.get(i));
                // check #'s before numbers.get(i)
                boolean minimal = checkMinimal(numbers, begin, i);
                System.out.println("Minimal: " + minimal);
                // check #'s after numbers.get(i)
                boolean maximal = checkMaximal(numbers, end, i);
                System.out.println("Maximal: " + maximal);
            }

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            outputFile.close();

        } catch (IOException excpt) {
            System.out.println("File not found!");
        }
    }

    private static boolean checkMaximal(ArrayList<BigInteger> numbers, int end, int i) {
//        System.out.println("Check Maximal");
        for (int k = i + 1; k <= end; k++) {
//            System.out.println(numbers.get(i));
//            System.out.println(numbers.get(k));
//            System.out.println();
            if (numbers.get(i).gcd(numbers.get(k)).intValue() > 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkMinimal(ArrayList<BigInteger> numbers, int begin, int i) {
//        System.out.println("Check minimal");
        for (int j = begin; j < i; j++) {
//            System.out.println(numbers.get(j));
//            System.out.println(numbers.get(i));
//            System.out.println();
            if (numbers.get(j).gcd(numbers.get(i)).intValue() > 1) {
                return false;
            }
        }
        return true;
    }
}
