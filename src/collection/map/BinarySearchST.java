package collection.map;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;


    public BinarySearchST() {
        this(2);
    }

    private BinarySearchST(int init) {
        keys = (Key[]) new Comparable[init];
        values = (Value[]) new Object[init];
        n = 0;
    }

    //扩容
    private void expansion() {
        Key[] es = (Key[]) new Comparable[n * 2];
        Value[] vs = (Value[]) new Object[n * 2];
        for (int i = 0; i < keys.length; i++) {
            es[i] = keys[i];
            vs[i] = values[i];
        }
        keys = es;
        values = vs;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("argument to put() is null");
        if (value == null) delete(key);
        else {
        int i = rank(key);
        // key is already in table
        if (i < n && ((Comparable<Key>)keys[i]).compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        if (n >= keys.length) expansion();
        for (int j = n; j > i; j--){
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }
    }


    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = rank(key);
        if (keys[i] != key) return null;
        else return values[i];
    }

    public void delete(Key key) {
        if (n == 0) throw new NoSuchElementException("calls delete() with empty symbol table");
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        if (n >= keys.length) expansion();
        for (int i = rank(key); i < n-1; i++){
            keys[i] = keys[i+1];
            values[i] = values[i+1];
        }
        n--;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public Key min() {
        if (n == 0) throw new NoSuchElementException("calls min() with empty symbol table");
        return keys[0];
    }


    public Key max() {
        if (n == 0) throw new NoSuchElementException("calls max() with empty symbol table");
        return keys[n-1];
    }

    public Key floor(Key key) {
        return null;
    }

    public Key ceiling(Key key) {
        return null;
    }

    private int rank(Key key){
        int lo = 0;
        int hi = n-1;
        int mid;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int compare = ((Comparable<Key>) key).compareTo(keys[mid]);
            if (compare > 0) lo = mid+1;
            else if (compare < 0) hi = mid-1;
            else  return mid;
        }
        return lo;
    }

    public Key select(Key key) {
        return null;
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (((Comparable<Key>) hi).compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    public Iterable<Key> keys() {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {
                    private Key[] items = keys;
                    private int i = 0;
                    @Override
                    public boolean hasNext() {
                        return i < n;
                    }

                    @Override
                    public Key next() {
                        return items[i++];
                    }
                };
            }
        };
    }


    public static void main(String[] args) {
        BinarySearchST<Integer, Integer> st = new BinarySearchST<Integer, Integer>();
        for (int i = 0; i < 5; i++) { ;
            st.put(i, i);
        }
        Iterable<Integer> keys = st.keys();
        for (Integer s : st.keys())
            StdOut.println(s + " " + st.get(s));
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 111; i++) {
            int key1 = scanner.nextInt();
            st.delete(key1);
            if (st.get(key1) == null){
                System.out.println("删除成功");
            }
            for (Integer s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }
    }
}


