

public class MaxPQ extends PriorityQueue {
    int[] pq = null;

    public MaxPQ(int capacity) {
        super(capacity);
        pq = new int[capacity+1];
    }

    void exchange(int i, int j) {
        int temp = this.pq[j];
        this.pq[j] = this.pq[i];
        this.pq[i] = temp;
    }

    private void swim(int k) {
        while (k>1 && this.less(k/2, k)) {
            this.exchange(k, k/2);
            k /= 2;
        }
    }

    private void sink(int k) {
        int j;
        while (2*k <= this.size()) {
            j = 2*k;
            if (j < this.size() && this.less(j, j+1)) j++;
            if (!this.less(k, j)) break;
            this.exchange(j, k);
            k = j;
        }
    }

    @Override
    public void insert(int x) throws QueueFullException {
        if (this.n == capacity) throw new QueueFullException();
        this.pq[++this.n] = x;
        this.swim(this.n);
    }

    @Override
    public int top() throws QueueEmptyException {
        if (this.isEmpty()) throw new QueueEmptyException();
        return this.pq[1];
    }

    @Override
    public int popTop() throws QueueEmptyException {
        int top = this.top();
        this.exchange(1, this.n--);
        this.sink(1);
        return top;
    }

    @Override
    public boolean less(int i, int j) {
        return this.pq[i] < this.pq[j];
    }

}
