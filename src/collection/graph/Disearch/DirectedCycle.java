package collection.graph.Disearch;

import collection.graph.Digraph;
import collection.graph.Graph;
import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G) {
        int n = G.V();
        edgeTo = new int[n];
        marked = new boolean[n];
        onStack = new boolean[n];
        for (int v = 0; v < n; v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


}
