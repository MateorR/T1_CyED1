package model;

public class Stack<T> implements Stackable<T> {
    private StackNode<T> top;

    public Stack() {
        this.top = null;
    }

    @Override
    public void push(T value) {
        StackNode<T> node = new StackNode<T>(value);
        if (top != null) {
            node.setUnder(top);
        }
        top = node;

    }

    @Override
    public T top() {
        T value = null;
        if (top != null) {
            value = top.getValue();
        }
        return value;
    }

    @Override
    public void pop() {
        if (top != null) {
            if (top.getUnder() == null) {
                top = null;
            } else {
                top = top.getUnder();
            }
        }
    }

    @Override
    public boolean isEmpty() {
        boolean empty = false;
        if (top == null) {
            empty = true;
        }
        return empty;
    }
}

