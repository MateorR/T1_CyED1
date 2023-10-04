package model;

public interface Prioritable<T> {
    public T front();

    public T back();
    public boolean isEmpty();

    public void insert(T elem);

    public T extractMax();

    public T extractMin();

    public void increaseKey(int i, T key);

    public void decreaseKey(int i, T key);

    public void maxHeapify(int i);

    public void minHeapify(int i);



}
