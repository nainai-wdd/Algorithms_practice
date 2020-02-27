import static edu.princeton.cs.algs4.StdRandom.random;

public class test {
    public static void main(String[] args) {
        System.out.println(greatestCommon(1111111,1234567));
    }
    //最大公约数欧几里得算法
    public static  int greatestCommon(int p , int q){
        int r = p%q;

        if (r == 0){
            return q;
        }else {
            return greatestCommon(q,r);
        }
    }



}
