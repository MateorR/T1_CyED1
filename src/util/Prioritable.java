package util;

public interface Prioritable<T> {
    public T front();

    public T back();

    public boolean isEmpty();

    public void insert(T elem);

    public T extractMax();

    public void increaseKey(int i, T key);

    public void decreaseKey(int i, T key);

    public void maxHeapify(int i);

    public void heapSort();
}
