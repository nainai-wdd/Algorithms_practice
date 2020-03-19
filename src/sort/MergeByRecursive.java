package sort;

public class MergeByRecursive {
    private static Comparable[] aux;

    //递归实现排序,方法入口
    public static void sort(Comparable[] a){
        int length = a.length;
        aux = new Comparable[length];
        sort(a, 0,  length-1);
    }

    //中分递归
    private static void sort(Comparable[] a, int head, int rear){
        int mid = head + (rear-head)/2;
        if (head >= rear){return;}
        sort(a, head, mid);
         sort(a, mid + 1, rear);
        merge(a, head, mid, rear);
    }

    //归并两个有序部分
    private static void merge(Comparable[] a, int head, int mid, int rear ){
        int i = head, j = mid+1;

        for (int k = i; k < rear+1; k++){
            aux[k] = a[k];
        }

        for (int k = i; k < rear+1; k++){
            if (i > mid){ a[k] = aux[j]; j++; }
            else if (j > rear){ a[k] = aux[i]; i++; }
            else if (aux[i].compareTo(aux[j]) < 0){ a[k] = aux[i]; i++; }
            else { a[k] = aux[j]; j++;}
        }
    }

    public static void main(String[] args) {
        Integer[] a = {9,8,7,6,6,7,8,9};
        MergeByRecursive.sort(a);
        for (Integer integer : a) {
            System.out.println(integer);
        }
    }
}
