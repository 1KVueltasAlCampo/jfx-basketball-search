package dataStructures;

import java.util.ArrayList;
import java.util.Collection;

public class Node<K,V> {
    protected K key;
    ArrayList<V> value;

    public Node(K key,V value){
        this.key=key;
        this.value=new ArrayList<>();
        this.value.add(value);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public ArrayList<V> getValue() {
        return value;
    }

}
