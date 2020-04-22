package collection.graph.minTree;

import collection.graph.Edge;
import collection.graph.EdgeWeightGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> pq;
    public LazyPrimMST(EdgeWeightGraph G) {
        marked = new boolean[G.V()];
        mst = new LinkedList();
        pq = new PriorityQueue();
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.remove();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.add(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v))
            if (!marked[e.other(v)]) pq.add(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }
    public double weight() {
        double sum = 0;
        for (Edge e : mst) {
            sum += e.weight();
        }
        return sum;
    }
}
