package collection.graph.search;

import collection.graph.Graph;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.IOException;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean hasTwoColor = true;

    public TwoColor(Graph g) {
        color = new boolean[g.V()];

        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i])
                dfs(g, i);
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v])
                hasTwoColor = false;
        }
    }


    public boolean hasTwoColor() {
        return hasTwoColor;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("algorithms_1/graph.txt");
        if (file.isFile()){
            In in  = new In("algorithms_1/graph.txt");
            Graph G = new Graph(in);
            System.out.println(G);
            TwoColor twoColor = new TwoColor(G);
            System.out.println(twoColor.hasTwoColor());
        }
    }

}
