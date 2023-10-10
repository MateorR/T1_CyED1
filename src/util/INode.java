package util;

public interface INode<K, V> {
    K getKey();

    V getValue();

    HashNode<K, V> getNext();

    void setNext(HashNode<K, V> nextNode);

    HashNode<K, V> getPrevious();

    void setPrevious(HashNode<K, V> previousNode);

    void setValue(V value);
}
