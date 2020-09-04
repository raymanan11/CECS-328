package FractionTree;
import java.math.BigInteger;

public class Node {

    private Fraction fraction;

    private Node left;
    private Node right;
    private Node parent;

    public Node() {

        fraction = null;

        left = null;
        right = null;
        parent = null;
    }

    public boolean fractionLessThan(Node node) {
        if (this.fraction.getB() == node.getFraction().getB()) {
            if (this.fraction.getA() < node.getFraction().getA()) {
                return true;
            }
            else if (this.fraction.getA() >= node.getFraction().getA()) {
                return false;
            }
        }
        else {
            BigInteger fractionOneNum = new BigInteger(String.valueOf(this.fraction.getA()));
            BigInteger fractionTwoDem = new BigInteger(String.valueOf(node.getFraction().getB()));

            BigInteger adjustedOneNum = fractionOneNum.multiply(fractionTwoDem);

            BigInteger fractionTwoNum = new BigInteger(String.valueOf(node.getFraction().getA()));
            BigInteger fractionOneDem = new BigInteger(String.valueOf(this.fraction.getB()));

            BigInteger adjustedTwoNum = fractionTwoNum.multiply(fractionOneDem);

            if (adjustedOneNum.compareTo(adjustedTwoNum) < 0) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Fraction getFraction() {
        return fraction;
    }
}
