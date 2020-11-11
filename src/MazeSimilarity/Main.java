// Raymond An

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

    private static int[][] c;
    private static Map<Integer, String> mazeCombos = new HashMap<>();
    private static int count = 0;
    private static String x = "";
    private static String y = "";
    private static int i = 0;
    private static int j = 0;

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
        c = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) c[i][0] = 0;
        for (int j = 0; j <= n; j++) c[0][j] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                }
                else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i - 1][j];
                }
                else {
                    c[i][j] = c[i][j - 1];
                }
            }
        }

    }

    private static void printLCS(int[][] c) {
        while (i != 0 && j != 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                n -=1;
                i -= 1;
                j -= 1;
                count++;
            }
            else if (c[i - 1][j] >= c[i][j - 1]) i -= 1;
            else j -= 1;
        }
    }

    public static void main(String[] args) {

        try {
            Scanner inputFile = new Scanner(new File("MazeSamples/input.txt"));

            PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

            int numMazes = inputFile.nextInt();

            n = inputFile.nextInt();
            inputFile.nextLine();

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
            // goes through each maze
            for (int j = 0; j < mazes.size(); j++) {
                // initialize new HashMap
                vertexes = new HashMap<>();
                // goes through all characters of string maze
                for (int numChar = 0; numChar < mazes.get(j).length(); numChar++) {
                    // determine which vertex that character is
                    int vertexNumber = numChar / 4 + 1;
                    int neighborVertex;
                    if (mazes.get(j).charAt(numChar) == '0') {
                        // determines the direction (0 = north, 1 = south, 2 = west, 3 = east
                        int direction = numChar % 4;
                        // get the integer neighbor
                        neighborVertex = getNeighborVertex(n, vertexNumber, direction);
                        // if vertex is not in map, then add it to map and initialize new vertex
                        if (!vertexes.containsKey(vertexNumber)) vertexes.put(vertexNumber, new Vertex());
                        // add the neighbor to the vertex's ArrayList of edges
                        vertexes.get(vertexNumber).edges.add(neighborVertex);
                    }
                }

                // depth first search those vertexes
                DFS(vertexes);
                mazePaths.add(path);
                path = "";
            }

            // go through every maze combo and get the length of LCS between the two mazes
            int j = 0;
            int k = j + 1;
            while (j < numMazes - 1) {
                if (k < numMazes) {
                    x = mazePaths.get(j);
                    y = mazePaths.get(k);
                    Main.i = x.length();
                    Main.j = y.length();
                    LCSLength(x, y);
                    printLCS(c);
                    // add to map the length of LCS and the corresponding mazes
                    if (!mazeCombos.containsKey(count)) mazeCombos.put(count, j + " " + k);
                    count = 0;
                    k++;
                }
                else {
                    j++;
                    k = j + 1;
                }
            }

            int leastCommon = Integer.MAX_VALUE;
            // search for the smallest number in the map = maze combo that is least similar
            for (Map.Entry<Integer, String> mazeCombo : mazeCombos.entrySet()) {
                if (mazeCombo.getKey() < leastCommon) {
                    leastCommon = mazeCombo.getKey();
                }
            }

            outputFile.println(mazeCombos.get(leastCommon));
            outputFile.close();

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
        // north or south
        if (direction == north || direction == south) {
            // subtract / add vertex # by n to get north / south
            neighborVertex = (direction == north) ? vertexNumber - n: vertexNumber + n;
        }
        else {
            // subtract / add vertex # by 1 to get north / south
            neighborVertex = (direction == west) ? vertexNumber - 1: vertexNumber + 1;
        }
        return neighborVertex;
    }
}
