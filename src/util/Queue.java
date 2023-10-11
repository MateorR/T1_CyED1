package util;

public class Queue<T> implements Queueable<T> {
    private QueueNode<T> front;
    private QueueNode<T> back;

    public Queue() {
        front = null;
        back = null;
    }

    @Override
    public void enqueue(T value) {
        QueueNode<T> node = new QueueNode<T>(value);
        if (isEmpty()) {
            front = node;
        } else {
            back.setBehind(node);
        }
        back = node;
    }

    @Override
    public T peek() {
        T value = null;
        if (!isEmpty()) {
            value = front.getValue();
        }
        return value;
    }

    @Override
    public T dequeue() {
        T value = null;
        if (!isEmpty()) {
            value = front.getValue();
            if (front.getBehind() != null) {
                front = front.getBehind();
            } else {
                front = null;
                back = null;
            }
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int size() {
        int size = 0;
        if (!isEmpty()) {
            QueueNode<T> node = front;
            while (node != null) {
                size++;
                node = node.getBehind();
            }
        }
        return size;
    }

    public void enqueueTop(T value) {
        QueueNode<T> node = new QueueNode<T>(value);
        if (!isEmpty()) {
            node.setBehind(front);
        }
        front = node;
    }

    public void remove(T value) {
        if (isEmpty()) {
            QueueNode<T> node = front;
            QueueNode<T> prev = null;
            while (node != null) {
                if (node.getValue().equals(value)) {
                    if (prev == null) {
                        front = node.getBehind();
                    } else {
                        prev.setBehind(node.getBehind());
                    }
                    break;
                }
                prev = node;
                node = node.getBehind();
            }
        }
    }
}
