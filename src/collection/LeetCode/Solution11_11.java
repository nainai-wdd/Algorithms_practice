package collection.LeetCode;

import java.util.*;

public class Solution11_11 {
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, LinkedList<Integer>> map = new HashMap<>();
        int len = words.length;
        for (int i = 0; i < len; ++i) {
            if (!map.containsKey(words[i])) {
                LinkedList<Integer> indexs = new LinkedList<>();
                indexs.add(i);
                map.put(words[i], indexs);
            } else {
                map.get(words[i]).add(i);
            }
        }
        int res = Integer.MAX_VALUE;
        LinkedList<Integer> list1 = map.get(word1);
        LinkedList<Integer> list2 = map.get(word2);
        for (Integer w : list1) {
            for (Integer v : list2) {
                int abs = Math.abs(w - v);
                if (abs < res) res = abs;
            }
        }
        return res;
    }


}
