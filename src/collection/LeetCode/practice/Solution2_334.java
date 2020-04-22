package collection.LeetCode.practice;

public class Solution2_334 {
    public boolean lengthOfLIS(int[] nums) {
        if(nums.length < 3) return false;
        int i1 = nums[0];
        int i2 = 0;
        boolean two = false;
        for (int i = 1; i < nums.length; i++) {
            if (two && nums[i] > i2) return true;
            if (nums[i] > i1) {
                two = true;
                i2 = nums[i];
            } else {
                i1 = nums[i];
            }
        }
        return false;
    }
}
