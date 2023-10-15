package util;

public class HashNode<K, V> implements INode<K, V> {
    private final K key;
    private V value;
    private HashNode<K, V> next;
    private HashNode<K, V> previous;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public HashNode<K, V> getNext() {
        return next;
    }

    @Override
    public void setNext(HashNode<K, V> nextNode) {
        this.next = nextNode;
    }

    @Override
    public HashNode<K, V> getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(HashNode<K, V> previousNode) {
        this.previous = previousNode;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }
}
