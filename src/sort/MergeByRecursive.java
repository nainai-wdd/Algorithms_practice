package sort;

public class MergeByRecursive {
    private static Comparable[] aux;
    //递归实现排序,方法入口
    public static void sort(Comparable[] a){
        int length = a.length;
        if (length == 0) return;
        aux = new Comparable[length];
        sort(a, 0,  length-1, true);
    }

    //中分递归
    private static void sort(Comparable[] a, int head, int rear, boolean exchange){
        int mid = head + (rear-head >>> 1);
        if (rear - head < 8){
            insertSort(a,head,rear);
            return;
        }
        sort(a, head, mid, !exchange);
        sort(a, mid + 1, rear, !exchange);
        if (a[mid].compareTo(a[mid + 1]) <= 0) return;
        if (exchange)
            mergeAuxToA(a, head, mid, rear);
        else
            mergeAToAux(a, head, mid, rear);
    }

    private static void insertSort(Comparable[] a, int head, int rear) {
        Comparable t;
        for (int i = head; i < rear + 1; i++) {
            for (int j = i; j > head; j--) {
                if (a[j - 1].compareTo(a[j]) > 0) {
                    t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }else break;
            }
        }
        for (int i = head; i < rear + 1; i++)
            aux[i] = a[i];
    }

    //归并两个有序部分
    private static void mergeAuxToA(Comparable[] a, int head, int mid, int rear){
        int i = head, j = mid+1;
        for (int k = i; k < rear+1; k++){
            if (i > mid){ a[k] = aux[j]; j++; }
            else if (j > rear){ a[k] = aux[i]; i++; }
            else if (aux[i].compareTo(aux[j]) < 0){ a[k] = aux[i]; i++; }
            else { a[k] = aux[j]; j++;}
        }
    }
    //归并两个有序部分
    private static void mergeAToAux(Comparable[] a, int head, int mid, int rear){
        int i = head, j = mid+1;
        for (int k = i; k < rear+1; k++){
            if (i > mid){ aux[k] = a[j]; j++; }
            else if (j > rear){ aux[k] = a[i]; i++; }
            else if (a[i].compareTo(a[j]) < 0){ aux[k] = a[i]; i++; }
            else { aux[k] = a[j]; j++;}
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[180];
        for (int i = 0; i < a.length; i++) {
            a[i] = 10-i;
        }
        MergeByRecursive.sort(a);
        for (Integer integer : a) {
            System.out.println(integer);
        }
    }
}
