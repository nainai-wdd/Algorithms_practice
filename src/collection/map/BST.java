package collection.map;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
//ok
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;


    private class Node {
        private Key key;
        private Value value;
        private int n;
        private Node left;
        private Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.n = 1;
        }
        
    }

    public BST() {
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (value == null) { //防御性代码,不进行懒删除
            delete(key);
            return;
        }
        root = put(root,key,value);
        assert check();
    }



    private Node put(Node x, Key key, Value value){

        if (x == null) return new Node(key, value);
        int compare = key.compareTo(x.key);

        if      (compare < 0 )  x.left = put(x.left, key, value);
        else if (compare > 0 )  x.right = put(x.right, key,value);
        else                    x.value = value;

        x.n = 1 + size(x.left) + size(x.right);
        return x;

    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");//记得控制判断
        return get(root,key);
    }

    private Value get(Node x, Key key){
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if      (compare < 0 )  return get(x.left,key);
        else if (compare > 0 )  return get(x.right,key);
        else                    return x.value;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root,key);
        assert check();
        
    }
    
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if      (compare < 0) x.left = delete(x.left, key);
        else if (compare > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }
    
    private int size(Node x){
        if (x == null)  return 0;
        else  return x.n;
    }
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root);
    }

    private Key max(Node x) {
        if (x.right == null) return x.key;
        else                 return max(x.right);
    }

    public Key floor(Key key) {
        if (key == null)  throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty())  throw new NoSuchElementException("calls floor() with empty symbol table");
        Node node = floor(root, key);
        if (node == null)  throw new NoSuchElementException("argument to floor() is too small");
        return node.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare == 0) return x;
        if (compare < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key floor2(Key key){
        if (key == null)  throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty())  throw new NoSuchElementException("calls floor() with empty symbol table");
        Node node = floor2(root, key, null);
        if (node == null)  throw new NoSuchElementException("argument to floor() is too small");
        return node.key;
    }
    private Node floor2(Node x, Key key, Node floor){//用参数floor缓存备选节点
        if (x == null) return floor;
        int compare = key.compareTo(x.key);
        if (compare < 0) return floor2(x.left, key,null);
        if (compare > 0) return floor2(x.right, key, x);
        else             return x;
    }

    public Key ceiling(Key key) {
        if (key == null)  throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty())  throw new NoSuchElementException("calls floor() with empty symbol table");
        Node node = ceiling(root, key);
        if (node == null)  throw new NoSuchElementException("argument to floor() is too small");
        return node.key;
    }
    private Node ceiling(Node x, Key key){
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare == 0) return x;
        if (compare > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public int rank(Key key) {
        if (key == null)  throw new IllegalArgumentException("argument to floor() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key){
        if (x == null) return 0;
        int compare = key.compareTo(x.key);
        if (compare < 0) return rank(x.left, key);
        if (compare > 0) return size(x.left) + 1 + rank(x.right, key);
        else             return size(x.left);
    }

    public Key select(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + index);
        }
        return select(root, index).key;
    }
    private Node select(Node x, int index){
        if (x == null) return null;
        int compare = index - size(x.left);
        if (compare < 0) return select(x.left, index);
        if (compare > 0) return select(x.right, compare-1);
        else             return x;

    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");//安全第一
        deleteMin(root);
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        else x.left = deleteMin(x.left);
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }


    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return null;
        else x.right = deleteMax(x.right);
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

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


    public int height(){
        return height(root);
    }

    private int height(Node x){
        if (x == null) return -1;
        return Math.max(height(x.left), height(x.right));
    }
    /**
     * ===================================================================
     * 检测代码
     * @return
     */
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (rank(select(i) ) != i) return false;
        for (Key key : keys())
            if (select(rank(key) ).compareTo(key) != 0) return false;
        return true;
    }



    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (size(x) != size(x.left) + size(x.right) + 1 ) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) < 0) return false;
        if (max != null && x.key.compareTo(max) > 0) return false;
        return isBST(x.left,min,x.left.key) && isBST(x.left, x.right.key, max);
    }

    public void show() {
        Stack<Node> stack = new Stack<>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
            x = stack.pop();
            System.out.println(x.key);
            x = x.right;

        }
    }


    public static void main(String[] args) {
        BST<Integer, Integer> st = new BST<Integer, Integer>();
        st.put(5,5);
        st.put(2,2);
        st.put(1,1);
        st.put(3,3);
        st.put(4,4);
        st.put(0,0);
        st.put(8,8);
        st.put(6,6);
        st.put(7,7);
        st.put(10,10);
        st.put(9,9);
        st.show();

//        Iterable<Integer> keys = st.keys();
//        for (Integer s : st.keys())
//            StdOut.println(s + " " + st.get(s));
//        Scanner scanner = new Scanner(System.in);
//        for (int i = 0; i < 111; i++) {
//            int key1 = scanner.nextInt();
//            st.delete(key1);
//            if (st.get(key1) == null){
//                System.out.println("删除成功");
//            }
//            for (Integer s : st.keys())
//                StdOut.println(s + " " + st.get(s));
//        }
    }


}


