package collection.map;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Scanner;

public class SequentialSearchST<Key,Value> {

    private Node head;
    private int n;


    private class Node{
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }



    public SequentialSearchST() {

    }

    public void put(Key key ,Value value){
        for (Node t = head; t != null; t = t.next){
            if (t.key == key) {
                t.value = value;
                return;
            }
        }
        head = new Node(key, value, head);
        n++;
    }

    public Value get(Key key){
        for (Node t = head; t != null; t = t.next) {
            if (t.key == key)  return t.value;
        }
        return null;
    }

    public void delete(Key key ){
        put(key,null);
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return n;
    }

    public Key min(){
        return null;
    }


    public Key max(){
        return null;
    }

    public Key floor(Key key){
        return null;
    }

    public Key ceiling(Key key){
        return null;
    }
    public int rank(Key key){
        return 0;
    }
    public Key select(Key key){
        return null;
    }
    public void deleteMin(){
        delete(min());
    }

    public void deleteMax(){
        delete(max());
    }



    public Iterable<Key> keys(Key lo,Key hi){
        return null;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<Key>();
        for (Node x = head; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;

    }





    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        Scanner sc = new Scanner(System.in);
        String s1;
        for (int i = 0; i < 5; i++) {
            s1 = sc.next();
            String key = s1;
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }


}


