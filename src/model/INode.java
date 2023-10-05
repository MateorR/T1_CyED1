package model;

public interface INode<K, V> {
    K getKey();

    V getValue();

    Node<K, V> getNext();

    void setNext(Node<K, V> nextNode);

    Node<K, V> getPrevious();

    void setPrevious(Node<K, V> previousNode);

    void setValue(V value);
}
