package dataStructures;

import java.util.ArrayList;

public class LinkedList<K extends Comparable<K>,V extends Comparable<V>> implements SearchStructuresInterface<K, V>{
    private LinkedListNode<K,V> root;

    public LinkedList(){
        root=null;
    }

    @Override
    public void insert(K key,V value){
        if (root == null || root.getKey().compareTo(key) >= 0) {
            LinkedListNode<K,V> newNode = new LinkedListNode<>(key, value);
            newNode.setNext(root);
            root = newNode;
        }
        else{
            root=insert(root,key,value);
        }
    }

    public LinkedListNode<K,V> insert(LinkedListNode<K,V> node,K key,V value){
        if(node.getNext()!=null && node.getNext().getKey().compareTo(key)>= 0){
            return insert(node.getNext(),key,value);
        }
        else {
            LinkedListNode<K,V> newNode = new LinkedListNode<>(key,value);
            newNode.setNext(node.getNext());
            node.setNext(newNode);
            return root;
        }
    }

    @Override
    public ArrayList<V> searchElement(K key) {
        LinkedListNode<K,V> node = searchElement(root,key);
        if(node!=null){
            return node.getValue();
        }
        return null;
    }

    public LinkedListNode<K,V> searchElement(LinkedListNode<K,V> node,K key) {
        if(node==null||node.getKey().compareTo(key)==0){
            return node;
        }
        else {
            return searchElement(node.getNext(),key);
        }
    }

    public LinkedListNode<K,V> search(K key){
        return searchElement(root,key);
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public ArrayList<V> searchByRange(K min, K max) {
        ArrayList<V> ll = new ArrayList<>();
        searchByRange(root,ll,min,max);
        return ll;
    }

    public void searchByRange(LinkedListNode<K,V> node,ArrayList<V> ll,K min,K max){
        if(node!=null){
            if(node.getKey().compareTo(max) <= 0 && node.getKey().compareTo(min) >= 0){
                ll.addAll(node.getValue());
            }
            searchByRange(node.getNext(),ll,min,max);
        }

    }
}
