package util;

public class Stack<T> implements Stackable<T> {
    private StackNode<T> top;

    public Stack() {
        this.top = null;
    }

    @Override
    public void push(T value) {
        StackNode<T> node = new StackNode<T>(value);
        if (isEmpty()) {
            node.setUnder(top);
        }
        top = node;

    }

    @Override
    public T top() {
        T value = null;
        if (isEmpty()) {
            value = top.getValue();
        }
        return value;
    }

    @Override
    public T pop() {
        T value = null;
        if (isEmpty()) {
            if (top.getUnder() == null) {
                value = top.getValue();
                top = null;
            } else {
                value = top.getValue();
                top = top.getUnder();
            }
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = false;
        if (top == null) {
            empty = true;
        }
        return !empty;
    }
}

