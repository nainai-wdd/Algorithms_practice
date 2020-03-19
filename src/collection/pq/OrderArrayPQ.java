package collection.pq;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderArrayPQ <Key> implements Iterable<Key>{

        private Key[] pq;
        private int N;
        private Comparator<Key> comparator = null;

        public OrderArrayPQ(int initCapacity) {
            pq = (Key[])new Object[initCapacity+1];
            N = 0;
        }

        public OrderArrayPQ(Key[] keys) {
            N = keys.length;
            pq = (Key[]) new Object[N];
            for (int i = 0; i < N; i++)
                pq[i] = keys[i];
        }

        //插入
        public void insert(Key e){
            if (N >= pq.length) expansion();
            pq[N] = e;
            for (int i = N;i > 0 &&less(N,N-1);i--) exch(N,N-1);
            N++;
        }


    //弹出最大值
        public Key delMax(){
            if (N == 0) throw new NoSuchElementException();
            Key max = pq[N-1];
            pq[N-1] = null;
            N--;
            return max;
        }


        //扩容
        private void expansion() {
            Key[] es = (Key[])new Object[N * 4];
            for (int i = 0; i < pq.length; i++) {
                es[i] = pq[i];
            }
            pq = es;
        }

        private void exch(int i, int j) {
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

        public int size(){
            return N;
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
//        int N =10;
//        Comparable[] a = new Double[N];
//        for (int i1 = 0; i1 < N; i1++) {
//            a[i1] = StdRandom.uniform();
//        }
//        //打乱数组
//        StdRandom.shuffle(a);
            OrderArrayPQ<Comparable> arrayPQ = new OrderArrayPQ<>(3);
            for (int i = 0; i < 10; i++) {
                arrayPQ.insert(i);
            }
            int n = arrayPQ.size();
            for (int i = 0; i < n; i++) {
                Comparable comparable = arrayPQ.delMax();
                System.out.println(comparable);
            }
        }
}


