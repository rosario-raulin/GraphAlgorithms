package de.raulin.rosario.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rosario on 20.03.14.
 */
public class Graph {

    private List<Integer>[] adj;
    private int E;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
        this.E = 0;
        this.adj = (List<Integer>[]) new List[V];

        for (int i = 0; i < V; ++i) {
            this.adj[i] = new ArrayList<Integer>();
        }
    }

    public Graph(Scanner in) {
        this(in.nextInt());

        int E = in.nextInt();
        for (int i = 0; i < E; ++i) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }

    public int E() {
        return E;
    }

    public int V() {
        return adj.length;
    }

    public void addEdge(int x, int y) {
        adj[x].add(y);
        adj[y].add(x);
        ++E;
    }

    public Iterable<Integer> adj(int x) {
        return adj[x];
    }
}
