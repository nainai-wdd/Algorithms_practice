package collection.LeetCode.greedyAlgorithm;

public class Solution334 {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int first = nums[0], second = 0;
        boolean two = false;
        first = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (first < nums[i]) {
                if (!two || nums[i] <= second) {
                    two = true;
                    second = nums[i];
                } else {
                    return true;
                }
            } else first = nums[i];

        }
        return false;
    }
    public static void main(String[] args) {
        Solution334 result334 = new Solution334();
        int[] a = {1,2,-10,-8,-7};
        result334.increasingTriplet(a);
    }
}
