package dataStructures;

public class LinkedListNode<K extends Comparable<K>,V> extends Node<K,V>{
    private LinkedListNode<K,V> next;
    private LinkedListNode<K,V> prev;
    public LinkedListNode(K key, V value) {
        super(key, value);
        next=null;
        prev=null;
    }

    public LinkedListNode<K, V> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<K, V> next) {
        this.next = next;
    }

    public LinkedListNode<K, V> getPrev() {
        return prev;
    }

    public void setPrev(LinkedListNode<K, V> prev) {
        this.prev = prev;
    }
}
