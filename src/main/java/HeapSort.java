import java.util.Arrays;

public class HeapSort {
    int[] values = null;
    public HeapSort(MaxPQ h) throws QueueEmptyException{
        if (h.isEmpty()) throw new QueueEmptyException();
        values = new int[h.size()];
        for (int i=0; i<values.length; i++) {
            values[i] = h.popTop();
        }
    }

    public int[] topK(int k) {
        return Arrays.copyOfRange(this.values, 0, k);
    }

    public int top() {
        return this.values[0];
    }

    public int[] getValues() {
        return values;
    }

    public int size() {
        return this.values.length;
    }
}
