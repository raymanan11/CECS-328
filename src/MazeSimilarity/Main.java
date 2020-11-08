package MazeSimilarity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Main {

    private static int n;
    private static int time;
    private static String white = "white";
    private static int tempEdge = 0;
    private static ArrayList<String> mazePaths = new ArrayList<>();
    private static String path = "";

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
        String gray = "gray";
        String black = "black";
        time += 1;
        u.discoveredTime = time;
        u.color = gray;
        for (int edge : u.edges) {
            if (vertexes.get(edge).color.equals(white)) {
                tempEdge = edge;
                // System.out.println(edge + " - " + currentVertexNumber);
                String direction = getStringDirection(currentVertexNumber, edge);
//                System.out.print(direction);
                path = path.concat(direction);
                // System.out.println();
                vertexes.get(edge).previous = currentVertexNumber;
                DFSVisit(vertexes, vertexes.get(edge), edge);
            }
        }
        // System.out.println(u.previous + " - " + tempEdge + " s");
        String d = getStringDirection(tempEdge, u.previous);
        if (u.previous != 0) {
//            System.out.print(d);
            path = path.concat(d);
        }
        // System.out.println();
        tempEdge = u.previous;
        u.color = black;
        time = time + 1;
        u.finishedTime = time;
    }

    private static String getStringDirection(int currentVertexNumber, int edge) {
        String direction;
        if (Math.abs(edge - currentVertexNumber) > 1) {
            direction = (edge - currentVertexNumber == (n * -1)) ? "N" : "S";
        } else {
            direction = (edge - currentVertexNumber == -1) ? "W" : "E";
        }
        return direction;
    }

    public static void main(String[] args) {

        try {
            Scanner inputFile = new Scanner(new File("MazeSamples/inputTest.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            int numMazes = inputFile.nextInt();

            n = inputFile.nextInt();
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

            Map<Integer, Vertex> vertexes;
            for (int j = 0; j < mazes.size(); j++) {
                vertexes = new HashMap<>();
                for (int numChar = 0; numChar < mazes.get(j).length(); numChar++) {
                    int vertexNumber = numChar / 4 + 1;
                    int direction = numChar % 4;
                    int neighborVertex;
                    if (mazes.get(j).charAt(numChar) == '0') {
                        neighborVertex = getNeighborVertex(n, vertexNumber, direction);
                        if (!vertexes.containsKey(vertexNumber)) vertexes.put(vertexNumber, new Vertex());
                        vertexes.get(vertexNumber).edges.add(neighborVertex);
                    }
                }

//            System.out.println(vertexes);
                DFS(vertexes);
                mazePaths.add(path);
                path = "";
            }

            System.out.println(mazePaths);

        }
        catch (IOException excpt) {
            System.out.println("File not found!");
        }

    }

    private static int getNeighborVertex(int n, int vertexNumber, int direction) {
        int north = 0;
        int south = 1;
        int west = 2;
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
