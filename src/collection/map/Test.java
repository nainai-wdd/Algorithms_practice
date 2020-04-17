package collection.map;

import edu.princeton.cs.algs4.LinearProbingHashST;

import java.util.*;

public class Test {
    class Test1{}
    public static void main(String[] args) {
        Test1 test1 = new Test().new Test1();
        Test1 test11 = new Test().new Test1();
        Test1 test12 = new Test().new Test1();
        System.out.println("test1: "+test1);
        System.out.println("test1: "+test1.hashCode());
        System.out.println("test1: "+test11);
        System.out.println("test11: "+test11.hashCode());
        System.out.println("test1: "+test12);
        System.out.println("test12: "+test12.hashCode());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), i);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey()+": ");
            System.out.println(entry.getValue());
        }
        TreeMap<Integer, Integer> map1 = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            map1.put(10-i,i+100);
        }
        map1.forEach((o1,o2)->{
            System.out.print(o1+":");
            System.out.println(o2);
        });
    }
}
