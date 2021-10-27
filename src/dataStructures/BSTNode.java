package dataStructures;

public class BSTNode<K extends Comparable<K>,V> extends Node<K, V>{

    private BSTNode rightNode;
    private BSTNode leftNode;

    public BSTNode(K key, V value){
        super(key, value);
        rightNode=null;
        leftNode=null;
    }

    public BSTNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BSTNode rightNode) {
        this.rightNode = rightNode;
    }

    public BSTNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BSTNode leftNode) {
        this.leftNode = leftNode;
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
