package collection.LeetCode;

public class Solution226 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
}
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode t;
        t = root.left;
        root.left = root.right;
        root.right = t;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
