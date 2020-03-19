package collection.pq;

    public class OrderListPQ<Item> {
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
        }

        public Item delMax(){
            Node<Item> max = head;
            Node<Item> nt = head;
            while(nt != null) if (less(max,nt)) max = nt;
            return max.item;
        }

        private boolean less(Node<Item> v, Node<Item> w) {
            return ((Comparable<Item>) v.item).compareTo(w.item) < 0;
        }

        public static void main(String[] args) {
            ArrayPQ<Comparable> arrayPQ = new ArrayPQ<>(3);
            for (int i = 0; i < 10; i++) {
                arrayPQ.insert(i);
            }
            int n = arrayPQ.size();
            for (int i = 0; i < n; i++) {
                Comparable comparable = arrayPQ.delMax();
                System.out.println(comparable);
            }
        }
    }

