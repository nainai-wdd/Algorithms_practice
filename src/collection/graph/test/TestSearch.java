package collection.graph.test;

import collection.graph.Graph;
import edu.princeton.cs.algs4.In;

import java.io.IOException;

public class TestSearch {
    public static void main(String[] args) throws IOException {
        Graph G = new Graph(new In("algorithms_1/graph.txt"));
        int s = System.in.read() - 48;
        Search search = new Search(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                System.out.println(v + " ");
            System.out.println();
        }
        if (search.count() != G.V())
            System.out.println("NOT ");
        System.out.println("connected");
    }
}
