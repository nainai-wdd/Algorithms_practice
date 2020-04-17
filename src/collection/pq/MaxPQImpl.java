package collection.pq;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;


//最大堆
public class MaxPQImpl<Key> implements Iterable<Key>{
    private Key[] pq;
    private int N;
    private Comparator<Key> comparator;

    public MaxPQImpl(int initCapacity) {
        pq = (Key[])new Object[initCapacity+1];
        N = 0;
    }

    public MaxPQImpl(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[N + 1];
        for (int i = 0; i < N; i++)
            pq[i+1] = keys[i];
        for (int k = N/2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }


    public Key max(){
        return pq[1];
    }
    //弹出最大值
    public Key delMax(){
        if (N <= 0) throw new NoSuchElementException("Priority queue underflow");
        Key key = pq[1];
        pq[1] = pq[N];
        sink(1);
        pq[N] = null;
        N--;
        return key;
    }

    //插入
    public void insert(Key e){
        N++;
        if ( N >= pq.length){
            this.expansion();
        }
        pq[N] = e;
        swim(N);
    }

    //上浮
    public  void swim(int n){
        while( n > 1 && less(n/2, n)){
            exch(n/2,n);
            n = n/2;
        }
    }

    //下沉
    public  void sink(int n){
        int j = n;
        while ( j*2 <= N){
            j += j;
            if (j+1 < N && less(j, j+1)) j++;
            if ( less(j,n)) break;
            exch(n,j);
            n = j;
        }
    }

    //扩容
    private void expansion() {
        Key[] es = (Key[])new Object[N * 3];
        for (int i = 0; i < pq.length; i++) {
            es[i] = pq[i];
        }
        pq = es;
    }

    //交换
    private  void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    //less
    private  boolean less(int v, int w){
        if (comparator == null) {
            return ((Comparable<Key>) pq[v]).compareTo(pq[w]) < 0;
        }
        else {
            return comparator.compare(pq[v], pq[w]) < 0;
        }
    }
    private int size() {
        return N;
    }

    private boolean isMaxHeap() {
        for (int i = 1; i <= N; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = N+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }
    //判断该节点下的树是否是最大堆
    private boolean isMaxHeapOrdered(int i) {
        if (i > N) return true;
        int left = i*2;
        int right = left+1;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }


    @Override
    public Iterator<Key> iterator() {
        Key[] es = (Key[])new Object[N+1];
        for (int i = 0; i < pq.length; i++) {
            es[i] = pq[i];
        }

        Iterator iterator = new Iterator() {
            private int n = N;
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Object next() {
                return pq[n--];
            }
        };
        return iterator;
    }

    public static void main(String[] args) {
        Comparable[] a = new Integer[10000];
        for (int i = 5000; i < a.length; i++) {
            a[i] =22;
        }
        for (int i = 0; i < 5000; i++) {
            a[i] = i;
        }
        //打乱数组
        StdRandom.shuffle(a);
        MaxPQImpl<Comparable> integers = new MaxPQImpl<>(a);
        boolean maxHeap = integers.isMaxHeap();
        System.out.println(maxHeap);
        Comparable max = integers.max();


    }



}
