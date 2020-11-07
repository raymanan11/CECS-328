package MazeSimilarity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Main {

    private static int time;
    private static String white = "white";
    private static String gray = "gray";
    private static String black = "black";
    private static int north = 0;
    private static int south = 1;
    private static int west = 2;

    private static void DFS(Map<Integer, Vertex> vertexes) {
        time = 0;
        for (Map.Entry<Integer, Vertex> vertex : vertexes.entrySet()) {
            Vertex currentVertex = vertex.getValue();
            if (currentVertex.color.equals(white)) {
                System.out.println(vertex.getKey());
                DFSVisit(vertexes, currentVertex, vertex.getKey());
            }
        }
    }

    private static void DFSVisit(Map<Integer, Vertex> vertexes, Vertex u, int currentVertexNumber) {
        time += 1;
        u.discoveredTime = time;
        u.color = gray;
        for (int edge : u.edges) {
            if (vertexes.get(edge).color.equals(white)) {
                System.out.println(edge);
                vertexes.get(edge).previous = currentVertexNumber;
                DFSVisit(vertexes, vertexes.get(edge), edge);
            }
        }
        System.out.println(u.previous);
        u.color = black;
        time = time + 1;
        u.finishedTime = time;
    }

    public static void main(String[] args) {

        try {
            Scanner inputFile = new Scanner(new File("MazeSamples/input2.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            int numMazes = inputFile.nextInt();

            int n = inputFile.nextInt();
            inputFile.nextLine();

            System.out.println("Number of mazes: " + numMazes);
            System.out.println("n: " + n);
            int i = 0;
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
                    neighborVertex = getNeighborVertex(n, vertexNumber, direction);
                    if (!vertexes.containsKey(vertexNumber)) vertexes.put(vertexNumber, new Vertex());
                    vertexes.get(vertexNumber).edges.add(neighborVertex);
                }
            }

            System.out.println(vertexes);
            DFS(vertexes);

//            for (Map.Entry<Integer, Vertex> vertex : vertexes.entrySet()) {
//                System.out.println(vertex.getKey());
//                System.out.println(vertex.getValue().discoveredTime);
//                System.out.println(vertex.getValue().finishedTime);
//                System.out.println("Parent: " + vertex.getValue().previous);
//                System.out.println();
//            }

        }
        catch (IOException excpt) {
            System.out.println("File not found!");
        }

    }

    private static int getNeighborVertex(int n, int vertexNumber, int direction) {
        int neighborVertex;
        if (direction == north || direction == south) {
            neighborVertex = (direction == 0) ? vertexNumber - n: vertexNumber + n;
        }
        else {
            neighborVertex = (direction == west) ? vertexNumber - 1: vertexNumber + 1;
        }
        return neighborVertex;
    }
}
