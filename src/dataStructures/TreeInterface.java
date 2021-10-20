package dataStructures;

public interface TreeInterface<K,V> {
    public void insert(K key,V value);
    public V searchElement(K key);
    public void remove(K key);
}
