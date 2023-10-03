package model;

public interface Stackable<T> {
    public void push(T value);
    public T top();
    public void pop();

    public boolean isEmpty();





}
