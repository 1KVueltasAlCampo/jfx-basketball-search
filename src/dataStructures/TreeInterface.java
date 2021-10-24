package dataStructures;

import java.util.ArrayList;

public interface TreeInterface<K,V> {
    public void insert(K key,V value);
    public ArrayList<V> searchElement(K key);
    public void remove(K key);
}
