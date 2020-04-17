package collection.graph.search;

import collection.graph.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.IOException;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

        private void dfs(Graph g, int s) {
        System.out.println(s);
        marked[s] = true;
        count++;
        for (Integer i : g.adj(s)) {
            if (!marked[i])
                dfs(g, i);
        }
    }
//    private void dfs(Graph g, int s) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(s);
//        marked[s] = true;
//        count++;
//        while (!stack.isEmpty()) {
//            Integer pop = stack.pop();
//            System.out.println(pop);
//            for (Integer i : g.adj(pop)) {
//                if (!marked[i]) {
//                    count++;
//                    marked[i] = true;
//                    stack.push(i);
//                }
//            }
//        }
//    }

    public boolean marked(int v) {
        return marked[v];
    }
    public int count() {
        return count;
    }
    public static void main(String[] args) throws IOException {
        File file = new File("algorithms_1/graph.txt");
        if (file.isFile()){
            In in  = new In("algorithms_1/graph.txt");
            Graph G = new Graph(in);
            System.out.println(G);
            new DepthFirstSearch(G, 2);
        }
    }

}
