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
    private static int tempEdge = 0;

    private static void DFS(Map<Integer, Vertex> vertexes) {
        time = 0;
        for (Map.Entry<Integer, Vertex> vertex : vertexes.entrySet()) {
            Vertex currentVertex = vertex.getValue();
            if (currentVertex.color.equals(white)) {
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
                tempEdge = edge;
                String direction = getStringDirection(currentVertexNumber, edge);
                System.out.print(direction + " ");
                vertexes.get(edge).previous = currentVertexNumber;
                DFSVisit(vertexes, vertexes.get(edge), edge);
            }
        }
        String d = getStringDirection(tempEdge, u.previous);
        if (u.previous != 0) {
            System.out.print(d + " ");
        }
        tempEdge = u.previous;
        u.color = black;
        time = time + 1;
        u.finishedTime = time;
    }

    private static String getStringDirection(int currentVertexNumber, int edge) {
        String direction;
        if (Math.abs(edge - currentVertexNumber) > 1) {
            direction = (edge - currentVertexNumber == -4) ? "N" : "S";
        } else {
            direction = (edge - currentVertexNumber == -1) ? "W" : "E";
        }
        return direction;
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
//            String d = (direction == 0) ? "North": "South";
//            System.out.println(d);
            neighborVertex = (direction == 0) ? vertexNumber - n: vertexNumber + n;
        }
        else {
//            String d = (direction == 2) ? "West": "East";
//            System.out.println(d);
            neighborVertex = (direction == west) ? vertexNumber - 1: vertexNumber + 1;
        }
        return neighborVertex;
    }
}
