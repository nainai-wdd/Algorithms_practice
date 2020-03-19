package collection.easyList;

import java.util.Iterator;
import java.util.function.Consumer;

public class EasyList<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> rear;
    private int n;

    public EasyList() {
        head = null;
        rear = null;
        n = 0;
    }


    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }


    public boolean isEmpty() {
        return head == null;
    }

    public void add(Item item){
        Node<Item> node = new Node<Item>();
        node.item = item;
        node.next = null;
        if (this.isEmpty()){
            head = node;
            rear = node;
        }else {
            rear.next = node;
            rear = node;
        }
        n++;
    }

    public Item get(int index){
        if (this.isEmpty()){new ArrayIndexOutOfBoundsException();}
        Node<Item> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public void delete(int index){
        if (this.isEmpty()){new ArrayIndexOutOfBoundsException();}
        Node<Item> node = head;
        for (int i = 0; i < index-1; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        n--;
    }

    public int size(){return n;}


    @Override
    public Iterator<Item> iterator() {
        return new EasyListIterator<Item>(head);
    }

    public class EasyListIterator<Item> implements Iterator{
        private Node<Item> current;

        public EasyListIterator(Node<Item> head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    @Override
    public void forEach(Consumer<? super Item> action) {
        Node<Item> current = head;
        while (current != null) {
            action.accept(current.item);
            current = current.next;
        }return;
    }

    public static void main(String[] args) {
        EasyList<Integer> integers = new EasyList<Integer>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        integers.forEach((s)-> System.out.println(s));

        integers.delete(5);
        for (int i = 0; i < 9; i++) {
            Integer integer = integers.get(i);
            System.out.println(integer);
        }
    }

}
