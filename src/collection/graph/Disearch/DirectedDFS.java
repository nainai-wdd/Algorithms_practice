package collection.graph.Disearch;

import collection.graph.Digraph;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int p) {
        marked = new boolean[G.V()];
        dfs(G, p);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> source) {
        marked = new boolean[G.V()];
        for (Integer p : source)
            if (!marked[p])
                dfs(G, p);

    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}
