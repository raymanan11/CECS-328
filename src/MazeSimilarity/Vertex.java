package MazeSimilarity;

import java.util.ArrayList;

public class Vertex {

    ArrayList<Integer> edges;
    String color;
    Vertex previous;

    public Vertex() {
        edges = new ArrayList<>();
        color = "white";
        previous = null;
    }

    @Override
    public String toString() {
        return edges.toString();
    }
}
