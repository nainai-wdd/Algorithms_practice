package collection.LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution110 {
    private boolean result = true;
     static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        dfs(root);
        return result;
    }
    private int dfs(TreeNode x){
        if (!result) return -1;
        int hl = 0, hr = 0;
        if (x.left != null) hl = dfs(x.left) + 1;
        if (x.right != null) hr = dfs(x.right) + 1;
        if (Math.abs(hl - hr) > 1) result = false;
        return Math.max(hl, hr);
    }

    public static void main(String[] args) {
        Integer[] a = {null,1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5};
        int len = a.length;
        TreeNode[] treeNodes = new TreeNode[len];
        for (int i = 1; i < len; i++) {
            if (a[i] != null)
                treeNodes[i] = new TreeNode(a[i]);
        }
        for (int i = 1; i < len; i++) {
            if (2 * i < len) {
                treeNodes[i].left = treeNodes[2 * i];
            }
            if (2 * i + 1 < len) {
                treeNodes[i].right = treeNodes[2 * i + 1];
            }
        }
        Solution110 solution110 = new Solution110();
        boolean balanced = solution110.isBalanced(treeNodes[1]);
        System.out.println(balanced);
    }
}
