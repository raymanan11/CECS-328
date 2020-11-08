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

    private static String[][] b;
    private static int[][] c;
    private static String upAndLeft = "up&left";
    private static String up = "up";
    private static String left = "left";

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
                String direction = getStringDirection(currentVertexNumber, edge);
                path = path.concat(direction);
                vertexes.get(edge).previous = currentVertexNumber;
                DFSVisit(vertexes, vertexes.get(edge), edge);
            }
        }
        String d = getStringDirection(tempEdge, u.previous);
        if (u.previous != 0) {
            path = path.concat(d);
        }
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

    private static void LCSLength(String x, String y) {
        int m = x.length();
        int n = y.length();

        // only use 1-5 (5 count)
        b = new String[m + 1][n + 1];
        c = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) c[i][0] = 0;
        for (int j = 0; j <= n; j++) c[0][j] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = upAndLeft;
                }
                else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = up;
                }
                else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = left;
                }
            }
        }
    }

    private static void printLCS(String[][] b, String x, int i, int j) {
        if (i == 0 || j == 0) return;
        if (b[i][j].equals(upAndLeft)) {
            printLCS(b, x, i - 1, j - 1);
            System.out.print(x.charAt(i - 1));
        }
        else if (b[i][j].equals(up)) {
            printLCS(b, x, i - 1, j);
        }
        else printLCS(b, x, i, j-1);
    }

    public static void main(String[] args) {

        try {
            Scanner inputFile = new Scanner(new File("MazeSamples/input.txt"));

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

                DFS(vertexes);
                mazePaths.add(path);
                path = "";
            }

            System.out.println(mazePaths);
            LCSLength(mazePaths.get(0), mazePaths.get(1));
            printLCS(b, mazePaths.get(0), mazePaths.get(0).length(), mazePaths.get(1).length());

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
