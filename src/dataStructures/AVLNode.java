package dataStructures;

public class AVLNode<K extends Comparable<K>,V> extends Node<K, V>{
    int height;
    AVLNode left;
    AVLNode right;
    int index;

    public AVLNode(K key, V value) {
        super(key, value);
        height = 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int compareTo(AVLNode<K,V> otherNode) {
        int aux = this.key.compareTo(otherNode.getKey());
        if(aux > 0){
            return 1;
        }
        else if(aux == 0){
            return 0;
        }
        else{
            return -1;
        }
    }
}
