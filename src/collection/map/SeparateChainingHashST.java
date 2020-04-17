package collection.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SeparateChainingHashST<Key,Value> {
    private int n;
    private int m;
    private SequentialSearchST<Key,Value>[] st;

    public SeparateChainingHashST() {
        this(4);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key,Value>[])new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<Key,Value>();
        }
    }

    private void resize(int size) {
        SeparateChainingHashST<Key, Value> searchST = new SeparateChainingHashST<Key, Value>(size);
        for (int i = 0; i < m; i++)
            for (Key key : st[i].keys())
                searchST.put(key, st[i].get(key));
        this.m = searchST.m;
        this.n = searchST.n;
        this.st = searchST.st;
    }

    private int hashCode(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        if (n >= 10*m) resize(2*m);

        int i = hashCode(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, value);

    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hashCode(key);
        return st[i].get(key);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        int i = hashCode(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        if (m > 4 && n <= 2*m) resize(m/2);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return   get(key) != null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.add(key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> chainingHashST = new SeparateChainingHashST<>();
        for (int i = 0; i < 10; i++) {
            chainingHashST.put(String.valueOf(i), i);
        }
        for (String key : chainingHashST.keys()) {
            System.out.println(key+": "+chainingHashST.get(key));
        }

    }


}
