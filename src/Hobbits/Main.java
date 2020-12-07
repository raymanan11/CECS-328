// Raymond An

package Hobbits;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static LinkedList<BigInteger> queue = new LinkedList<>();
    private static ArrayList<BigInteger> uniqueNumbers = new ArrayList<>();
    private static ArrayList<BigInteger> repeatedNumbers = new ArrayList<>();
    private static ArrayList<BigInteger> maximalNumbers;
    private static ArrayList<BigInteger> neither;

    private static String path = "";

    public static void BFS(Map<BigInteger, Vertex> vertexes) {

        queue.add(new BigInteger("1"));
        while (!queue.isEmpty()) {
            BigInteger u = queue.remove();
            Vertex currentVertex = vertexes.get(u);
            for (BigInteger bigInteger : currentVertex.neighbors) {
                Vertex neighbor = vertexes.get(bigInteger);
                if (neighbor.color.equals("white")) {
                    neighbor.color = "gray";
                    neighbor.discoveredTime = currentVertex.discoveredTime + 1;
                    neighbor.previous = u;
                    queue.add(bigInteger);
                    if (maximalNumbers.contains(bigInteger) || neither.contains(bigInteger)) break;
                }
            }
            currentVertex.color = "black";
        }
    }

    public static void printPath(Map<BigInteger, Vertex> vertexes, BigInteger v) {
        BigInteger s = new BigInteger("1");
        if (v.compareTo(s) == 0) {
            path = path.concat(s + " ");
        }
        else if (vertexes.get(v).previous == null) { }
        else {
            printPath(vertexes, vertexes.get(v).previous);
            path = path.concat(v + " ");
            if (!uniqueNumbers.contains(v)) {
                uniqueNumbers.add(v);
            }
            else {
                repeatedNumbers.add(v);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Scanner inputFile = new Scanner(new File("HobbitExamples/input.txt"));

            ArrayList<BigInteger> numbers = new ArrayList<>();
            ArrayList<BigInteger> minimalNumbers = new ArrayList<>();
            maximalNumbers = new ArrayList<>();
            neither= new ArrayList<>();

            while (inputFile.hasNextLine()) {
                numbers.add(new BigInteger(inputFile.nextLine()));
            }

            Collections.sort(numbers);

            int begin = 0;
            int end = numbers.size() - 1;
            for (int i = 0; i < numbers.size(); i++) {
                // check #'s before numbers.get(i)
                boolean minimal = checkMinimal(numbers, begin, i);
                // check #'s after numbers.get(i)
                boolean maximal = checkMaximal(numbers, end, i);
                if (minimal) minimalNumbers.add(numbers.get(i));
                if (maximal) maximalNumbers.add(numbers.get(i));
                if (!maximal && !minimal) neither.add(numbers.get(i));

            }

            Map<BigInteger, Vertex> vertexes = new HashMap<>();
            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            // add all minimum numbers as places to go from pad 1
            BigInteger start = new BigInteger("1");
            vertexes.put(start, new Vertex(minimalNumbers));
            Vertex startVertex = vertexes.get(start);
            startVertex.color = "gray";
            startVertex.discoveredTime = 0;
            startVertex.previous = null;


            // add neighbors for each minimal #
            for (BigInteger minimalNumber : minimalNumbers) {
                vertexes.put(minimalNumber, new Vertex());

                // add neighbors from max
                for (BigInteger maximalNumber : maximalNumbers) {
                    if (minimalNumber.gcd(maximalNumber).intValue() > 1 && maximalNumber.compareTo(minimalNumber) > 0) vertexes.get(minimalNumber).neighbors.add(maximalNumber);
                }

                // add neighbors from neither
                for (BigInteger neitherNumber : neither) {
                    if (minimalNumber.gcd(neitherNumber).intValue() > 1 && neitherNumber.compareTo(minimalNumber) > 0) vertexes.get(minimalNumber).neighbors.add(neitherNumber);
                }
            }

            // add neighbors for each neither #
            for (BigInteger neitherNumber : neither) {
                vertexes.put(neitherNumber, new Vertex());

                // add neighbors from max
                for (BigInteger maximalNumber : maximalNumbers) {
                    if (neitherNumber.gcd(maximalNumber).intValue() > 1 && maximalNumber.compareTo(neitherNumber) > 0) vertexes.get(neitherNumber).neighbors.add(maximalNumber);
                }

                // add other neither neighbors
                for (BigInteger neitherNum : neither) {
                    if (!neitherNumber.equals(neitherNum) && neitherNumber.gcd(neitherNum).intValue() > 1 && neitherNum.compareTo(neitherNumber) > 0) vertexes.get(neitherNumber).neighbors.add(neitherNum);
                }

            }

            // add maximal numbers as vertexes
            for (BigInteger maxNum : maximalNumbers) {
                vertexes.put(maxNum, new Vertex());
            }

            BFS(vertexes);

            for(BigInteger maximalPad : maximalNumbers) {
                printPath(vertexes, maximalPad);
                String[] arr = path.split(" ");
                if (arr.length > 1) {
                    outputFile.println(path);
                }
                path = "";
            }

            outputFile.close();

        }
        catch (IOException excpt) {
            System.out.println("File not found!");
        }
    }

    private static boolean checkMaximal(ArrayList<BigInteger> numbers, int end, int i) {
        for (int k = i + 1; k <= end; k++) {
            if (numbers.get(i).gcd(numbers.get(k)).intValue() > 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkMinimal(ArrayList<BigInteger> numbers, int begin, int i) {
        for (int j = begin; j < i; j++) {
            if (numbers.get(j).gcd(numbers.get(i)).intValue() > 1) {
                return false;
            }
        }
        return true;
    }
}
