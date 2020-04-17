package collection.graph.search;

import collection.graph.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.io.File;
import java.io.IOException;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public DepthFirstPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        for (Integer i : g.adj(s)) {
            if (!marked[i]) {
                edgeTo[i] = s;
                dfs(g, i);
            }
        }
    }

    public boolean hasPaths(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> stack = new Stack<>();
        if (!hasPaths(v)) {
            System.out.println("无法连通");
            return stack;
        }
        for (; v != s; v = edgeTo[v])
            stack.push(v);
        stack.push(s);
        return stack;
    }
    public static void main(String[] args) throws IOException {
        File file = new File("algorithms_1/graph.txt");
        if (file.isFile()){
            In in  = new In("algorithms_1/graph.txt");
            Graph G = new Graph(in);
            System.out.println(G);
            DepthFirstPaths paths = new DepthFirstPaths(G, 4);
            for (Integer i : paths.pathTo(1)) {
                System.out.println(i);
            }
        }
    }

}
