package day3_13;

import edu.princeton.cs.algs4.SET;

import java.security.Key;
import java.util.*;
import java.util.function.Consumer;

public class EasyListMap<K,V> implements Map {

    private Node<K,V> head;
    private Node<K,V> rear;
    private int n;

    public EasyListMap() {
        head = new Node<K,V>();
        rear = head;
        n = 0;
    }


    private static class Node<K,V> {
        private K key;
        private V value;
        private Node<K,V> next;
    }


    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Node<K, V> current = head;
        while (current != null){
            if (current.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Node<K, V> current = head;
        while (current != null){
            if (current.value.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        Node<K,V> current = head;
        while(current != null){
            if (current.key.equals(key)){
                return current.value;
            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        Node<K,V> node = new Node<K,V>();
        node.key = (K)key;
        node.value = (V)value;
        node.next = node;
        if (this.isEmpty()){
            head = node;
            rear = node;
        }else {
            rear.next = node;
            rear = node;
        }
        n++;
        return node;
    }

    @Override
    public Object remove(Object key) {
        Node<K,V> current = head;
        while(current != null){
            if (current.next.key.equals(key)){
                Node<K, V> next = current.next;
                current.next = next.next;
                return next.key;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
        Set set = m.keySet();
        for (Object key : set) {
            Node<K,V> node = new Node<K,V>();
            node.key = (K)key;
            node.value = (V)m.get(key);
            node.next = null;
            if (this.isEmpty()){
                head = node;
                rear = node;
            }else {
                rear.next = node;
                rear = node;
            }
            n++;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        HashSet<Object> set = new HashSet<>();
        Node<K,V> current = head;
        while (current != null){
            set.add(current.key);
        }
        return set;
    }

    @Override
    public Collection values() {

        return null;
    }



    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    public void add(K key,V value){
        Node<K,V> node = new Node<K,V>();
        node.value = value;
        node.next = null;
        if (this.isEmpty()){
            head = node;
            rear = node;
        }else {
            rear.next = node;
            rear = node;
        }
        n++;
    }

    public int size(){return n;}




    public static void main(String[] args) {
        EasyListMap<String,Integer> map = new EasyListMap<String,Integer>();
        for (int i = 0; i < 10; i++) {
            map.put(Integer.valueOf(i).toString(),i);
        }
//        map.forEach((k,v)-> System.out.println(k+":"+v));

        for (Object key : map.keySet()) {
            Object value = map.get(key);
            System.out.println(key+":"+value);
        }
    }
}