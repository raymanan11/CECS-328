package MazeSimilarity;
import java.io.File;
import java.io.IOException;
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
            String maze = "";

            while (i < numMazes) {
                String s = inputFile.nextLine();

                if (!s.equals("")) {
                    maze = maze.concat("\n" + s);
                }
                else {
                    System.out.println(maze);
                    // do something with the maze
                    maze = "";
                    i++;
                }
            }
        }
        catch  (IOException excpt) {
            System.out.println("File not found!");
        }

    }
}
