package model;

import java.util.ArrayList;

public class HashTable<K, V> implements IHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private final ArrayList<Node<K, V>> table;
    private int size;

    public HashTable() {
        this.table = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            this.table.add(null);
        }
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table.get(index) == null) {
            table.set(index, newNode);
        } else {
            Node<K, V> current = table.get(index);
            while (current.getNext() != null) {
                current = (Node<K, V>) current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = table.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = (Node<K, V>) current.getNext();
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> current = table.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = (Node<K, V>) current.getNext();
        }

        return false;
    }

    @Override
    public void remove(K key) {
        int index = getIndex(key);
        Node<K, V> current = table.get(index);
        Node<K, V> prev = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                } else {
                    table.set(index, (Node<K, V>) current.getNext());
                }
                size--;
                return;
            }
            prev = current;
            current = (Node<K, V>) current.getNext();
        }
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public int hash() {
        //TODO implement hash function
        return 0;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % table.size());
    }
}