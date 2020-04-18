package collection.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.IOException;

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private Bag<Integer>[] adj;
    private final int V;
    private int E;

    public Digraph(int n) {
        V = n;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[n];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public void add(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        E++;
    }
    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < adj.length; v++) {
            for (Integer w : adj[v]) {
                digraph.add(w,v);
            }
        }
        return digraph;
    }


    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    public static void main(String[] args) throws IOException {
        File file = new File("algorithms_1/graph.txt");
        if (file.isFile()){
            In in  = new In("algorithms_1/graph.txt");
            Graph G = new Graph(in);
            StdOut.println(G);
        }

    }
}
