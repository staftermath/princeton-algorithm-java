

public class UnorderedMaxPQ extends PriorityQueue {
    int[] pq = null;

    public UnorderedMaxPQ(int capacity) {
        super (capacity);
        this.pq = new int[capacity+1];
    }

    @Override
    public void insert(int x) throws QueueFullException{
        if (this.n >= this.capacity) throw new QueueFullException("Capacity is full");
        this.pq[++this.n] = x;
    }

    @Override
    void exchange(int i, int j) {
        int temp = this.pq[i];
        this.pq[i] = this.pq[j];
        this.pq[j] = temp;
    }

    private int getMaxIndex() throws QueueEmptyException {
        if (this.isEmpty()) throw new QueueEmptyException("Queue is empty");
        int maxIdx=0;
        for (int i=1; i<=this.n; i++) {
            if (this.less(maxIdx, i)) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    @Override
    public int top() throws QueueEmptyException {
        return this.pq[this.getMaxIndex()];
    }

    @Override
    public int popTop() throws QueueEmptyException {
        int maxIdx = this.getMaxIndex();
        this.exchange(maxIdx, this.n);
        return this.pq[this.n--];
    }

    @Override
    public boolean less(int i, int j) {
        return this.pq[i] < this.pq[j];
    }
}

