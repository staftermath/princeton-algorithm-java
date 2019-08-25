import java.util.Arrays;

public class HeapSort<T extends Comparable> {
    T[] values = null;
    public HeapSort(MaxPQ h) throws QueueEmptyException{
        if (h.isEmpty()) throw new QueueEmptyException();
        values = (T[]) new Comparable[h.size()];
        for (int i=0; i<values.length; i++) {
            values[i] = (T) h.popTop();
        }
    }

    public T[] topK(int k) {
        return Arrays.copyOfRange(this.values, 0, k);
    }

    public T top() {
        return this.values[0];
    }

    public T[] getValues() {
        return values;
    }

    public int size() {
        return this.values.length;
    }
}
