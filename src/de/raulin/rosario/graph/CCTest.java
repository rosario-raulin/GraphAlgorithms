package de.raulin.rosario.graph;

import java.util.Scanner;

/**
 * Created by Rosario on 20.03.14.
 */
public class CCTest {
    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));
        CC cc = new CC(graph);

        System.out.println(cc.count());

        int c = 0;
        for (Iterable<Integer> comps : cc.getComponents()) {
            System.out.print(c + ": [ ");
            for (Integer x : comps) {
                System.out.print(x + " ");
            }
            System.out.println("]");
            ++c;
        }
    }
}
