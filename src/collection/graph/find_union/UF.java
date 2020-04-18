package collection.graph.find_union;

public class UF {
    private int[] parent;
    private int[] sz;
    private int count;

    public UF(int initial) {
        count = initial;
        parent = new int[initial];
        sz = new int[initial];
        for (int i = 0; i < initial; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i1 = find(p);
        int i2 = find(q);
        if (i1 == i2) return;
        if (sz[i1] > sz[i2]) {
            parent[i2] = i1;
            sz[i2]++;
        } else {
            parent[i1] = i2;
            sz[i1]++;
        }
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

}
