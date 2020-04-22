package collection.LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    private void dfs(Node root) {
        if(root == null) return;
        if(root.left != null && root.right != null)
            root.left.next = root.right;
        if (root.right != null)
            root.right.next = getNext(root);
        else if (root.left != null)
            root.left.next = getNext(root);
        dfs(root.right);
        dfs(root.left);

    }
    private Node getNext(Node h){
        h = h.next;
        while(h != null){
            if      (h.left != null)  return h.left;
            else if (h.right != null) return h.right;
            else h = h.next;
        }
        return null;
    }
}

