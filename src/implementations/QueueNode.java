package model;

public class QueueNode<T> {
    private QueueNode<T> behind;

    private T value;

    public QueueNode(T value) {
        behind = null;
        this.value = value;
    }

    public QueueNode<T> getBehind() {
        return behind;
    }

    public void setBehind(QueueNode<T> behind) {
        this.behind = behind;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
