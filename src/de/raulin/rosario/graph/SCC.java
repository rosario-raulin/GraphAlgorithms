package de.raulin.rosario.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosario on 20.03.14.
 */
public class SCC {

    private int[] scc;
    private int count;
    private List<Integer>[] comps;

    @SuppressWarnings("unchecked")
    public SCC(DiGraph graph) {
        this.count = 0;
        this.scc = new int[graph.V()];

        Stack<Integer> dfOrder = dfOrder(graph.reverse());
        boolean marked[] = new boolean[graph.V()];

        for (Integer v : dfOrder) {
            if (!marked[v]) {
                sccVisit(graph, marked, v, count);
                ++count;
            }
        }

        this.comps = (List<Integer>[]) new List[count];
        for (int i = 0; i < count; ++i) {
            this.comps[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < graph.V(); ++i) {
            this.comps[scc[i]].add(i);
        }
    }

    public boolean connected(int v, int w) {
        return scc[v] == scc[w];
    }

    public int count() {
        return count;
    }

    public Iterable<Integer>[] getSCCs() {
        return comps;
    }

    private void sccVisit(DiGraph graph, boolean[] marked, Integer v, int count) {
        marked[v] = true;
        scc[v] = count;

        for (int x : graph.adj(v)) {
            if (!marked[x]) {
                sccVisit(graph, marked, x, count);
            }
        }
    }

    private Stack<Integer> dfOrder(DiGraph reversed) {
        Stack<Integer> dfOrder = new Stack<Integer>();
        boolean[] marked = new boolean[reversed.V()];

        for (int i = 0; i < reversed.V(); ++i) {
            if (!marked[i]) {
                dfVisit(reversed, marked, dfOrder, i);
            }
        }

        return dfOrder;
    }

    private void dfVisit(DiGraph reversed, boolean[] marked, Stack<Integer> dfOrder, int v) {
        marked[v] = true;
        for (int x : reversed.adj(v)) {
            if (!marked[x]) {
                dfVisit(reversed, marked, dfOrder, x);
            }
        }
        dfOrder.push(v);
    }
}
