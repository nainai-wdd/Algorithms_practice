package collection.graph.search;

import collection.graph.Graph;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.IOException;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle = false;

    public Cycle(Graph g) {

        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i])
                dfs(g, i);
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (marked[w])
                hasCycle = true;
            else
                dfs(g, w);
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("algorithms_1/graph.txt");
        if (file.isFile()){
            In in  = new In("algorithms_1/graph.txt");
            Graph G = new Graph(in);
            System.out.println(G);
            Cycle cycle = new Cycle(G);
            boolean hasCycle = cycle.hasCycle;
            System.out.println(hasCycle);
        }
    }

}
