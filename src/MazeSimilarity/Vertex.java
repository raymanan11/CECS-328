package MazeSimilarity;

import java.util.ArrayList;

public class Vertex {

    ArrayList<Integer> edges;
    String color;
    int discoveredTime;
    int finishedTime;
    int previous;

    public Vertex() {
        edges = new ArrayList<>();
        color = "white";
        discoveredTime = 0;
        finishedTime = 0;
        previous = 0;
    }

    @Override
    public String toString() {
        return edges.toString();
    }
}
