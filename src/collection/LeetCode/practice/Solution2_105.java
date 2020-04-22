package collection.LeetCode.practice;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class Solution2_105 {
    private int[] pre;
    private int curr = 0;
    private Map<Integer, Integer> map = new HashMap<>();
    public class TreeNode { int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return help(0, inorder.length - 1);
    }

    private TreeNode help(int lo, int hi) {
        if (lo > hi) return null;
        int r = pre[curr];
        curr++;
        Integer mid = map.get(r);
        TreeNode node = new TreeNode(r);
        node.left = help(lo, mid - 1);
        node.right = help(mid + 1, hi);
        return node;
    }
}
