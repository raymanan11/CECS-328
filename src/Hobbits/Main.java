package Hobbits;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static LinkedList<BigInteger> queue = new LinkedList<>();

    public static void BFS(Map<BigInteger, Vertex> vertexes) {
        
    }

    public static void main(String[] args) {
        try {
            Scanner inputFile = new Scanner(new File("HobbitExamples/input2.txt"));

            ArrayList<BigInteger> numbers = new ArrayList<>();
            ArrayList<BigInteger> minimalNumbers = new ArrayList<>();
            ArrayList<BigInteger> maximalNumbers = new ArrayList<>();
            ArrayList<BigInteger> neither = new ArrayList<>();

            while (inputFile.hasNextLine()) {
                numbers.add(new BigInteger(inputFile.nextLine()));
            }

            Collections.sort(numbers);

            int begin = 0;
            int end = numbers.size() - 1;
            for (int i = 0; i < numbers.size(); i++) {
                // System.out.println("NUmber being checked: " + numbers.get(i));
                // check #'s before numbers.get(i)
                boolean minimal = checkMinimal(numbers, begin, i);
                // System.out.println("Minimal: " + minimal);
                // check #'s after numbers.get(i)
                boolean maximal = checkMaximal(numbers, end, i);
                // System.out.println("Maximal: " + maximal);
                if (minimal) minimalNumbers.add(numbers.get(i));
                if (maximal) maximalNumbers.add(numbers.get(i));
                if (!maximal && !minimal) neither.add(numbers.get(i));

            }

            System.out.println("Miminal #'s: " + minimalNumbers);
            System.out.println("Maximal #'s: " +maximalNumbers);
            System.out.println("Neither: " +neither);

            Map<BigInteger, Vertex> vertexes = new HashMap<>();

            // add all minimum numbers as places to go from pad 1
            vertexes.put(new BigInteger("1"), new Vertex(minimalNumbers));

            // add neighbors for each minimal #
            for (int i = 0; i < minimalNumbers.size(); i++) {
                vertexes.put(minimalNumbers.get(i), new Vertex());
                // add neighbors from max
                for (int j = 0; j < maximalNumbers.size(); j++) {
                    if (minimalNumbers.get(i).gcd(maximalNumbers.get(j)).intValue() > 1 && maximalNumbers.get(j).compareTo(minimalNumbers.get(i)) > 0) vertexes.get(minimalNumbers.get(i)).neighbors.add(maximalNumbers.get(j));
                }
                // add neighbors from neither
                for (int j = 0; j < neither.size(); j++) {
                    if (minimalNumbers.get(i).gcd(neither.get(j)).intValue() > 1 && neither.get(j).compareTo(minimalNumbers.get(i)) > 0) vertexes.get(minimalNumbers.get(i)).neighbors.add(neither.get(j));
                }
            }

            // add neighbors for each neither #
            for (int i = 0; i < neither.size(); i++) {
                vertexes.put(neither.get(i), new Vertex());
                for (int j = 0; j < neither.size(); j++) {
                    if (!neither.get(i).equals(neither.get(j)) && neither.get(i).gcd(neither.get(j)).intValue() > 1 && neither.get(j).compareTo(neither.get(i)) > 0) { vertexes.get(neither.get(i)).neighbors.add(neither.get(j));

                    }
                }
                // add neighbors from max
                for (int j = 0; j < maximalNumbers.size(); j++) {
                    if (neither.get(i).gcd(maximalNumbers.get(j)).intValue() > 1 && maximalNumbers.get(j).compareTo(neither.get(i)) > 0) vertexes.get(neither.get(i)).neighbors.add(maximalNumbers.get(j));
                }
            }

            BFS(vertexes);

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            outputFile.close();

        }
        catch (IOException excpt) {
            System.out.println("File not found!");
        }
    }

    private static boolean checkMaximal(ArrayList<BigInteger> numbers, int end, int i) {
        // System.out.println("Check Maximal");
        for (int k = i + 1; k <= end; k++) {
//            System.out.println(numbers.get(i));
//            System.out.println(numbers.get(k));
//            System.out.println();
//            System.out.println("GCD: " + numbers.get(i).gcd(numbers.get(k)).intValue());
            if (numbers.get(i).gcd(numbers.get(k)).intValue() > 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkMinimal(ArrayList<BigInteger> numbers, int begin, int i) {
        // System.out.println("Check minimal");
        for (int j = begin; j < i; j++) {
//            System.out.println(numbers.get(j));
//            System.out.println(numbers.get(i));
//            System.out.println();
//            System.out.println("GCD: " + numbers.get(j).gcd(numbers.get(i)).intValue());
            if (numbers.get(j).gcd(numbers.get(i)).intValue() > 1) {
                return false;
            }
        }
        return true;
    }
}
