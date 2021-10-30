package dataStructures;

public class BSTNode<K extends Comparable<K>,V> extends Node<K, V>{

    private BSTNode<K,V> rightNode;
    private BSTNode<K,V> leftNode;
    private BSTNode<K,V> parent;

    public BSTNode(K key, V value){
        super(key, value);
        rightNode=null;
        leftNode=null;
        parent=null;
    }

    public BSTNode<K,V> getRightNode() {
        return rightNode;
    }

    public void setRightNode(BSTNode rightNode) {
        this.rightNode = rightNode;
    }

    public BSTNode<K,V> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BSTNode leftNode) {
        this.leftNode = leftNode;
    }

    public BSTNode<K,V> getParent() {
        return parent;
    }

    public void setParent(BSTNode parent) {
        this.parent = parent;
    }

    public int compareTo(BSTNode<K,V> otherNode) {
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
