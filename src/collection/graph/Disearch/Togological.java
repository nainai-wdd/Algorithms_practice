package collection.graph.Disearch;

import collection.graph.Digraph;
import edu.princeton.cs.algs4.Queue;


public class Togological {
    private Iterable<Integer> order;
    public Togological(Digraph G) {
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder order = new DepthFirstOrder(G);
            this.order = order.reversePost();
        }

    }


    public Iterable<Integer> order() {
        return order;
    }
}
