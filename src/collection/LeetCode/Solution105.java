package collection.LeetCode;
import java.util.*;

public class Solution105 {
    int[] pre;
    Map<Integer, Integer> map = new HashMap<>();
    private int preNum = 0;
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = inorder.length;
        if(len == 0) return null;
        pre = preorder;
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(0, len - 1);
    }

    public TreeNode buildTree(int lo, int hi) {
        if (lo > hi) return null;
        TreeNode node = new TreeNode(pre[preNum]);
        Integer index = map.get(pre[preNum]);
        preNum++;
        node.left = buildTree(lo, index - 1);
        node.right = buildTree(index + 1, hi);
        return node;
    }



    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        Solution105 solution105 = new Solution105();
        TreeNode node = solution105.buildTree(preorder, inorder);
        System.out.println(node);
    }


}

