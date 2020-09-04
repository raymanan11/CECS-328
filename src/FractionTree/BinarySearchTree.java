package FractionTree;

import java.util.ArrayList;
import java.math.BigInteger;

public class BinarySearchTree {

    private Node root;

    private BigInteger M;
    private BigInteger N;

    ArrayList<Fraction> fractions;
    ArrayList<Fraction> fractionsInTree;

    public BinarySearchTree() {
        fractions = new ArrayList<>();
        fractionsInTree = new ArrayList<>();
        root = null;
    }

    public Fraction populateAndFindFractions(int m, int n) {

        fractions.add(new Fraction(0, 1));
        fractions.add(new Fraction(1, 0));

        M = new BigInteger(String.valueOf(m));
        N = new BigInteger(String.valueOf(n));

        double L = 0;

        Fraction outputFraction = null;

        while (true) {
            for (int i = 0; i <= Math.pow(2, L) - 1; i++) {
                Fraction fraction = new Fraction(fractions.get(2 * i).getA() + fractions.get(2 * i + 1).getA(), fractions.get(2 * i).getB() + fractions.get(2 * i + 1).getB());
                Boolean satisfyEquation = fraction.calculateEquation(M, N);
                fractions.add(2 * i + 1, fraction);

                if (satisfyEquation) {
                    outputFraction = fraction;
                    break;
                }

            }
            L++;
            fractionsInTree.clear();

            if (outputFraction != null) {
                break;
            }

        }
        return outputFraction;
    }

    public Node getRoot() {
        return root;
    }
}
