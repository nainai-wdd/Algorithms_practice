package collection.graph.search;

import collection.graph.Graph;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.IOException;

public class CC {
    private int[] id;
    private int count;
    private boolean[] marked;

    public CC(Graph g) {
        id = new int[g.V()];
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        id[s] = count;
        for (Integer v : g.adj(s)) {
            if (!marked[v])
                dfs(g, v);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) throws IOException {
        File file = new File("algorithms_1/graph.txt");
        if (file.isFile()){
            In in  = new In("algorithms_1/graph.txt");
            Graph G = new Graph(in);
            System.out.println(G);
            CC cc = new CC(G);
            System.out.println(cc.count);
            for (int i = 0; i < cc.id.length; i++) {
                System.out.println(i + ": " + cc.id(i));
            }
        }
    }
}
