package collection.map.practice;

import collection.map.RedBlackBSTTest;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RedBlackBST2<Key extends Comparable<Key>, Value> {
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

    public RedBlackBST2() {
    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/

    private int size(Node node){
        if (node == null) return 0;
        else return node.n;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public int size() {
        return size(root);
    }


    private boolean isEmpty(){
        return  root == null;

    }
    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) throw new NoSuchElementException("calls get() with empty map");
        return get(root, key);
    }

    private Value get(Node x, Key key){
        if (x == null) return null;

        int compare = key.compareTo(x.key);
        if      (compare < 0) return get(x.left, key);
        else if (compare > 0) return get(x.right, key);
        else                  return x.value;

    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/
    public void put(Key key, Value value){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");

        root = put(root, key, value);
        root.color = BLACK;
        assert check();
    }



    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value);
        int compare = key.compareTo(x.key);
        if (compare < 0) x.left = put(x.left, key, value);
        else if (compare > 0) x.right = put(x.right, key, value);
        else x.value = value;
        return balance(x);
    }


    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/
    public void deleteMin(){
        if (isEmpty()) throw new NoSuchElementException("calls to deleteMin() with empty map");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        root.color = BLACK;
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;

        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);

        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMax(){
        if (isEmpty()) throw new NoSuchElementException("calls to delete() with empty map");
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        assert check();
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left))
            x = rotateRight(x);

        if (x.right == null) return null;

        if (!isRed(x.right) && !isRed(x.right.left))
            x = moveRedRight(x);

        x.right = deleteMax(x.right);
        return balance(x);
    }

    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("argument to delete() with empty map");
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);

        if (!isEmpty()) root.color = BLACK;
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (key.compareTo(x.key) < 0){
            if (!isRed(x.left) && !isRed(x.left.left))
                x = moveRedLeft(x);//为了保证下一个访问的节点是3-节点(红节点,或者左子节点为红)
            x.left = delete(x.left, key);
        }
        else {
            if (isRed(x.left))
                x = rotateRight(x);
            if (key.compareTo(x.key) == 0 && x.left == null)//说明节点为红色的叶节点.可以直接删除
                return null;
            if (!isRed(x.right) && !isRed(x.right.left))    //为了保证 deleteMin(x.right) 可以执行
                x = moveRedRight(x);
            if (key.compareTo(x.key) == 0){
                Node t = min(x.right);
                x.key = t.key;
                x.value = t.value;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(x.right, key);
        }
        return balance(x);
    }
    

    private boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() with empty map");
        return get(key) != null;
    }


    /*********************************************************************
     *  辅助方法
     * *******************************************************************/

    private Node balance(Node x) {
        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left)  && isRed(x.left.left)) x= rotateRight(x);
        if (isRed(x.left)  && isRed(x.right)) flipColor(x);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void flipColor(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.n = h.n;
        h.color = RED;
        h.n = size(h.left) + size(h.right) + 1;

        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.n = h.n;
        h.color = RED;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node moveRedLeft(Node h) {
        flipColor(h);
        if (isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColor(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColor(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColor(h);
        }
        return h;
    }



    /*********************************************************************
     *
     * *******************************************************************/

    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("calls to min with empty map");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max(){
        if (isEmpty()) throw new NoSuchElementException("calls to max with empty map");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    /***************************************************************************
     *  Utility functions.
     ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the symbol table between {@code lo}
     *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int compLo = lo.compareTo(x.key);
        int compHi = hi.compareTo(x.key);
        if (compLo < 0) keys(x.left, queue, lo, hi);
        if (compLo <= 0 && compHi >= 0) queue.enqueue(x.key);
        if (compHi > 0) keys(x.right, queue, lo, hi);
    }


    /*********************************************************************
     * 抄的
     * *******************************************************************/
    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
       if (key == null) throw new IllegalArgumentException("argument to floor() is null");
       if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
       Node x = floor(root, key);
       if (x == null) throw new NoSuchElementException("argument to floor() is too small");
       else           return x.key;
    }

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) return null;//退出条件
        int compare = key.compareTo(x.key);
        if (compare == 0) return x;
        if (compare < 0) return floor(x.left, key);//向左遍历,若遍历到叶节点依旧满足条件,则返回null
        //(证明上一次从左向右越界时的节点就是所查找的节点)
        /**
         *  此时(compare >= 0),向右遍历,越界进入(compare <= 0),
         *  当floor(x.right, key)返回值为null,则说明越界点( x )就是floor
         *      两种情况:
         *          1.x.right == null  向右遍历到叶节点,上一个点肯定是floor了
         *          2.换运行分支,执行 return floor(x.left, key),通过返回null来判断,换分支的点是floor
         *
         *  ps:换运行分支前执行 t = floor(x.right, key),
         *      之后 return floor(x.left, key) 把结果返回到最近一层的 t
         *      通过返回null 来确定floor,然后通过 t = floor(x.right, key) 把值传递回去
         *      (此时 t 有值,所以 t 被不断赋值给上一层的 t)
         */
        Node t = floor(x.right, key); //同过t 实现关键点执行语句的切换
        if (t != null) return t;
        else return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to ceiling() is too small");
        else           return x.key;
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {
        if(key == null) return null;
        int compare = key.compareTo(x.key);
        if (compare == 0) return x;
        if (compare > 0)  return ceiling(x.right, key);
        Node t = ceiling(x.left, key);//左往右"越界"
        if (t != null) return t;
        else           return x;
    }

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table. 
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    // the key of rank k in the subtree rooted at x
    private Node select(Node x, int k) {//索引 k 从 0 开始
        int i = k - size(x.left); //ps:实际上i = (k+1) - (size(x.left) + 1)    size(x.left) + 根节点
        if      (i < 0) return select(x.left, k);
        else if (i > 0) return select(x.right, i-1);
        else            return x;
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int compare = key.compareTo(x.key);
        if      (compare < 0) return rank(key, x.left);
        else if (compare > 0) return size(x.left) + 1 + rank(key, x.right);
        else                  return size(x.left);
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

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.n != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
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
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }



    /*********************************************************************
     * 测试方法
     * *******************************************************************/

    public static void main(String[] args) {
        RedBlackBST2<Integer, Integer> st = new RedBlackBST2<Integer, Integer>();
        for (int i = 0; i < 5; i++) { ;
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

