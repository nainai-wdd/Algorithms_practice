package collection.graph;

import edu.princeton.cs.algs4.Stack;

import java.util.Queue;

public class EasyTree {
    private int n;
    private Node root;

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public EasyTree() {
        root = null;
        n = 0;
    }
    public void getNewTree() {
        root = getNode(100);
    }

    public Node getNode(int value) {
        Node node = new Node(value);
        n++;
        if (value > 10) {
            node.left = getNode(value / 4);
            node.right = getNode(value / 2);
        }
        return node;
    }

    public void show() {
        Node curr = null;
        Node prev = null;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            curr = stack.peek();
            if ( prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null)
                    stack.push(curr.left);
                else if (curr.right != null)
                    stack.push(curr.right);
            }
            else if (curr.left == prev){
                if (curr.right != null)
                    stack.push(curr.right);
            }
            else {
                System.out.println(curr.value);
                stack.pop();
            }
            prev = curr;
        }
    }



    public static void main(String[] args) {
        EasyTree easyTree = new EasyTree();
        easyTree.root = easyTree.new Node(100);
        easyTree.root.left = easyTree.new Node(50);
        easyTree.root.right = easyTree.new Node(80);
        easyTree.root.left.left = easyTree.new Node(10);
        easyTree.root.left.right = easyTree.new Node(40);
        easyTree.root.right.left = easyTree.new Node(60);
        easyTree.root.right.right = easyTree.new Node(70);
        easyTree.show();
    }

}
