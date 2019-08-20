public class MinPQ extends MaxPQ {
    public MinPQ(int capacity) {
        super (capacity);
    }

    @Override
    public boolean less(int i, int j) {
        return super.less(j, i);
    }
}
