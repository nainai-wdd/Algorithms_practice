package sort;

public class Shell {
    //算法主入口
    public static void sort(Comparable[] a) {
        int N = a.length;
        int f = 1;
        while(f < N/3) f = f*3 + 1;
        while(f >= 1){
            for (int i = f; i < N; i++){
                for (int j = i; j >= f && less(a[j],a[j-f]); j = j-f){
                    exch(a,j,j-f);
                }
            }
            f = f/3 ;
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
        Comparable[] a = {1,2,6,2,32,41,5,76,8,93,3,2,3,7,6,};
        sort(a);
        assert(isSorted(a));
        show(a);
    }
}