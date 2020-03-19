package sort;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

//执行一次的时间
public class SortCompare {
    private static double time(String alg, Comparable[] a){
        Stopwatch time = new Stopwatch();
        if (alg == "insert"){ Insert.sort(a); }
        if (alg == "select"){ Selection.sort(a);}
        if (alg == "shell"){ Shell.sort(a);}
        if (alg == "merge"){Merge.sort(a);}
        if (alg == "mergeby"){ MergeByRecursive.sort(a);}
        if (alg == "quick"){ Quick.sort(a);}
        return time.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int i1 = 0; i1 < N; i1++) {
                a[i1] = StdRandom.uniform();
            }
            total = total+time(alg,a);
        }

        return total;

    }

    public static void main(String[] args) {
        int N = 1000000;
        int T = 4;
        double merge = timeRandomInput("merge", N, T);
        System.out.println("merge: "+merge);
        double mergeby = timeRandomInput("mergeby", N, T);
        System.out.println("mergeby: "+mergeby);
        double quick = timeRandomInput("quick", N, T);
        System.out.println("quick: "+quick);
        double shell = timeRandomInput("shell", N, T);
        System.out.println("shell: "+shell);
//        double select = timeRandomInput("select", N, T);
//        System.out.println("select: "+select);
//        double insert = timeRandomInput("insert", N, T);
//        System.out.println("insert: "+insert);

    }
}
