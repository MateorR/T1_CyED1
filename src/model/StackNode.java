package model;

public class StackNode<T> {
    private StackNode<T> under;
    private T value;
    
    public StackNode(T value) {
        this.value = value;
        under = null;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }

    public StackNode<T> getUnder() {
        return under;
    }

    public void setUnder(StackNode<T> under) {
        this.under = under;
    }
}
