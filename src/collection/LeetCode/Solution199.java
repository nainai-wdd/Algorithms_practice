package collection.LeetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution199 {
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    private int high = 0;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if(root == null) return list;
        dfs(list, root, 0);
        return list;
    }
    public void dfs(List<Integer> list, TreeNode x ,int h){
        if (h >= high) {list.add(x.val); high++;}
        if (x.right != null)   dfs(list, x.right, h + 1);
        if (x.left != null)    dfs(list, x.left, h + 1);
    }
}
