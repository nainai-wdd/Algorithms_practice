package collection.map;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;
//看一下n,我怀疑n有问题
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;


    private class Node {
        private Key key;
        private Value value;
        private int n;
        private Node left;
        private Node right;
        private boolean color;

        public Node(Key key, Value value) {
            this.n = 1;
            this.key = key;
            this.value = value;
            this.color = true;
        }
        
    }

    public RedBlackBST() {
    }

    public void put(Key key, Value value) {
        root = put(root,key,value);
        root.color = BLACK;
    }
    
    private Node put(Node x, Key key, Value value){
        if (x == null) return new Node(key, value);
        int compare = key.compareTo(x.key);
        if (compare < 0 )  x.left = put(x.left, key, value);
        else if (compare > 0 )  x.right = put(x.right, key,value);
        else x.value = value;
        
        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);

        x.n = 1 + size(x.left) + size(x.right);
        return x;
    }

    private void flipColor(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    private Node rotateRight(Node x) {
        Node t  = x.left;
        x.left = t.right;
        t.right = x;
        t.color = x.color;
        x.color = RED;
        t.n = x.n;
        x.n = 1 + size(x.left) + size(x.right);
        return t;
    }

    private Node rotateLeft(Node x) {
        Node t  = x.right;
        x.right = t.left;
        t.left = x;
        t.color = x.color;
        x.color = RED;
        t.n = x.n;
        x.n = 1 + size(x.left) + size(x.right);
        return t;
    }

    public Value get(Key key) {
        return get(root,key);
    }
    private Value get(Node x, Key key){
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare < 0 ) return get(x.left,key);
        else if (compare > 0 ) return get(x.right,key);
        else return x.value;
    }

    public void delete(Key key) { 
        delete(root,key);
        
    }
    
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare < 0) {
            helpLeft(x);
            x.left = delete(x.left, key);
        }
        else if (compare > 0) {
            helpRight(x);
            x.right = delete(x.right, key);
        }
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = deleteMin(t.right);
            x.left = t.left;
            x.right = t.right;
        }
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }

    public void deleteMin() {
        if (isRed(root.left) && isRed(root.right)) {
            root.left.color = RED;
            root.right.color = RED;
        }
        deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        helpLeft(x);
        x.left = deleteMin(x.left);
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }
    private void helpLeft(Node x){
        if (isRed(x.left) && !isRed(x.left.left) ){
            if (isRed(x.right.left)){
                Node t = x;
                x = t.right.left;
                t.right.left = x.right;
                x.right = t.right;
                t.right = x.left;
                x.left = t;
                x.color = t.color;
                t.left.color = RED;
            }
            else reColor(x);
        }
    }

    private void helpRight(Node x){
        if (!isRed(x.right.left) ){
            if (isRed(x.left.left)){
                Node t = x;
                x = t.left;
                t.left = x.right;
                x.right = t.right;
                t.right = x.right.left;
                x.color =t.color;
                t.color = RED;
            }
            else reColor(x);
        }
    }
    private void reColor(Node x){
        x.color = BLACK;
        x.left.color = RED;
        x.right.color = RED;
    }



    public void deleteMax() {
        deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return null;

        x.right = deleteMin(x.right);
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }



    
    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
        
    }


    public int size() {
        return root.n;
    }
    
    private int size(Node x){
        if (x == null) return 0;
        return x.n;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key min() { 
        return min(root);
    }

    private Key min(Node x) {
        if (x.left == null) return x.key;
        else return min(x.left);
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
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



    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue queue = new Queue<>();
        keys(queue, root, lo, hi);
        return queue;
    }

    private void keys(Queue queue, Node x, Key lo, Key hi) {
        if (x == null) return;
        int compareLo = x.key.compareTo(lo);
        int compareHi = x.key.compareTo(hi);
        if (compareLo > 0 )  keys(queue, x.left, lo, hi);
        if (compareLo >= 0 && compareHi <= 0 )  queue.enqueue(x.key);
        if (compareHi < 0 )  keys(queue, x.right, lo, hi);
    }
    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> st = new RedBlackBST<Integer, Integer>();
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


