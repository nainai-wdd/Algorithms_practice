package sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

public class Quick {
    //算法主入口
    public static void sort(Comparable[] a) {
        if (a.length == 0) return;
        //打乱数组
//        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    //算法主逻辑
    private static void sort(Comparable[] a, int lo, int hi){
        //切换到插入排序
        if (hi - lo < 8) {
            insertSort(a, lo, hi);
            return;
        }

//        int p = partition(a,lo,hi);
//        sort(a, lo, p - 1);
//        sort(a, p + 1, hi);
        //三取样切分（交换次数多）
        int lt = lo, i = lo + 1, gt = hi;
        //三分取样
        if (a[lo + 3].compareTo(a[hi]) > 0)     exch(a, lo + 3, hi);
        if (a[lo + 3].compareTo(a[lo]) > 0)     exch(a, lo + 3, hi);
        if (a[lo].compareTo(a[hi]) > 0)         exch(a, lo, hi);
        Comparable v = a[lo];
        while (i <= gt) {
            int comp = v.compareTo(a[i]);
            if (comp > 0) exch(a, i, gt--);
            else if (comp < 0) exch(a, i++, lt++);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void insertSort(Comparable[] a, int lo, int hi) {
        Comparable t;
        for (int i = lo; i < hi + 1; i++)
            for (int j = i; j > lo; j--)
                if (a[j - 1].compareTo(a[j]) > 0)
                    exch(a, j - 1, j);
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int lt = lo, gt = hi+1;
        //三分取样
        if (a[lo + 3].compareTo(a[hi]) > 0)     exch(a, lo + 3, hi);
        if (a[lo + 3].compareTo(a[lo]) > 0)     exch(a, lo + 3, hi);
        if (a[lo].compareTo(a[hi]) > 0)         exch(a, lo, hi);
        Comparable v = a[lo];
        while(true){
            while ( less(a[++lt],v));
            while ( less(v,a[--gt]));
            if (lt >= gt) break;
            exch(a,lt,gt);
        }
        exch(a,lo,gt);
        return gt;
    }

    //比较方法
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //交换元素方法
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //展示方法
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    //检测方法
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    //测试方式
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

        sort(a);
        assert (isSorted(a));
        show(a);
    }
}