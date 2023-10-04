package model;

public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(K key, V value) {
        root = insertRecursive(root, key, value);
    }

    private Node<K, V> insertRecursive(Node<K, V> current, K key, V value) {
        if (current == null) {
            return new Node<>(key, value);
        }

        if (key.compareTo(current.getKey()) < 0) {
            current.setPrevious(insertRecursive(current.getPrevious(), key, value));
        } else if (key.compareTo(current.getKey()) > 0) {
            current.setNext(insertRecursive(current.getNext(), key, value));
        }

        return current;
    }

    public boolean containsKey(K key) {
        return containsKeyRecursive(root, key);
    }

    private boolean containsKeyRecursive(Node<K, V> current, K key) {
        if (current == null) {
            return false;
        }

        if (key.compareTo(current.getKey()) == 0) {
            return true;
        } else if (key.compareTo(current.getKey()) < 0) {
            return containsKeyRecursive(current.getPrevious(), key);
        } else {
            return containsKeyRecursive(current.getNext(), key);
        }
    }
}