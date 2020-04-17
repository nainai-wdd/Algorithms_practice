package collection.map;

import java.util.LinkedList;
import java.util.Queue;

public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private static final float LOAD_FACTOR = 0.5f;


    private int n;
    private int m;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int size) {
        this.m = size;
        this.n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int hashCode(Key key) {
        return (key.hashCode() & 0xfffffff) % m;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("contains()方法获得了非法参数null");
        return get(key) != null;
    }

    private void resize(int size) {
        LinearProbingHashST<Key, Value> hashST = new LinearProbingHashST<>(size);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) hashST.put(keys[i], vals[i]);
        }
        this.m    = hashST.m;
        this.keys = hashST.keys;
        this.vals = hashST.vals;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("get()方法获得非法参数null");
        int i = hashCode(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) return vals[i];
            i = (i + 1) % m;
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("put()方法获得的第一个参数为非法参数null");
        if (value == null) {
            delete(key);
            return;
        }

        int i = hashCode(key);
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                vals[i] = value;
                return;
            }
            i = (i + 1) % m;
        }
        n++;
        keys[i] = key;
        vals[i] = value;
        if (n > LOAD_FACTOR*m) resize(2*m);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("delete()方法获得了非法参数null");
        if (!contains(key)) return;
        int i = hashCode(key);
        while (!keys[i].equals(key)) {
            i = (i+1) % m;
        }
        keys[i] = null;
        vals[i] = null;
        n--;
        if (4*n < LOAD_FACTOR*m){
            resize(m/2);
            return;
        }

        while (keys[i] != null) {
            Key key1 = keys[i];
            Value val1 = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(key1, val1);
        }

    }

    private Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) queue.add(keys[i]);
        }
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> hashST = new LinearProbingHashST<>();
        for (int i = 0; i < 30; i++) {
            hashST.put(String.valueOf(i), i);
        }
        for (int i = 30; i < 50; i++) {
            hashST.put(String.valueOf(i), i);
        }
//        for (String key : hashST.keys()) {
//            System.out.println(key+": "+hashST.get(key));
//        }
        for (int i = 0; i < 48; i++) {
            hashST.delete(String.valueOf(i));
        }
        for (String key : hashST.keys()) {
            System.out.println(key+": "+hashST.get(key));
        }
    }
}
