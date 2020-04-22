package sort;

public class Merge {
    private static Comparable[] aux;

    //自底向上实现排序,方法入口
    public static void sort(Comparable[] a){
        int length = a.length;
        if (a.length == 0){new ArrayIndexOutOfBoundsException();}
        aux = new Comparable[length];
        Comparable t;
        for (int lo = 0; lo < length; lo += 8) {
            for (int j = lo; j < Math.min(length, lo + 8); j++) {
                for (int i = j; i > lo; i--) {
                    if (a[i - 1].compareTo(a[i]) > 0) {
                        t = a[i];
                        a[i] = a[i - 1];
                        a[i - 1] = t;
                    }else break;
                }
            }
        }
        for (int i = 0; i < length; i++)
            aux[i] = a[i];
        int mid;
        int rear;
        boolean exchange = false;
        for (int sz = 8; sz < length; sz += sz) {
            exchange = !exchange;
            for (int lo = 0; lo < length - sz; lo += sz + sz) {
                mid =lo + sz -1;
                rear = Math.min(length - 1, mid + sz);
                if (a[mid].compareTo(a[mid + 1]) <= 0) continue;
                if (exchange)   mergeAToAux(a, lo, mid, rear);
                else            mergeAuxToA(a, lo, mid, rear);
            }
        }
        if (exchange)
            for (int i = 0; i < length; i++)
                a[i] = aux[i];
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
        for (int j = 1; j < 100; j++) {
            Integer[] a = new Integer[j];
            for (int i = 0; i < a.length; i++) {
                a[i] = 10-i;
            }
            Merge.sort(a);
            System.out.println(a[j-1]);
        }
//        Integer[] a = new Integer[100];
//        for (int i = 0; i < a.length; i++) {
//            a[i] = 10-i;
//        }
//        Merge.sort(a);
//        for (Integer integer : a) {
//            System.out.println(integer);
//        }
    }
}
