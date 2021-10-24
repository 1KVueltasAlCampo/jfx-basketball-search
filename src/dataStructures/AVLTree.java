package dataStructures;

import java.util.ArrayList;

public class AVLTree <K extends Comparable<K>,V> implements TreeInterface<K,V>{

    private AVLNode<K,V> root;

    public AVLTree(){
        root = null;
    }
    public int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    public int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;


        x.right = y;
        y.left = T2;


        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    public AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;


        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;


        return y;
    }


    int getBalance(AVLNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    /*
    public AVLNode insert(AVLNode node, K key, V value) {

        if (node == null) {
            return (new AVLNode(key, value));
        }

        if (node.key.compareTo(key) >= 1 ) {
            node.left = insert(node.left, key, value);
        }else if (node.key.compareTo(key) <= -1) {
            node.right = insert(node.right, key, value);
        }else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && node.key.compareTo(key) >= 1) {
            return rightRotate(node);
        }

        if (balance < -1 && node.right.key.compareTo(key) <= -1) {
            return leftRotate(node);
        }

        if (balance > 1 && node.left.key.compareTo(key) <= -1) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }


        if (balance < -1 && node.right.key.compareTo(key) >= 1) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    */

    public void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    @Override
    public void insert(K key, V value) {

    }

    @Override
    public ArrayList<V> searchElement(K key) {
        return null;
    }

    @Override
    public void remove(K key) {

    }
}
