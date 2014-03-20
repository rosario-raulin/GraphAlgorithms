package de.raulin.rosario.graph;

import java.util.*;

/**
 * Created by Rosario on 20.03.14.
 */
public class BCC {

    private Set<Integer> articulationPoints;
    private int[] d;
    private int[] low;
    private int count;

    public BCC(Graph graph) {
        this.articulationPoints = new HashSet<Integer>();
        this.d = new int[graph.V()];
        this.low = new int[graph.V()];

        for (int i = 0; i < graph.V(); ++i) {
            d[i] = -1;
        }

        for (int v = 0; v < graph.V(); ++v) {
            if (d[v] == -1) {
                visit(graph, v, v);
            }
        }
    }

    private void visit(Graph graph, int v, int parent) {
        d[v] = count++;
        low[v] = d[v];

        int children = 0;
        for (Integer w : graph.adj(v)) {
            if (d[w] == -1) {
                children++;
                visit(graph, w, v);
                if (low[w] < low[v]) {
                    low[v] = low[w];
                }
                if (low[w] >= d[v] && parent != v) {
                    articulationPoints.add(v);
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], d[w]);
            }
        }

        if (v == parent && children >= 2) {
            articulationPoints.add(v);
        }
    }

    public Iterable<Integer> articulationPoints() {
        return articulationPoints;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));
        BCC bcc = new BCC(graph);

        for (Integer x : bcc.articulationPoints()) {
            System.out.println(x);
        }
    }
}
