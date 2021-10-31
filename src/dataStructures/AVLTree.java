package dataStructures;

import java.util.ArrayList;

public class AVLTree <K extends Comparable<K>,V> implements SearchStructuresInterface<K,V> {

    private AVLNode<K,V> root;
    private String treeStructure;

    public AVLTree(){
        root = null;
        treeStructure="";
    }
    public int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    public int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private AVLNode rightRotate(AVLNode<K,V> node) {
        AVLNode aux = node.getLeft();
        AVLNode aux2 = aux.getRight();
        aux.setRight(node);
        node.setLeft(aux2);

        //  Update heights
        node.setHeight(max(height(node.getLeft()),height(node.getRight())) + 1);
        aux.setHeight(max(height(aux.getLeft()),height(aux.getRight())) + 1);

        return aux;
    }

    public AVLNode leftRotate(AVLNode<K,V> node) { //x
        AVLNode aux = node.getRight();
        AVLNode aux2 = aux.getLeft();
        aux.setLeft(node);
        node.setRight(aux2);

        //  Update heights
        node.setHeight(max(height(node.getLeft()), height(node.getRight())) + 1);
        aux.setHeight(max(height(aux.getLeft()), height(aux.getRight())) + 1);

        return aux;
    }


    int getBalance(AVLNode N) {
        if (N == null){
            return 0;
        }
        return height(N.left) - height(N.right);
    }


    public AVLNode insert(AVLNode root,AVLNode node) {
        if (root == null) {
            return node;
        }

        if (root.compareTo(node) >= 0 ) {
            root.setLeft(insert(root.getLeft(),node));
        }
        else if (root.compareTo(node) <= 0) {
            root.setRight(insert(root.getRight(),node));
        }
        else {
            return root;
        }

        root.setHeight(1 + max(height(root.getLeft()), height(root.getRight())));

        int balance = getBalance(root);

        if (balance > 1 && root.compareTo(node) >= 1) {
            return rightRotate(root);
        }

        if (balance < -1 && root.getRight().compareTo(node) <= -1) {
            return leftRotate(root);
        }

        if (balance > 1 && root.getLeft().compareTo(node) <= -1) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }

        if (balance < -1 && root.getRight().compareTo(node) >= 1) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;

    }

    public void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }



    @Override
    public void insert(K key, V value) {
        AVLNode<K,V> newNode = new AVLNode<>(key,value);
        if(root!=null){
            root = insert(root,newNode);
        }
        else {
            root = newNode;
        }

    }

    @Override
    public ArrayList<V> searchElement(K key) {
        AVLNode<K,V> element = searchElement(root,key);
        if(element!=null){
            return element.getValue();
        }
        return null;
    }

    public AVLNode<K,V> searchElement(AVLNode<K,V> node,K key){
        if(node==null){
            return node;
        }
        else{
            K aux = node.getKey();
            if(aux.equals(key)){
                return node;
            }
            else{
                if(key.compareTo(aux)<0){
                    return searchElement(node.getLeft(),key);
                }
                else{
                    return searchElement(node.getRight(),key);
                }
            }
        }
    }

    @Override
    public ArrayList<V> searchByRange(K min,K max){
        ArrayList<V> aL = new ArrayList<>();
        searchByRange(root,aL,min,max);

        return aL;
    }

    public void searchByRange(AVLNode<K,V> node, ArrayList<V> ll, K min, K max) {
        if (node == null) {
            return;
        }
        if (node.getKey().compareTo(max) < 0) { //k1 < node.data
            searchByRange(node.getRight(),ll, min, max);
        }
        if (node.getKey().compareTo(max) <= 0 && node.getKey().compareTo(min) >= 0) { //k1 <= node.data && k2 >= node.data
            ll.addAll(node.getValue());
        }
        searchByRange(node.getLeft(),ll, min, max);
    }

    public AVLNode<K,V> search(K key){
        return searchElement(root,key);
    }


    public void inorderTraversal() {
        treeStructure="";
        inorderTraversal(this.root);
    }

    public void inorderTraversal(AVLNode<K,V> node) {
        if(node!=null) {
            inorderTraversal(node.getLeft());
            String temp = node.getValue().toString();
            treeStructure += temp.substring(1,temp.length()-1);
            inorderTraversal(node.getRight());
        }
    }

    @Override
    public void remove(K key) {

    }

    public AVLNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<K, V> root) {
        this.root = root;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }
}
