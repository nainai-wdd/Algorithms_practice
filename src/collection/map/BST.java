package collection.map;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;
//看一下delete
public class BST<Key extends Comparable<Key>, Value> {

    private Node<Key> root;


    private class Node<Key> {
        private Key key;
        private Value value;
        private int n;
        private Node<Key> left;
        private Node<Key> right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.n = 1;
        }
        
    }

    public BST() {
    }

    public void put(Key key, Value value) {
        root = put(root,key,value);
    }
    
    private Node<Key> put(Node<Key> x, Key key, Value value){
        if (x == null) return new Node<>(key, value);
        int compare = key.compareTo(x.key);
        if (compare < 0 )  x.left = put(x.left, key, value);
        else if (compare > 0 )  x.right = put(x.right, key,value);
        else x.value = value;
        x.n = size(x.left)+1+size(x.right);
        return x;
    }

    public Value get(Key key) {
        return get(root,key);
    }
    private Value get(Node<Key> x, Key key){
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare < 0 ) return get(x.left,key);
        else if (compare > 0 ) return get(x.right,key);
        else return x.value;
    }

    public void delete(Key key) { 
        delete(root,key);
        
    }
    
    private Node<Key> delete(Node<Key> x, Key key) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare < 0) x.left = delete(x.left, key);
        else if (compare > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node<Key> t = x;
            x = deleteMin(t.right);
            x.left = t.left;
            x.right = t.right;
        }
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return root.n;
    }
    
    private int size(Node<Key> x){
        if (x == null) return 0;
        return x.n;
    }
    public Key min() { 
        return min(root);
    }

    private Key min(Node<Key> x) {
        if (x.left == null) return x.key;
        else return min(x.left);
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node<Key> x) {
        if (x.right == null) return x.key;
        else return max(x.right);
    }

    public Key floor(Key key) {
        return null;
    }

    public Key ceiling(Key key) {
        return null;
    }

    public int rank(Key key) {
        return 0;
    }

    public Key select(Key key) {
        return null;
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node<Key> deleteMin(Node<Key> x) {
        if (x.left == null) return null;
        else x.left = deleteMin(x.left);
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }


    public void deleteMax() {
        deleteMax(root);
    }

    private Node<Key> deleteMax(Node<Key> x) {
        if (x.right == null) return null;
        else x.right = deleteMin(x.right);
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }

    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(queue, root, lo, hi);
        return queue;
    }

    private void keys(Queue<Key> queue, Node<Key> x, Key lo, Key hi) {
        if (x == null) return;
        int compareLo = x.key.compareTo(lo);
        int compareHi = x.key.compareTo(hi);
        if (compareLo > 0 )  keys(queue, x.left, lo, hi);
        if (compareLo >= 0 && compareHi <= 0 )  queue.enqueue(x.key);
        if (compareHi < 0 )  keys(queue, x.right, lo, hi);
    }
    public static void main(String[] args) {
        BST<Integer, Integer> st = new BST<Integer, Integer>();
        for (int i = 0; i < 10; i++) { ;
            st.put(i, i);
        }
        Iterable<Integer> keys = st.keys();
        for (Integer s : st.keys())
            StdOut.println(s + " " + st.get(s));
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
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


