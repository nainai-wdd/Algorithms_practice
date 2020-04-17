package collection.graph.search;

import collection.graph.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.IOException;

public class BreadFirstSearch {
    private boolean[] marked;
    private int count;

    public BreadFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        bfs(G, s);
    }


    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        count++;
        while (!queue.isEmpty()) {
            Integer v = queue.dequeue();
            System.out.println(v);
            for (Integer w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    count++;
                    queue.enqueue(w);
                }
            }
        }
    }

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
            new BreadFirstSearch(G, 2);
        }
    }

}
