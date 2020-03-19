package practice;

import java.math.BigInteger;
import java.util.*;

public class FandM {


    public static BigInteger f(int n) {
        if (n == 0) {
            return BigInteger.valueOf(1);}
        else if (n == 1) {
            return BigInteger.valueOf(1);
        } else if (n == 2) {
            return BigInteger.valueOf(2);
        } else if (n == 3) {
            return BigInteger.valueOf(4);
        } else if (n == 4) {
            return BigInteger.valueOf(7);
        }else {
            int mid = n/2;
            if (n%2 == 0) {
                BigInteger f = f(mid-1);
                BigInteger fm = fM(mid-1);
                BigInteger sum1 = f.multiply(f);//f(mid-1)*f(mid-1)
                BigInteger sum2 = fm.multiply(fm);//fm(mid-1)*fm(mid-1)
                BigInteger sum3 = fM(mid-2).multiply(f).multiply(BigInteger.valueOf(2));//fm(mid-2)*f(mid-1)*2

                return sum3.add(sum2).add(sum1);
            } else {
                BigInteger f = f(mid);
                BigInteger f_1 = f(mid - 1);
                BigInteger fm_1 = fM(mid-1);
                BigInteger sum1 = f.multiply(f);
                BigInteger sum2 = fM(mid-1).multiply(f(mid-1)).multiply(BigInteger.valueOf(2));
                BigInteger sum3 = fm_1.multiply(fm_1);

                return sum3.add(sum2).add(sum1);

            }
        }
    }
    public static BigInteger fM(int n){
        if (n == 0) {
            return BigInteger.valueOf(1);}
        else if (n == 1){
            return BigInteger.valueOf(2);}
        else if (n == 2){
            return BigInteger.valueOf(3);}
        else if (n == 3){
            return BigInteger.valueOf(5);}
        else {
            int mid = n/2;
            if (n%2 == 0) {
                BigInteger f = f(mid-1);
                BigInteger fm = fM(mid-1);
                BigInteger sum1 = f.multiply(fm);
                BigInteger sum2 = fm.multiply(fMM(mid-1));
                BigInteger sum3 = f.multiply(fMM(mid-2)).add(fM(mid-2).multiply(fm));

                return sum3.add(sum2).add(sum1);
            } else {
                BigInteger fm_1 = fM(mid-1);
                BigInteger fmm_1 = fMM(mid-1);
                BigInteger sum1 = f(mid).multiply(fM(mid));
                BigInteger sum2 = fm_1.multiply(fm_1).add(f(mid-1).multiply(fmm_1));
                BigInteger sum3 = fm_1.multiply(fmm_1);

                return sum3.add(sum2).add(sum1);

            }
        }
    }
    public static BigInteger fMM(int n){
        if (n == 0) {
            return BigInteger.valueOf(1);
        }
        else if (n == 1){
            return BigInteger.valueOf(2);
        }
        else if (n == 2){
            return BigInteger.valueOf(4);
        }
        else if (n == 3){
            return BigInteger.valueOf(7);
        }
        else  {
            int mid = n / 2;
            if (n%2 == 0) {
                BigInteger fm = fM(mid-1);
                BigInteger fmm = fMM(mid-1);
                BigInteger sum1 = fm.multiply(fm);
                BigInteger sum2 = fmm.multiply(fmm);
                BigInteger sum3 = fMM(mid-2).multiply(fm).multiply(BigInteger.valueOf(2));

                return sum3.add(sum2).add(sum1);
            } else {
                BigInteger fm = fM(mid);
                BigInteger fm_1 = fM(mid-1);
                BigInteger fmm_1 = fMM(mid-1);
                BigInteger sum1 = fm.multiply(fm);
                BigInteger sum2 = fmm_1.multiply(fm_1).multiply(BigInteger.valueOf(2));
                BigInteger sum3 = fmm_1.multiply(fmm_1);

                return sum3.add(sum2).add(sum1);
            }
        }
    }

    public static BigInteger fnn(int n){
        switch (n){
            case 1:return BigInteger.valueOf(1);
            case 2:return BigInteger.valueOf(2);
            case 3:return BigInteger.valueOf(4);
            case 4:return BigInteger.valueOf(7);
        }
        return fnn(n-1).add(fnn(n-2)).add(fnn(n-4));
    }

    public static BigInteger ffnn(int n){
        int N =30000;
        BigInteger[] integers = new BigInteger[30000];
        integers[0] = (BigInteger.valueOf(1));
        integers[1] = (BigInteger.valueOf(2));
        integers[2] = (BigInteger.valueOf(4));
        integers[3] = (BigInteger.valueOf(7));
        integers[4] = (BigInteger.valueOf(12));
        for (int i = 5; i < n; i++) {
            integers[i] = integers[i-1].add(integers[i-2]).add(integers[i-4]);
        }
        return integers[n-1];
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
//
//            long time1 = new Date().getTime();
//            BigInteger f = f(n);
//            long time2 = new Date().getTime();

            long time3 = new Date().getTime();
            BigInteger ffnn = ffnn(n);
            long time4 = new Date().getTime();


//            System.out.println("二分递归"+f+" : ");
//            System.out.println((time2-time1)+"毫秒 ");
            System.out.println("数组通项"+ffnn);
//            System.out.println((time4-time3)+"毫秒 ");
//            long time5 = new Date().getTime();
//            BigInteger fnn = fnn(n);
//            long time6 = new Date().getTime();
//
//            System.out.println("通项递归"+f+" : ");
//            System.out.println((time6-time5)+"毫秒 ");
        }
    }
}





