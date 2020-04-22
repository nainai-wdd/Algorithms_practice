package collection.LeetCode.greedyAlgorithm;

public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] tail = new int[nums.length];
        tail[0] = nums[0];
        int end = 0;
        for (int i = 1; i < nums.length; i++) {
            if (tail[end] < nums[i]) {
                end++;
                tail[end] = nums[i];
            }
            else {
                int left = 0;
                int right = end;
                int mid;
                while (left < right) {
                    mid = left + (right - left) >>> 1;
                    if (tail[mid] < nums[i])
                        left = mid + 1;
                    else
                        right = mid;
                }
                tail[left] = nums[i];
            }
        }
        return end + 1;
    }
}


