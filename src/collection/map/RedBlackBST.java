package collection.map;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;


    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color;
        private int n;

        public Node(Key key, Value value) {//新节点默认红,n = 1;
            this.n = 1;
            this.key = key;
            this.value = value;
            this.color = RED;
        }
        
    }

    public RedBlackBST() {
    }

    /***************************************************************************
     *  Node helper methods.
    ***************************************************************************/
    private boolean isRed(Node h){
        if (h == null) return false;
        return h.color == RED;
    }

    public int size() {
        return size(root);
    }

    private int size(Node h){
        if (h == null) return 0;
        return h.n;
    }


    public boolean isEmpty() {
        return root == null;
    }
    
    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root,key);
    }

    private Value get(Node h, Key key){
        while (h != null){
            int compare = key.compareTo(h.key);
            if      (compare < 0) h = h.left;
            else if (compare > 0) h = h.right;
            else                  return h.value;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null){
            delete(key);
            return;
        }

        root = put(root,key,value);
        root.color = BLACK;

        assert check();

    }

    private Node put(Node h, Key key, Value value){
        if (h == null) return new Node(key, value);

        int compare = key.compareTo(h.key);
        if      (compare < 0)  h.left = put(h.left, key, value);
        else if (compare > 0)  h.right = put(h.right, key,value);
        else                   h.value = value;

        if (!isRed(h.left) && isRed(h.right))      h = rotateLeft(h);
        if (isRed(h.left)  && isRed(h.left.left))  h = rotateRight(h);
        if (isRed(h.left)  && isRed(h.right))      flipColors(h);
        h.n =size(h.left) + size(h.right) + 1;
        return h;


    }

    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/
    
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        assert check();
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;//删除最左端

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;


        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        assert check();
    }
    /**
     * 把下一个节点构造成可以直接删除的3-节点,红黑树中非法的(黑-红)节点
     */
    private Node deleteMax(Node h) {
        //不让左子节点为红节点,让右节点为非法的右红节点,保证右子节点可以直接删除
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null) return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);

    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root,key);
        if (!isEmpty()) root.color = BLACK;
        assert check();
    }

    private Node delete(Node h, Key key) {

        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else{
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0){
                Node x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }



    /*********************************************************************
     *  辅助方法
     * *******************************************************************/
    private Node balance(Node h){
        if (!isRed(h.left) && isRed(h.right) )      h = rotateLeft(h);
        if (isRed(h.left)  && isRed(h.left.left))  h = rotateRight(h);
        if (isRed(h.left)  && isRed(h.right))      flipColors(h);
        h.n =size(h.left) + size(h.right) + 1;
        return h;
    }
    
    private void flipColors(Node h) {
//         h must have opposite color of its two children
         assert (h != null) && (h.left != null) && (h.right != null);
         assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
            || (isRed(h)  && !isRed(h.left) && !isRed(h.right));

        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node rotateRight(Node h) {
        Node t  = h.left;
        h.left = t.right;
        t.right = h;
        t.color = t.right.color;
        t.right.color = RED;
        t.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return t;
    }

    private Node rotateLeft(Node h) {
        Node t  = h.right;
        h.right = t.left;
        t.left = h;
        t.color = t.left.color;
        t.left.color = RED;
        t.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return t;
    }

    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        // assert !isRed(h.left);
        flipColors(h);
        //assert isRed(h.left)
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


    /*********************************************************************
     *
     * *******************************************************************/

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node h) {
        if (h.left == null) return h;
        else                return min(h.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node h) {
        if (h.right == null) return h;
        else                 return max(h.right);
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

    private void keys(Queue queue, Node h, Key lo, Key hi) {
        if (h == null) return;
        int compareLo = h.key.compareTo(lo);
        int compareHi = h.key.compareTo(hi);
        if (compareLo > 0) keys(queue, h.left, lo, hi);
        if (compareLo >= 0 && compareHi <= 0)  queue.enqueue(h.key);
        if (compareHi < 0) keys(queue, h.right, lo, hi);
    }



    /*********************************************************************
     * 未实现的方法
     * *******************************************************************/

    public Key floor(Key key) {
        return null;
    }

    public Key ceiling(Key key) {
        return null;
    }
    /*********************************************************************
     * 抄的
     * *******************************************************************/
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // number of keys less than key in the subtree rooted at h
    private int rank(Key key, Node h) {
        if (h == null) return 0;
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) return rank(key, h.left);
        else if (cmp > 0) return 1 + size(h.left) + rank(key, h.right);
        else              return size(h.left);
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalueid: " + k);
        }
        Node h = select(root, k);
        return h.key;
    }
    // the key of rank k in the subtree rooted at h
    private Node select(Node h, int k) {
        // assert h != null;
        // assert k >= 0 && k < size(h);
        int t = size(h.left);
        if      (t > k) return select(h.left,  k);
        else if (t < k) return select(h.right, k-t-1);
        else            return h;
    }



    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        if (!is23())             StdOut.println("Not a 2-3 tree");
        if (!isBalanced())       StdOut.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
        
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at h a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node h, Key min, Key max) {
        if (h == null) return true;
        if (min != null && h.key.compareTo(min) <= 0) return false;
        if (max != null && h.key.compareTo(max) >= 0) return false;
        return isBST(h.left, min, h.key) && isBST(h.right, h.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node h) {
        if (h == null) return true;
        if (h.n != size(h.left) + size(h.right) + 1) return false;
        return isSizeConsistent(h.left) && isSizeConsistent(h.right);
    }

//     check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(Node h) {
        if (h == null) return true;
        if (isRed(h.right)) return false;
        if (h != root && isRed(h) && isRed(h.left))
            return false;
        return is23(h.left) && is23(h.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node h = root;
        while (h != null) {
            if (!isRed(h)) black++;
            h = h.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node h, int black) {
        if (h == null) return black == 0;
        if (!isRed(h)) black--;
        return isBalanced(h.left, black) && isBalanced(h.right, black);
    }

    /*********************************************************************
     * 测试方法
     * *******************************************************************/
    public Node rep(){
        return (collection.map.RedBlackBST.Node)root;
    }
    
    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> st = new RedBlackBST<Integer, Integer>();
        for (int i = 0; i > -15; i--) { ;
            st.put(i, i);
        }
        Iterable<Integer> keys = st.keys();
        for (Integer s : st.keys())
            StdOut.println(s + " " + st.get(s));
        Scanner scanner = new Scanner(System.in);
        int key1 = 1;
        while (key1 != 100){
            key1 = scanner.nextInt();
            st.delete(key1);
            for (Integer s : st.keys())
                StdOut.println(s + " " + st.get(s));
            key1 = scanner.nextInt();
            st.delete(key1);
            for (Integer s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }


    }


}


