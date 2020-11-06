package MazeSimilarity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
            Scanner inputFile = new Scanner(new File("MazeSamples/input.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            int numMazes = inputFile.nextInt();

            int n = inputFile.nextInt();
            inputFile.nextLine();

            System.out.println("Number of mazes: " + numMazes);
            System.out.println("n: " + n);
            int i = 0;
            int j = 0;
            String maze = "";

            ArrayList<String> mazes = new ArrayList<>();

            while (i < numMazes) {
                String s = inputFile.nextLine();

                if (!s.equals("")) {
                    maze = maze.concat(s);
                    // split the string every four places by using for loop
                    // j++
                }
                else {
                    System.out.println(maze);
                    mazes.add(maze);
                    maze = "";
                    i++;
                    //j = 0;
                }
            }

            Map<Integer, Vertex> vertexes = new HashMap<>();

            for (int numChar = 0; numChar < mazes.get(0).length(); numChar++) {
                int vertexNumber = numChar / 4 + 1;
                int direction = numChar % 4;
                int neighborVertex;
                if (mazes.get(0).charAt(numChar) == '0') {
                    if (direction == 0 || direction == 1) {
                        neighborVertex = (direction == 0) ? vertexNumber - n: vertexNumber + n;
                    }
                    else {
                        neighborVertex = (direction == 2) ? vertexNumber - 1: vertexNumber + 1;
                    }
                    if (!vertexes.containsKey(vertexNumber)) {
                        System.out.print(numChar + " ");
                        vertexes.put(vertexNumber, new Vertex());
                    }
                    vertexes.get(vertexNumber).edges.add(neighborVertex);
                }
            }

            System.out.println();
            System.out.println(vertexes);

        }
        catch  (IOException excpt) {
            System.out.println("File not found!");
        }

    }
}
