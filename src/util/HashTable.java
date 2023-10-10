package util;

import java.util.ArrayList;

public class HashTable<K, V> implements IHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private final ArrayList<HashNode<K, V>> table;
    private int size;

    public HashTable() {
        this.table = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            this.table.add(null);
        }
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        int index = getHash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (table.get(index) == null) {
            table.set(index, newNode);
        } else {
            HashNode<K, V> current = table.get(index);
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public V get(K key) {
        int index = getHash(key);
        HashNode<K, V> current = table.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getHash(key);
        HashNode<K, V> current = table.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public void remove(K key) {
        int index = getHash(key);
        HashNode<K, V> current = table.get(index);
        HashNode<K, V> prev = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                } else {
                    table.set(index, current.getNext());
                }
                size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    @Override
    public int length() {
        return size;
    }

    private int getHash(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % table.size());
    }

    public V[] values() {
        ArrayList<V> values = new ArrayList<>();
        for (HashNode<K, V> node : table) {
            while (node != null) {
                values.add(node.getValue());
                node = node.getNext();
            }
        }
        return (V[]) values.toArray();
    }

    public boolean isEmpty() {
        return size == 0;
    }
}