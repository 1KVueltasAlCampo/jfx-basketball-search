package dataStructures;

public class RBNode<K extends Comparable<K>,V> extends Node<K, V> {

    private boolean color;

    private RBNode<K,V> parent;
    private RBNode<K,V> left;
    private RBNode<K,V> right;

    public RBNode(K key, V value) {
        super(key,value);
        color = true; //Red
        parent=null;
        left=null;
        right=null;
    }


    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public RBNode<K, V> getParent() {
        return parent;
    }

    public void setParent(RBNode<K, V> parent) {
        this.parent = parent;
    }

    public RBNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(RBNode<K, V> left) {
        this.left = left;
    }

    public RBNode<K, V> getRight() {
        return right;
    }

    public void setRight(RBNode<K, V> right) {
        this.right = right;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int compareTo(RBNode<K,V> otherNode) {
        int aux = this.key.compareTo(otherNode.getKey());
        if(aux > 0){
            return -1;
        }
        else if(aux == 0){
            return 0;
        }
        else{
            return 1;
        }
    }
}
