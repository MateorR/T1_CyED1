package implementations.interfaces;

public interface Queueable<T> {
    public void enqueue(T value);
    public T front();
    public T back();
    public void dequeue();
    public boolean isEmpty();
}
