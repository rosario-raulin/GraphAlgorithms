package de.raulin.rosario.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosario on 20.03.14.
 */
public class CC {

    private int id[];
    private int count;
    private List<Integer>[] comps;

    @SuppressWarnings("unchecked")
    public CC(Graph graph) {
        this.id = new int[graph.V()];
        this.count = 0;

        boolean[] marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); ++i) {
            if (!marked[i]) {
                dfs(graph, marked, i);
                ++count;
            }
        }

        this.comps = (List<Integer>[])new List[count];
        for (int i = 0; i < count; ++i) {
            comps[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < graph.V(); ++i) {
            comps[id[i]].add(i);
        }
    }

    private void dfs(Graph graph, boolean[] marked, int i) {
        marked[i] = true;
        id[i] = count;
        for (int x : graph.adj(i)) {
            if (!marked[x]) {
                dfs(graph, marked, x);
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean connected(int v, int u) {
        return id[v] == id[u];
    }

    public Iterable<Integer>[] getComponents() {
        return comps;
    }
}
