// Raymond An

package Magnets;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner inputFile = new Scanner(new File("MagnetExamples/input5.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            int numMagnets = inputFile.nextInt();
            String positive = "+";
            String negative = "-";
            String star = "*";

            inputFile.nextLine();

            String first = inputFile.nextLine();
            String second = inputFile.nextLine();

            String[] arr = first.split("");
            String[] arr2 = second.split("");

            ArrayList<String> firstLine = new ArrayList<>(Arrays.asList(arr));
            ArrayList<String> secondLine  = new ArrayList<>(Arrays.asList(arr2));
            ArrayList<Integer> locationOfAdjacentStars = new ArrayList<>();


            System.out.println("Original arrays");
            System.out.println("Number of magnets to place: " + numMagnets);
            for (String pole : firstLine) {
                System.out.print(pole + " ");
            }
            System.out.println();
            for (String pole : secondLine) {
                System.out.print(pole + " ");
            }
            System.out.println();
            System.out.println();

            // set magnetic pole to be opposite of what magnetic pole it is (+ or -)
            for (int i = 0; i < firstLine.size(); i++) {
                if (firstLine.get(i).equals(positive)) {
                    secondLine.set(i, negative);
                    System.out.println("0 " + i + " 1 " + i);
                    outputFile.println("0 " + i + " 1 " + i);
                    numMagnets -= 1;
                }
                if (firstLine.get(i).equals(negative)) {
                    secondLine.set(i, positive);
                    System.out.println("1 " + i + " 0 " + i);
                    outputFile.println("1 " + i + " 0 " + i);
                    numMagnets -= 1;
                }
                if (secondLine.get(i).equals(positive) && firstLine.get(i).equals(star)) {
                    firstLine.set(i, negative);
                    System.out.println("1 " + i + " 0 " + i);
                    outputFile.println("1 " + i + " 0 " + i);
                    numMagnets -= 1;
                }
                if (secondLine.get(i).equals(negative) && firstLine.get(i).equals(star)) {
                    firstLine.set(i, positive);
                    System.out.println("0 " + i + " 1 " + i);
                    outputFile.println("0 " + i + " 1 " + i);
                    numMagnets -= 1;
                }
            }

            System.out.println();
            System.out.println("Number of magnets left to place: " + numMagnets);
            System.out.println("Arrays after setting opposite pole of magnet if there is already a positive or negative pole");
            System.out.print("  ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.print("0 ");
            for (String pole : firstLine) {
                System.out.print(pole + " ");
            }
            System.out.println();
            System.out.print("1 ");
            for (String pole : secondLine) {
                System.out.print(pole + " ");
            }
            System.out.println();

            for (int i = 0; i < firstLine.size(); i++) {
                if (firstLine.get(i).equals(star)) {
                    locationOfAdjacentStars.add(i);
                }
            }

            System.out.println(locationOfAdjacentStars);

            ArrayList<Integer> indexesToBeRemoved = new ArrayList<>();

            // make an array of indexes that will be removed and at the end of this loop, create a loop that will remove those indexes from locationOfAdjacentStars
            for (int i = 0; i < locationOfAdjacentStars.size(); i++) {
                // beginning of array
                if (numMagnets > 0) {
                    // beginning of array
                    if (locationOfAdjacentStars.get(i) == 0) {
                        int rightNeighbor = locationOfAdjacentStars.get(i) + 1;
                        int current = locationOfAdjacentStars.get(i);
                        if (firstLine.get(rightNeighbor).equals(positive)) {
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
                            System.out.println("1 " + current + " 0 " + current);
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (firstLine.get(rightNeighbor).equals(negative)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            System.out.println("0 " + current + " 1 " + current);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        if (secondLine.get(rightNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            System.out.println("0 " + current + " 1 " + current);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (secondLine.get(rightNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
                            System.out.println("1 " + current + " 0 " + current);
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                    }
                    // end of array
                    else if (locationOfAdjacentStars.get(i) == firstLine.size() - 1) {
                        int leftNeighbor = locationOfAdjacentStars.get(i) - 1;
                        int current = locationOfAdjacentStars.get(i);
                        if (firstLine.get(leftNeighbor).equals(positive)) {
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
                            System.out.println("1 " + current + " 0 " + current);
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (firstLine.get(leftNeighbor).equals(negative)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            System.out.println("0 " + current + " 1 " + current);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        if (secondLine.get(leftNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            System.out.println("0 " + current + " 1 " + current);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (secondLine.get(leftNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
                            System.out.println("1 " + current + " 0 " + current);
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                    }
                    else {
                        int leftNeighbor = locationOfAdjacentStars.get(i) - 1;
                        int rightNeighbor = locationOfAdjacentStars.get(i) + 1;
                        int current = locationOfAdjacentStars.get(i);
                        if (firstLine.get(leftNeighbor).equals(positive) ||
                                firstLine.get(rightNeighbor).equals(positive)) {
                            System.out.println("a2");
                            System.out.println(firstLine.get(leftNeighbor) + " " + firstLine.get(current) + " " + firstLine.get(rightNeighbor));
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
                            numMagnets -= 1;
                            System.out.println("1 " + current + " 0 " + current);
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                        }
                        else if (firstLine.get(leftNeighbor).equals(negative) ||
                                firstLine.get(rightNeighbor).equals(negative)) {
                            System.out.println("b2");
                            System.out.println(firstLine.get(leftNeighbor) + " " + firstLine.get(current) + " " + firstLine.get(rightNeighbor));
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            numMagnets -= 1;
                            System.out.println("0 " + current + " 1 " + current);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                        }
                        if ((secondLine.get(leftNeighbor).equals(positive) ||
                                secondLine.get(rightNeighbor).equals(positive)) && firstLine.get(current).equals(star)) {
                            System.out.println("c2");
                            System.out.println(secondLine.get(leftNeighbor) + " " + secondLine.get(current) + " " + secondLine.get(rightNeighbor));
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            numMagnets -= 1;
                            System.out.println("0 " + current + " 1 " + current);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                        }
                        else if ((secondLine.get(leftNeighbor).equals(negative) ||
                                secondLine.get(rightNeighbor).equals(negative)) && firstLine.get(current).equals(star)) {
                            System.out.println("d2");
                            System.out.println(secondLine.get(leftNeighbor) + " " + secondLine.get(current) + " " + secondLine.get(rightNeighbor));
                            secondLine.set(locationOfAdjacentStars.get(i), positive);
                            firstLine.set(locationOfAdjacentStars.get(i), negative);
                            numMagnets -= 1;
                            System.out.println("1 " + current + " 0 " + current);
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                        }
                    }
                }
                else {
                    break;
                }
            }

            // these are the indexes that need to be removed from locationOfAdjacentStars
            System.out.println("Indexes to be removed: " + indexesToBeRemoved);
            System.out.println("Location of adjacent stars:" + locationOfAdjacentStars);
            System.out.println();
            // method to remove those indexes from locationOfAdjacentStars
            ArrayList<Integer> remainingLocationOfAdjacentStars = new ArrayList<>();
            for (int i = 0; i < locationOfAdjacentStars.size(); i++) {
                boolean inArray = false;
                for (int j = 0; j < indexesToBeRemoved.size(); j++) {
                    if (i == indexesToBeRemoved.get(j)) {
                        inArray = true;
                    }
                }
                if (!inArray) {
                    remainingLocationOfAdjacentStars.add(i);
                }
            }

            System.out.println(remainingLocationOfAdjacentStars);
            System.out.println();

            System.out.println(numMagnets);

            // now going opposite direction to fill in any missing magnets
            if (numMagnets > 0) {
                for (int j = 0; j < remainingLocationOfAdjacentStars.size(); j++) {
                    System.out.println("hi");
                    // beginning of array
                    if (numMagnets > 0) {
                        int i = remainingLocationOfAdjacentStars.size() - 1 - j;
                        if (remainingLocationOfAdjacentStars.get(i) == 0) {
                            int rightNeighbor = remainingLocationOfAdjacentStars.get(i) + 1;
                            int current = remainingLocationOfAdjacentStars.get(i);
                            if (firstLine.get(rightNeighbor).equals(positive)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                System.out.println("a");
                                System.out.println("1 " + current + " 0 " + current);
                                outputFile.println("1 " + current + " 0 " + current);
                                numMagnets -= 1;
                            }
                            else if (firstLine.get(rightNeighbor).equals(negative)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                System.out.println("b");
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            if (secondLine.get(rightNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                System.out.println("c");
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            else if (secondLine.get(rightNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                System.out.println("d");
                                System.out.println("1 " + current + " 0 " + current);
                                outputFile.println("1 " + current + " 0 " + current);
                                numMagnets -= 1;
                            }
                        }
                        // end of array
                        else if (remainingLocationOfAdjacentStars.get(i) == firstLine.size() - 1) {
                            int leftNeighbor = remainingLocationOfAdjacentStars.get(i) - 1;
                            int current = remainingLocationOfAdjacentStars.get(i);
                            if (firstLine.get(leftNeighbor).equals(positive)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                System.out.println("e");
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            else if (firstLine.get(leftNeighbor).equals(negative)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                System.out.println("f");
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            if (secondLine.get(leftNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                System.out.println("g");
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            else if (secondLine.get(leftNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                System.out.println("h");
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                        }
                        else {
                            int leftNeighbor = remainingLocationOfAdjacentStars.get(i) - 1;
                            int rightNeighbor = remainingLocationOfAdjacentStars.get(i) + 1;
                            int current = remainingLocationOfAdjacentStars.get(i);
                            if (firstLine.get(leftNeighbor).equals(positive) ||
                                    firstLine.get(rightNeighbor).equals(positive)) {
                                System.out.println("i");
                                System.out.println(firstLine.get(leftNeighbor) + " " + firstLine.get(current) + " " + firstLine.get(rightNeighbor));
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                numMagnets -= 1;
                                System.out.println("1 " + current + " 0 " + current);
                                outputFile.println("1 " + current + " 0 " + current);
                            } else if (firstLine.get(leftNeighbor).equals(negative) ||
                                    firstLine.get(rightNeighbor).equals(negative)) {
                                System.out.println("j");
                                System.out.println(firstLine.get(leftNeighbor) + " " + firstLine.get(current) + " " + firstLine.get(rightNeighbor));
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                numMagnets -= 1;
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                            }
                            if ((secondLine.get(leftNeighbor).equals(positive) ||
                                    secondLine.get(rightNeighbor).equals(positive)) && firstLine.get(current).equals(star)) {
                                System.out.println("k");
                                System.out.println(secondLine.get(leftNeighbor) + " " + secondLine.get(current) + " " + secondLine.get(rightNeighbor));
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                numMagnets -= 1;
                                System.out.println("0 " + current + " 1 " + current);
                                outputFile.println("0 " + current + " 1 " + current);
                            } else if ((secondLine.get(leftNeighbor).equals(negative) ||
                                    secondLine.get(rightNeighbor).equals(negative)) && firstLine.get(current).equals(star)) {
                                System.out.println("l");
                                System.out.println(secondLine.get(leftNeighbor) + " " + secondLine.get(current) + " " + secondLine.get(rightNeighbor));
                                secondLine.set(remainingLocationOfAdjacentStars.get(i), positive);
                                firstLine.set(remainingLocationOfAdjacentStars.get(i), negative);
                                numMagnets -= 1;
                                System.out.println("1 " + current + " 0 " + current);
                                outputFile.println("1 " + current + " 0 " + current);
                            }
                        }
                    }
                    else {
                        break;
                    }
                }
            }

            System.out.println("Final arrays");
            System.out.println("Number of magnets left to place: " + numMagnets);
            System.out.print("  ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.print("0 ");
            for (String pole : firstLine) {
                System.out.print(pole + " ");
            }
            System.out.println();
            System.out.print("1 ");
            for (String pole : secondLine) {
                System.out.print(pole + " ");
            }
            System.out.println();

            outputFile.close();

        }
        catch(IOException excpt) {
            System.out.println("File not found!");
        }
    }
}
