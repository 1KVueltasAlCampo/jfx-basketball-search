package dataStructures;

public class AVLNode <K extends Comparable<K>,V> extends Node<K, V>{
    K key;
    int height;
    AVLNode left;
    AVLNode right;
    int index;

    public AVLNode(K key, V value) {
        super(key, value);
        height = 1;
    }
}
