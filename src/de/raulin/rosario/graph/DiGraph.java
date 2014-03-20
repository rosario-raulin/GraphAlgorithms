package de.raulin.rosario.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rosario on 20.03.14.
 */
public class DiGraph {
    private int E;
    private List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public DiGraph(int V) {
        this.E = 0;
        this.adj = (List<Integer>[]) new List[V];

        for (int i = 0; i < V; ++i) {
            this.adj[i] = new ArrayList<Integer>();
        }
    }

    public DiGraph(Scanner in) {
        this(in.nextInt());

        int E = in.nextInt();
        for (int i = 0; i < E; ++i) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return adj.length;
    }

    public int E() {
        return E;
    }

    public void addEdge(int from, int to) {
        adj[from].add(to);
        ++E;
    }

    public DiGraph reverse() {
        DiGraph reversed = new DiGraph(V());

        for (int u = 0; u < V(); ++u) {
            for (int w : adj(u)) {
                reversed.addEdge(w, u);
            }
        }

        return reversed;
    }
}
