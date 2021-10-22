package dataStructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class RBTree<K extends Comparable<K>,V> implements TreeInterface<K,V>{

    private final static boolean BLACK = false;
    private final static boolean RED = true;

    private boolean leftRotate;
    private boolean rightRotate;
    private boolean leftThenRightRotate;
    private boolean rightThenLeftRotate;
    private RBNode<K,V> root;
    private String treeStructure;

    public RBTree(){
        root = null;
        leftRotate=false;
        rightRotate=false;
        leftThenRightRotate=false;
        rightThenLeftRotate=false;
        treeStructure="";
    }

    public RBNode<K,V> rotateLeft(RBNode<K,V> node){
        RBNode aux = node.getRight();
        RBNode aux2 = aux.getLeft();
        aux.setLeft(node);
        node.setRight(aux2);
        node.setParent(aux);
        if(aux2!=null){
            aux2.setParent(node);
        }
        return aux;
    }

    public RBNode<K,V> rotateRight(RBNode<K,V> node){
        RBNode aux = node.getLeft();
        RBNode aux2 = aux.getRight();
        aux.setRight(node);
        node.setLeft(aux2);
        node.setParent(aux);
        if(aux2!=null){
            aux2.setParent(node);
        }
        return aux;
    }

    public void insert(K key,V value){
        RBNode<K,V> node = new RBNode<>(key, value);
        if(root!=null){
            root=insert(root,node);
        }
        else{
            root=node;
            root.setColor(BLACK);
        }
    }

    public RBNode<K,V> insert(RBNode<K,V> root,RBNode<K,V> node){
        boolean flag = false;
        if(root==null){
            return node;
        }

        else if(node.compareTo(root) == -1) {
            root.setLeft(insert(root.getLeft(),node));
            root.getLeft().setParent(root);
            if(root!=this.root) {
                if(root.getColor()==RED && root.getLeft().getColor() ==RED){
                    flag = true; //RED RED COINCIDENCE
                }
            }
        }
        else {
            root.setRight(insert(root.getRight(),node));
            root.getRight().setParent(root);
            if(root!=this.root) {
                if(root.getColor() == RED && root.getRight().getColor() == RED) {
                    flag = true; //RED RED COINCIDENCE
                }
            }
        }
        //ROTATIONS
        if(leftRotate) {
            root = rotateLeft(root);
            root.setColor(BLACK);
            root.getLeft().setColor(RED);
            leftRotate = false;
        }
        else if(rightRotate) {
            root = rotateRight(root);
            root.setColor(BLACK);
            root.getRight().setColor(RED);
            rightRotate = false;
        }
        else if(rightThenLeftRotate) {
            root.setRight(rotateRight(root.getRight()));
            root.getRight().setParent(root);
            root = rotateLeft(root);
            root.setColor(BLACK);
            root.getLeft().setColor(RED);
            rightThenLeftRotate = false;
        }
        else if(leftThenRightRotate) {
            root.setLeft(rotateLeft(root.getLeft()));
            root.getLeft().setParent(root);
            root = rotateRight(root);
            root.setColor(BLACK);
            root.getRight().setColor(RED);
            leftThenRightRotate = false;
        }
        //RED RED conflict
        if(flag) {
            if(root.getParent().getRight() == root) {
                if(root.getParent().getLeft()==null || root.getParent().getLeft().getColor()==BLACK) {
                    if(root.getLeft()!=null && root.getLeft().getColor()==RED) {
                        rightThenLeftRotate = true;
                    }
                    else if(root.getRight()!=null && root.getRight().getColor()==RED){
                        leftRotate = true;
                    }
                }
                else{
                    root.getParent().getLeft().setColor(BLACK);
                    root.setColor(BLACK);
                    if(root.getParent()!=this.root) {
                        root.getParent().setColor(RED);
                    }
                }
            }
            else {
                if(root.getParent().getRight()==null || root.getParent().getRight().getColor()==BLACK) {
                    if(root.getLeft()!=null && root.getLeft().getColor()==RED) {
                        rightRotate = true;
                    }
                    else if(root.getRight()!=null && root.getRight().getColor()==RED) {
                        leftThenRightRotate = true;
                    }
                }
                else {
                    root.getParent().getRight().setColor(BLACK);
                    root.setColor(BLACK);
                    if(root.getParent()!=this.root) {
                        root.getParent().setColor(RED);
                    }
                }
            }
            flag = false;
        }
        return(root);
    }

    public V searchElement(K key){
        RBNode<K,V> element = searchElement(root,key);
        if(element!=null){
            return element.getValue();
        }
        return null;
    }

    @Override
    public void remove(K key) {
        return;
    }

    public RBNode<K,V> search(K key){
        return searchElement(root,key);
    }

    public RBNode<K,V> searchElement(RBNode<K,V> node,K key){
        if(node==null){
            return node;
        }
        else{
            K aux = node.getKey();
            if(aux.equals(key)){
                return node;
            }
            else{
                if(key.compareTo(aux)>0){
                    return searchElement(node.getLeft(),key);
                }
                else{
                    return searchElement(node.getRight(),key);
                }
            }
        }
    }


    public void inorderTraversal(RBNode<K,V> node) {
        if(node!=null) {
            inorderTraversal(node.getLeft());
            treeStructure += node.getValue().toString();
            inorderTraversal(node.getRight());
        }
    }


    /*
    public ArrayList<Long> rangeSearch(RBNode<K,V> node,Double ){
        if(node!=null && node.getKey()) {
            inorderTraversal(node.getLeft());
            treeStructure += node.getValue().toString();
            inorderTraversal(node.getRight());
        }
    }
     */

    public LinkedList<V> searchFromARange(K min,K max){
        LinkedList<V> newLinkedList = new LinkedList<>();
        searchFromARange(this.search(max),newLinkedList,min);
        return newLinkedList;
    }

    public void searchFromARange(RBNode<K,V> node,LinkedList<V> ll,K min){
        if(node!=null && node.getKey().compareTo(min) > 0) {
            searchFromARange(node.getRight(),ll,min);
            ll.addAll((Collection<? extends V>) node.getValue());
            searchFromARange(node.getLeft(),ll,min);
        }
    }


    public void inorderTraversal() {
        treeStructure="";
        inorderTraversal(this.root);
    }


    public RBNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(RBNode<K, V> root) {
        this.root = root;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }
}
