package collection.pq;

public class ListPQ<Item> {
    private  int N =0;
    private Node<Item> head = null;


    private class Node<Item>{
        private Item item;
        private Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public void insert(Item item){
        head = new Node<>(item,head);
        N++;
    }

    public Item delMax(){
        Node<Item> max = head;
        Node<Item> maxPre = head;
        Node<Item> nt = head;
        while(nt.next != null) {
            if (less(max,nt.next)) {
                max = nt.next;
                maxPre = nt;
            }
            nt = nt.next;
        }
        if (max == maxPre) head = head.next;
        else maxPre.next = max.next;
        N--;
        return max.item;
    }

    private boolean less(Node<Item> v, Node<Item> w) {
        return ((Comparable<Item>) v.item).compareTo(w.item) < 0;
    }

    public static void main(String[] args) {
        ListPQ<Comparable> listPQ = new ListPQ<>();
        for (int i = 0; i < 10; i++) {
            listPQ.insert(10-i);
        }
        int n = listPQ.size();
        for (int i = 0; i < n; i++) {
            Comparable comparable = listPQ.delMax();
            System.out.println(comparable);
        }
    }

    private int size() {
        return N;
    }

}
