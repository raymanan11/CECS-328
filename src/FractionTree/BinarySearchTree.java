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

        // maybe do a while true then when it finds the numbers that satisfy the equation, return that
        while (fractionsInTree.size() < 10) {
            for (int i = 0; i <= Math.pow(2, L) - 1; i++) {
                Fraction fraction = new Fraction(fractions.get(2 * i).getA() + fractions.get(2 * i + 1).getA(), fractions.get(2 * i).getB() + fractions.get(2 * i + 1).getB());
//                System.out.println("Added fraction: " + fraction);
                fractions.add(2 * i + 1, fraction);
                fractionsInTree.add(fraction);

//                M = new BigInteger(String.valueOf(m));
//                N = new BigInteger(String.valueOf(n));
//                Node fractionNode = new Node();
//                fractionNode.setFraction(fraction);
//                insert(fractionNode);
                // check the fractions that are only added in this level of the tree instead
                // of going through whole tree like what is being done down below when findFractions
                // is called
                // maybe have another method that does this and then resets the fractionsInTree so
                // won't remember the past fractions
            }
            L++;
//            System.out.println("Finished fractions: " + fractions);
//            System.out.println("Fractions in tree: " + fractionsInTree);
            outputFraction = findFraction(m, n);
            if (outputFraction != null) {
                break;
            }
//            outputFraction = inOrderTreeWalk(root);
//            if (outputFraction != null) {
//                break;
//            }

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

//    public Fraction findFraction(int m, int n) {
//        BigInteger M = new BigInteger(String.valueOf(m));
//        BigInteger N = new BigInteger(String.valueOf(n));
//        for (int i = 0; i < fractions.size(); i++) {
//            BigInteger asquared = new BigInteger(String.valueOf(fractions.get(i).getA()));
//            asquared = asquared.pow(2);
//            BigInteger bsquared = new BigInteger(String.valueOf(fractions.get(i).getB()));
//            BigInteger b = bsquared;
//            bsquared = bsquared.pow(2);
//
//            BigInteger equation = N.multiply(asquared).subtract(M.multiply(bsquared));
//            equation = equation.abs();
//
////            System.out.println(N + "^" + asquared + " - " + M + "^" + bsquared + " < " + b);
////            System.out.println(equation + " < " + b);
//
//            if (equation.compareTo(b) < 0) {
//                return new Fraction(fractions.get(i).getA(), fractions.get(i).getB());
//            }
//        }
//        return null;
//    }


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

    // have it return a Fraction and if its not null exit out of the loop
    public void inOrderTreeWalk(Node node) {
        // use this function to compare fractions
        if (node != null) {
            inOrderTreeWalk(node.getLeft());
            System.out.println(node.getFraction());
            inOrderTreeWalk(node.getRight());
        }
    }

//    public Fraction inOrderTreeWalk(Node node) {
//        // use this function to compare fractions
//        if (node != null) {
//            System.out.println("in here");
//            inOrderTreeWalk(node.getLeft());
////            System.out.println(node.getFraction());
//            BigInteger asquared = new BigInteger(String.valueOf(node.getFraction().getA()));
//            asquared = asquared.pow(2);
//            BigInteger bsquared = new BigInteger(String.valueOf(node.getFraction().getB()));
//            BigInteger b = bsquared;
//            bsquared = bsquared.pow(2);
//
//            BigInteger equation = N.multiply(asquared).subtract(M.multiply(bsquared));
//            equation = equation.abs();
//
//            if (equation.compareTo(b) < 0) {
//                return new Fraction(node.getFraction().getA(), node.getFraction().getB());
//            }
//            inOrderTreeWalk(node.getRight());
//        }
//        return null;
//    }

    public Node getRoot() {
        return root;
    }
}
