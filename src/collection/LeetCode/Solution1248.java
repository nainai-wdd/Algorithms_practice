package collection.LeetCode;

public class Solution1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        if (nums == null) return 0;
        int sum = 0, count = 0;
        int[] map = new int[nums.length + 1];
        map[0] = 1;
        for (int i : nums) {
            sum += i&1;
            map[sum]++;
            if (sum >= k) count += map[sum - k];
        }
        return count;
    }
}
