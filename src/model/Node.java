package model;

public class Node<K, V> implements INode<K, V> {
    private final K key;
    private V value;
    private INode<K, V> next;
    private INode<K, V> previous;

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

    @Override
    public INode<K, V> getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(INode<K, V> previousNode) {
        this.previous = previousNode;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }
}
