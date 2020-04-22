package collection.graph.minTree;

import collection.graph.Edge;
import collection.graph.EdgeWeightGraph;
import collection.graph.find_union.UF;
import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;
import java.util.Queue;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightGraph G) {
        mst = new LinkedList<Edge>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges())
            pq.insert(e);
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.find(w) == uf.find(v)) continue;
            uf.union(w, v);
            mst.add(e);
        }

    }
    public Iterable<Edge> edges(){
        return mst;
    }
    public double weight() {
        double sum = 0.0;
        for (Edge edge : mst) {
            sum += edge.weight();
        }
        return sum;
    }
}
