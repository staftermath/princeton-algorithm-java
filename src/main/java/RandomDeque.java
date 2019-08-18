import java.util.Random;

public class RandomDeque extends Deque {
    Random random = null;

    public RandomDeque() {
        this(123);
    }

    public RandomDeque(int seed) {
        super ();
        this.random = new Random(seed);
    }

    public void setSeed(int seed) {
        this.random = new Random(seed);
    }

    public int sample() {
        if (this.empty()) throw new NullPointerException("Deque is empty");

        int idx = this.random.nextInt(this.size());
        return this.arr[this.head+idx];
    }
}
