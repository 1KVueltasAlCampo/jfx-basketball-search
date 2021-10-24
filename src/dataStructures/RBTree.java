package dataStructures;

import java.util.ArrayList;

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

        else if(node.compareTo(root) == 1) {
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

    public ArrayList<V> searchElement(K key){
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
                if(key.compareTo(aux)<0){
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
            String temp = node.getValue().toString();
            treeStructure += temp.substring(1,temp.length()-1);
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

    /*
    public ArrayList<V> searchFromARange(K min,K max){
        ArrayList<V> newArrayList = new ArrayList<>();
        RBNode<K,V> aux = this.search(max);
        searchFromARange(aux,newArrayList,min);
        return newArrayList;
    }

    public void searchFromARange(RBNode<K,V> node,ArrayList<V> ll,K min){
        if( node != null && node.getKey().compareTo(min) >= 0){
            helpSearchFromARange(node.getParent(),ll,min);
        }
    }

    public void helpSearchFromARange(RBNode<K,V> node,ArrayList<V> ll,K min){
        if(node!=null && node.getKey().compareTo(min) >= 0) {
            helpSearchFromARange(node.getRight(),ll,min);
            ll.addAll(node.getValue());
            helpSearchFromARange(node.getLeft(),ll,min);
        }
    }
    */

    public ArrayList<V> searchByRange(K min,K max){
        ArrayList<V> aL = new ArrayList<>();
        searchByRange(root,aL,min,max);

        return aL;
    }

    public void searchByRange(RBNode<K,V> node, ArrayList<V> ll, K min, K max) {
        if (node == null) {
            return;
        }
        /* Since the desired o/p is sorted, recurse for left subtree first
         If root->data is greater than k1, then only we can get o/p keys
         in left subtree */
        if (node.getKey().compareTo(max) < 0) { //k1 < node.data
            searchByRange(node.getRight(),ll, min, max);
        }

        /* if root's data lies in range, then prints root's data */
        if (node.getKey().compareTo(max) <= 0 && node.getKey().compareTo(min) >= 0) { //k1 <= node.data && k2 >= node.data
            ll.addAll(node.getValue());
        }

        /* recursively call the right subtree  */
        searchByRange(node.getLeft(),ll, min, max);
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
