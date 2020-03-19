package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Insert {
    //算法主入口
    public static void sort(Comparable[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j >0 && less(a[j],a[j-1]); j--){
                exch(a,j,j-1);
            }
        }
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
        assert isSorted(a);
        show(a);
    }
}