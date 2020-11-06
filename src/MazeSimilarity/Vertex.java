package MazeSimilarity;

import java.util.ArrayList;

public class Vertex {

    ArrayList<Integer> edges;
    String color;
    int discoveredTime;
    int finishedTime;
    Vertex previous;

    public Vertex() {
        edges = new ArrayList<>();
        color = "white";
        discoveredTime = 0;
        finishedTime = 0;
        previous = null;
    }

    @Override
    public String toString() {
        return edges.toString();
    }
}
