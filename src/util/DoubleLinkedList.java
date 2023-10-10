package util;

public class DoubleLinkedList<K, T> {
    private HashNode<K, T> head;
    private HashNode<K, T> tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public String add(K key, T value) {
        HashNode<K, T> newNode = new HashNode<>(key, value);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return "The node was added";
        }
        tail.setNext(newNode);
        newNode.setPrevious(tail);
        tail = newNode;
        size++;
        return "The node was added";
    }

    public String remove(K key) {
        HashNode<K, T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                if (currentNode == head) {
                    head = (HashNode<K, T>) currentNode.getNext();
                    head.setPrevious(null);
                } else if (currentNode == tail) {
                    tail = (HashNode<K, T>) currentNode.getPrevious();
                    tail.setNext(null);
                } else {
                    currentNode.getPrevious().setNext(currentNode.getNext());
                    currentNode.getNext().setPrevious(currentNode.getPrevious());
                }
                size--;
                return "The node was removed";
            }
            currentNode = (HashNode<K, T>) currentNode.getNext();
        }
        return "The node was not found";
    }

    public String swap(K key1, K key2) {
        HashNode<K, T> node1 = getNode(key1);
        HashNode<K, T> node2 = getNode(key2);
        if (node1 == null || node2 == null) {
            return "The nodes were not found";
        }
        T temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
        return "The nodes were swapped";
    }

    private HashNode<K, T> getNode(K key1) {
        HashNode<K, T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key1)) {
                return currentNode;
            }
            currentNode = (HashNode<K, T>) currentNode.getNext();
        }
        return null;
    }

    private String print() {
        StringBuilder stringBuilder = new StringBuilder();
        HashNode<K, T> currentNode = head;
        while (currentNode != null) {
            stringBuilder.append(currentNode.getValue());
            if (currentNode.getNext() != null) {
                stringBuilder.append("->");
            }
            currentNode = (HashNode<K, T>) currentNode.getNext();
        }
        return stringBuilder.toString();
    }

    private int getSize() {
        return size;
    }
}