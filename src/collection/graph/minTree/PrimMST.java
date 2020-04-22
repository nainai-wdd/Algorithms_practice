package collection.graph.minTree;
import collection.graph.Edge;
import collection.graph.EdgeWeightGraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.LinkedList;

public class PrimMST {
    private boolean[] marked;
    private double[] distTo;
    private Edge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightGraph G) {
        marked = new boolean[G.V()];
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];
        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0;
        for (int v = 1; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(G, pq.delMin());


    }

    private void visit(EdgeWeightGraph g, int v) {
        marked[v] = true;
        for (collection.graph.Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeKey(w, e.weight());
                else                pq.insert(w, e.weight());
            }
        }
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> list = new LinkedList<>();
        for (Edge e : edgeTo) {
            list.add(e);
        }
        return list;
    }

    public double weight() {
        double sum = 0;
        for (Edge e : edgeTo) {
            sum += e.weight();
        }
        return sum;
    }
}
