package Hobbits;

import java.math.BigInteger;
import java.util.ArrayList;

public class Vertex {

    ArrayList<BigInteger> neighbors;
    private String color;
    private int discoveredTime;
    private BigInteger previous;

    public Vertex() {
        neighbors = new ArrayList<>();
        color = "white";
        discoveredTime = -1;
        previous = null;
    }

    public Vertex(ArrayList<BigInteger> neighbors) {
        this.neighbors = neighbors;
        color = "white";
        discoveredTime = -1;
        previous = null;
    }

    @Override
    public String toString() {
        return neighbors.toString();
    }
}
