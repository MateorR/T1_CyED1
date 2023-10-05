package implementations.interfaces;

public interface Stackable<T> {
    public void push(T value);
    public T top();
    public T pop();

    public boolean isEmpty();





}
