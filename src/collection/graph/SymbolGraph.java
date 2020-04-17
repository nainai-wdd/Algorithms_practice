package collection.graph;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;

public class SymbolGraph {
    private HashMap<String, Integer> st;
    private String[] keys;
    private Graph G;
    public SymbolGraph(String filename, String delim) {
        st = new HashMap<String, Integer>();
        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            for (int i = 0; i < a.length; i++) {
                if (!st.containsKey(a[i]))
                    st.put(a[i], st.size());
            }
        }
        keys = new String[st.size()];
        for (String s : st.keySet()) {
            keys[st.get(s)] = s;
        }
        G = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readString().split(delim);
            Integer v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }

    }

    public boolean contains(String key) {
        return st.containsKey(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

}
