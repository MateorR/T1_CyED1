package util;

public interface Queueable<T> {
    public void enqueue(T value);

    public T peek();

    public T dequeue();

    public boolean isEmpty();

    public int size();
}
