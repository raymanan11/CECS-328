package MazeSimilarity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Main {

    static int time;
    static String white = "white";
    static String gray = "gray";
    static String black = "black";

    static void DFS(Map<Integer, Vertex> vertexes) {
        time = 0;
        for (Map.Entry<Integer, Vertex> vertex : vertexes.entrySet()) {
            Vertex currentVertex = vertex.getValue();
            if (currentVertex.color.equals(white)) {
                DFSVisit(vertexes, currentVertex);
            }
        }
    }

    static void DFSVisit(Map<Integer, Vertex> vertexes, Vertex u) {
        time += 1;
        u.discoveredTime = time;
        u.color = gray;
        for (int edge : u.edges) {
            if (vertexes.get(edge).color.equals(white)) {
                vertexes.get(edge).previous = u;
                DFSVisit(vertexes, vertexes.get(edge));
            }
        }
        u.color = black;
        time = time + 1;
        u.finishedTime = time;
    }

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
            int north = 0;
            int south = 1;
            int west = 2;
            String maze = "";

            ArrayList<String> mazes = new ArrayList<>();

            // read all the mazes and add to ArrayList
            while (i < numMazes) {
                String s = inputFile.nextLine();

                if (!s.equals("")) {
                    maze = maze.concat(s);
                }
                else {
                    mazes.add(maze);
                    maze = "";
                    i++;
                }
            }

            Map<Integer, Vertex> vertexes = new HashMap<>();

            for (int numChar = 0; numChar < mazes.get(0).length(); numChar++) {
                int vertexNumber = numChar / 4 + 1;
                int direction = numChar % 4;
                int neighborVertex;
                if (mazes.get(0).charAt(numChar) == '0') {
                    if (direction == north || direction == south) {
                        neighborVertex = (direction == 0) ? vertexNumber - n: vertexNumber + n;
                    }
                    else {
                        neighborVertex = (direction == west) ? vertexNumber - 1: vertexNumber + 1;
                    }
                    if (!vertexes.containsKey(vertexNumber)) vertexes.put(vertexNumber, new Vertex());
                    vertexes.get(vertexNumber).edges.add(neighborVertex);
                }
            }

            DFS(vertexes);

        }
        catch  (IOException excpt) {
            System.out.println("File not found!");
        }

    }
}
