package FractionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger;

public class BinarySearchTree {

    private Map<Fraction, Node> bst;
    private Node root;

    private BigInteger M;
    private BigInteger N;

    ArrayList<Fraction> fractions;
    ArrayList<Fraction> fractionsInTree;

    public BinarySearchTree() {
        fractions = new ArrayList<>();
        fractionsInTree = new ArrayList<>();
        bst = new HashMap<>();
        root = null;
    }

    public Fraction populateAndFindFractions(int m, int n) {

        fractions.add(new Fraction(0, 1));
        fractions.add(new Fraction(1, 0));

        double L = 0;

        Fraction outputFraction = null;

        while (fractionsInTree.size() < 10) {
            for (int i = 0; i <= Math.pow(2, L) - 1; i++) {
                Fraction fraction = new Fraction(fractions.get(2 * i).getA() + fractions.get(2 * i + 1).getA(), fractions.get(2 * i).getB() + fractions.get(2 * i + 1).getB());
//                System.out.println("Added fraction: " + fraction);
                fractions.add(2 * i + 1, fraction);
                fractionsInTree.add(fraction);

//                Node fractionNode = new Node();
//                fractionNode.setFraction(fraction);
//                insert(fractionNode);

            }
            L++;
//            System.out.println("Finished fractions: " + fractions);
//            System.out.println("Fractions in tree: " + fractionsInTree);
            outputFraction = findFraction(m, n);
            if (outputFraction != null) {
                break;
            }

        }
        return outputFraction;
    }

    public Fraction findFraction(int m, int n) {
        BigInteger M = new BigInteger(String.valueOf(m));
        BigInteger N = new BigInteger(String.valueOf(n));
//        System.out.println("Fractions in tree: " + fractionsInTree);
        for (int i = 0; i < fractionsInTree.size(); i++) {
            BigInteger asquared = new BigInteger(String.valueOf(fractionsInTree.get(i).getA()));
            asquared = asquared.pow(2);
            BigInteger bsquared = new BigInteger(String.valueOf(fractionsInTree.get(i).getB()));
            BigInteger b = bsquared;
            bsquared = bsquared.pow(2);

            BigInteger equation = N.multiply(asquared).subtract(M.multiply(bsquared));
            equation = equation.abs();

            if (equation.compareTo(b) < 0) {
                return new Fraction(fractionsInTree.get(i).getA(), fractionsInTree.get(i).getB());
            }
        }
        fractionsInTree.clear();
        return null;
    }

    public void insert(Node z) {

        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (z.fractionLessThan(x)) {
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        z.setParent(y);
        if (y == null) {
            root = z;
        }
        else if (z.fractionLessThan(y)) {
            y.setLeft(z);
        }
        else {
            y.setRight(z);
        }

    }

    public void inOrderTreeWalk(Node node) {
        // use this function to compare fractions
        if (node != null) {
            inOrderTreeWalk(node.getLeft());
            System.out.println(node.getFraction());
            inOrderTreeWalk(node.getRight());
        }
    }

    public Node getRoot() {
        return root;
    }
}
