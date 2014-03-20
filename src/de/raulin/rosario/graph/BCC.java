package de.raulin.rosario.graph;

import java.util.Scanner;

/**
 * Created by Rosario on 20.03.14.
 */
public class BCC {

    private int[] low;

    public BCC(Graph graph) {
        this.low = new int[graph.V()];

        boolean[] marked = new boolean[graph.V()];
        int[] d = new int[graph.V()];

        int time = 0;
        for (int v = 0; v < graph.V(); ++v) {
            if (!marked[v]) {
                time = visit(graph, time, marked, d, v, v);
            }
        }
    }

    public boolean connected(int v, int w) {
        return low[v] == low[w];
    }

    private int visit(Graph graph, int time, boolean[] marked, int[] d, int v, int u) {
        marked[v] = true;
        d[v] = ++time;
        low[v] = d[v];
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                time = visit(graph, time, marked, d, w, v);
                if (low[w] < low[v]) low[v] = low[w];
            } else if (w != u) {
                if (d[w] < low[v]) low[v] = d[w];
            }
        }
        return ++time;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));
        BCC bcc = new BCC(graph);

        System.out.println(graph.E());

        for (int i = 0; i < graph.V(); ++i) {
            for (int j = 0; j < graph.V(); ++j) {
                if (bcc.connected(i, j)) {
                    System.out.println(i + " und " + j + " liegen in der gleichen BCC");
                }
            }
        }
    }
}
