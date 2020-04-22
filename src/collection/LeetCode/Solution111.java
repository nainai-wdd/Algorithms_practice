package collection.LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution111 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        return bfs(queue);
    }
    private int bfs(Queue<TreeNode> queue){
        int h = 1,size;
        TreeNode x;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++){
                x = queue.remove();
                if(x.left == null && x.right == null) return h;
                if(x.left != null) queue.add(x.left);
                if(x.right != null) queue.add(x.right);
            }
            h++;
        }
        return -1;
    }
}
