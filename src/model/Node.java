package model;

public class Node<K, V> implements INode<K, V> {
    private final K key;
    private final V value;
    private INode<K, V> next;

    public Node(K key, V value) {
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
    public INode<K, V> getNext() {
        return next;
    }

    @Override
    public void setNext(INode<K, V> nextNode) {
        this.next = nextNode;
    }
}
