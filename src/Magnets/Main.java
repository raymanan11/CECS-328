// Raymond An

package Magnets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner inputFile = new Scanner(new File("MagnetExamples/input6.txt"));

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


            // set magnetic pole to be opposite of what magnetic pole it is (+ or -)
            for (int i = 0; i < firstLine.size(); i++) {
                if (firstLine.get(i).equals(positive)) {
                    secondLine.set(i, negative);
                    outputFile.println("0 " + i + " 1 " + i);
                    numMagnets -= 1;
                }
                if (firstLine.get(i).equals(negative)) {
                    secondLine.set(i, positive);
                    outputFile.println("1 " + i + " 0 " + i);
                    numMagnets -= 1;
                }
                if (secondLine.get(i).equals(positive) && firstLine.get(i).equals(star)) {
                    firstLine.set(i, negative);
                    outputFile.println("1 " + i + " 0 " + i);
                    numMagnets -= 1;
                }
                if (secondLine.get(i).equals(negative) && firstLine.get(i).equals(star)) {
                    firstLine.set(i, positive);
                    outputFile.println("0 " + i + " 1 " + i);
                    numMagnets -= 1;
                }
            }

            for (int i = 0; i < firstLine.size(); i++) {
                if (firstLine.get(i).equals(star)) {
                    locationOfAdjacentStars.add(i);
                }
            }

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
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (firstLine.get(rightNeighbor).equals(negative)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        if (secondLine.get(rightNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (secondLine.get(rightNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
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
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (firstLine.get(leftNeighbor).equals(negative)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        if (secondLine.get(leftNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                        else if (secondLine.get(leftNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                            numMagnets -= 1;
                        }
                    }
                    else {
                        int leftNeighbor = locationOfAdjacentStars.get(i) - 1;
                        int rightNeighbor = locationOfAdjacentStars.get(i) + 1;
                        int current = locationOfAdjacentStars.get(i);
                        if (firstLine.get(leftNeighbor).equals(positive) && firstLine.get(rightNeighbor).equals(negative) ||
                                firstLine.get(leftNeighbor).equals(negative) && firstLine.get(rightNeighbor).equals(positive) ||
                                secondLine.get(leftNeighbor).equals(positive) && secondLine.get(rightNeighbor).equals(negative) ||
                                secondLine.get(leftNeighbor).equals(negative) && secondLine.get(rightNeighbor).equals(positive)) {
                            continue;
                        }
                        else if (firstLine.get(leftNeighbor).equals(positive) ||
                                firstLine.get(rightNeighbor).equals(positive)) {
                            firstLine.set(current, negative);
                            secondLine.set(current, positive);
                            numMagnets -= 1;
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                        }
                        else if (firstLine.get(leftNeighbor).equals(negative) ||
                                firstLine.get(rightNeighbor).equals(negative)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            numMagnets -= 1;
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                        }
                        if ((secondLine.get(leftNeighbor).equals(positive) ||
                                secondLine.get(rightNeighbor).equals(positive)) && firstLine.get(current).equals(star)) {
                            firstLine.set(current, positive);
                            secondLine.set(current, negative);
                            numMagnets -= 1;
                            outputFile.println("0 " + current + " 1 " + current);
                            indexesToBeRemoved.add(i);
                        }
                        else if ((secondLine.get(leftNeighbor).equals(negative) ||
                                secondLine.get(rightNeighbor).equals(negative)) && firstLine.get(current).equals(star)) {
                            secondLine.set(current, positive);
                            firstLine.set(current, negative);
                            numMagnets -= 1;
                            outputFile.println("1 " + current + " 0 " + current);
                            indexesToBeRemoved.add(i);
                        }
                    }
                }
                else {
                    break;
                }
            }
            // remove indexes of stars that have already been placed in previous for loop so that the remaining
            // stars can be updated and can be checked again with updated star locations of we still need to place
            // more magnets
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

            // now going opposite direction to fill in any missing magnets
            if (numMagnets > 0) {
                for (int j = 0; j < remainingLocationOfAdjacentStars.size(); j++) {
                    // beginning of array
                    if (numMagnets > 0) {
                        int i = remainingLocationOfAdjacentStars.size() - 1 - j;
                        if (remainingLocationOfAdjacentStars.get(i) == 0) {
                            int rightNeighbor = remainingLocationOfAdjacentStars.get(i) + 1;
                            int current = remainingLocationOfAdjacentStars.get(i);
                            if (firstLine.get(rightNeighbor).equals(positive)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                outputFile.println("1 " + current + " 0 " + current);
                                numMagnets -= 1;
                            }
                            else if (firstLine.get(rightNeighbor).equals(negative)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            if (secondLine.get(rightNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            else if (secondLine.get(rightNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
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
                                outputFile.println("1 " + current + " 0 " + current);
                                numMagnets -= 1;
                            }
                            else if (firstLine.get(leftNeighbor).equals(negative)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            if (secondLine.get(leftNeighbor).equals(positive) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                outputFile.println("0 " + current + " 1 " + current);
                                numMagnets -= 1;
                            }
                            else if (secondLine.get(leftNeighbor).equals(negative) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                outputFile.println("1 " + current + " 0 " + current);
                                numMagnets -= 1;
                            }
                        }
                        else {
                            int leftNeighbor = remainingLocationOfAdjacentStars.get(i) - 1;
                            int rightNeighbor = remainingLocationOfAdjacentStars.get(i) + 1;
                            int current = remainingLocationOfAdjacentStars.get(i);
                            if (firstLine.get(leftNeighbor).equals(positive) && firstLine.get(rightNeighbor).equals(negative) ||
                                    firstLine.get(leftNeighbor).equals(negative) && firstLine.get(rightNeighbor).equals(positive) ||
                                    secondLine.get(leftNeighbor).equals(positive) && secondLine.get(rightNeighbor).equals(negative) ||
                                    secondLine.get(leftNeighbor).equals(negative) && secondLine.get(rightNeighbor).equals(positive)) {
                                continue;
                            }
                            else if (firstLine.get(leftNeighbor).equals(positive) ||
                                    firstLine.get(rightNeighbor).equals(positive)) {
                                firstLine.set(current, negative);
                                secondLine.set(current, positive);
                                numMagnets -= 1;
                                outputFile.println("1 " + current + " 0 " + current);
                            } else if (firstLine.get(leftNeighbor).equals(negative) ||
                                    firstLine.get(rightNeighbor).equals(negative)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                numMagnets -= 1;
                                outputFile.println("0 " + current + " 1 " + current);
                            }
                            if ((secondLine.get(leftNeighbor).equals(positive) ||
                                    secondLine.get(rightNeighbor).equals(positive)) && firstLine.get(current).equals(star)) {
                                firstLine.set(current, positive);
                                secondLine.set(current, negative);
                                numMagnets -= 1;
                            } else if ((secondLine.get(leftNeighbor).equals(negative) ||
                                    secondLine.get(rightNeighbor).equals(negative)) && firstLine.get(current).equals(star)) {
                                secondLine.set(current, positive);
                                firstLine.set(current, negative);
                                numMagnets -= 1;
                                outputFile.println("1 " + current + " 0 " + current);
                            }
                        }
                    }
                    else {
                        break;
                    }
                }
            }

            outputFile.close();

        }
        catch(IOException excpt) {
            System.out.println("File not found!");
        }
    }
}
