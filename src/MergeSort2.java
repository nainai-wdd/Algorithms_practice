
public class MergeSort2 {
    private static Comparable[] aux;

    //自底向上实现排序,方法入口
    public static void sort(Comparable[] a, int length){
        if (a.length == 0){new ArrayIndexOutOfBoundsException();}
        aux = new Comparable[length];
        int k = 2;
        int lastHead = 0;
        for (; k < length; k = k+k){
            for (int i = 0; i < length/k; i++) {
                lastHead = i*k;
                merge(a, lastHead, lastHead + (k-1)/2, lastHead+k-1);
            }
            if (lastHead+k != length) {
                int mid = lastHead + k + (length - lastHead - k - 1) / 2;
                merge(a, lastHead + k, mid, length-1);
            }
        }
        if ( (k/2) != length){
            merge(a,0,k/2 -1, length-1);
        }
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
        Integer[] a = new Integer[1];
        for (int i = 0; i < a.length; i++) {
            a[i] = 10-i;
        }
        MergeSort2.sort(a,a.length);
        for (Integer integer : a) {
            System.out.println(integer);
        }
    }
}
