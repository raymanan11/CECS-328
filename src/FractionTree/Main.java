package FractionTree;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner inputFile = new Scanner(new File("input.txt"));

            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            int m = inputFile.nextInt();

            int n = inputFile.nextInt();

            BinarySearchTree bst = new BinarySearchTree();
            Fraction outputFraction = bst.populateAndFindFractions(m, n);

            System.out.println("a: " + outputFraction.getA());
            System.out.println("b: " + outputFraction.getB());

            writer.println(outputFraction.getA());
            writer.println(outputFraction.getB());
            writer.close();

        }

        catch(IOException excpt) {
            System.out.println("File Not Found!");
        }

    }

}
