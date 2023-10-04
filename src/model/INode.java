package model;

public interface INode<K, V> {
    K getKey();

    V getValue();

    INode<K, V> getNext();

    void setNext(INode<K, V> nextNode);

    INode<K, V> getPrevious();

    void setPrevious(INode<K, V> previousNode);

    void setValue(V value);
}
