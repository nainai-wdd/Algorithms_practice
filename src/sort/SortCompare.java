package sort;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

//执行一次的时间
public class SortCompare {
    private static double time(String alg, Comparable[] a){
        Stopwatch time = new Stopwatch();
        if (alg == "insert"){ Insert.sort(a); }
        if (alg == "select"){ Selection.sort(a);}
        if (alg == "merge"){Merge.sort(a);}
        if (alg == "mergeby"){ MergeByRecursive.sort(a);}
        if (alg == "quick"){ Quick.sort(a);}
        if (alg == "shell"){ Shell.sort(a);}
        return time.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        Integer[] b = new Integer[N];
        Random rand = new Random();
        for (int i = 0; i < T; i++) {
//            for (int i1 = 0; i1 < N; i1++) {
//                a[i1] = StdRandom.uniform();
//            }
            for (int i1 = 0; i1 < N; i1++) {
                b[i1] = rand.nextInt(100);
            }
            total = total+time(alg,b);
        }

        return total;

    }

    public static void main(String[] args) {
        int N = 16;
        int T = 2000000;
        double merge = timeRandomInput("merge", N, T);
        System.out.println("merge: "+merge);
        double mergeby = timeRandomInput("mergeby", N, T);
        System.out.println("mergeby: "+mergeby);
        double quick = timeRandomInput("quick", N, T);
        System.out.println("quick: "+quick);
        double shell = timeRandomInput("shell", N, T);
        System.out.println("shell: "+shell);
        double select = timeRandomInput("select", N, T);
        System.out.println("select: "+select);
        double insert = timeRandomInput("insert", N, T);
        System.out.println("insert: "+insert);

    }
}
