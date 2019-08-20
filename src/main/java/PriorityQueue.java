
public abstract class PriorityQueue {
    int capacity;
    int n;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.n = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public abstract void insert(int x) throws QueueFullException ;

    public abstract int top() throws QueueEmptyException;

    public abstract int popTop() throws QueueEmptyException ;

    public abstract boolean less(int i, int j);

    abstract void exchange(int i, int j);

}

class QueueFullException extends Exception {
    QueueFullException(String msg) {
        super(msg);
    }

    QueueFullException() {
        super("The queue is full.");
    }
}


class QueueEmptyException extends Exception {
    QueueEmptyException(String msg) {
        super(msg);
    }

    QueueEmptyException() {
        super("The queue is empty.");
    }
}