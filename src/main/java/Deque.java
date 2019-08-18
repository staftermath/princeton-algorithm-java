public class Deque {
    private int size = 0;
    private int[] arr = new int[4];
    private int head = 1;
    private int tail = this.size+this.head;

    public Deque() {}

    public boolean empty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public int first() {
        if (this.empty()) throw new NullPointerException("Deque is empty.");
        return this.arr[this.head];
    }

    public int last() {
        if (this.empty()) throw new NullPointerException("Deque is empty.");
        return this.arr[this.tail-1];
    }

    public void addFirst(int x) {
        this.size++;
        if (this.empty()) {
            this.arr[this.head] = x;
            this.tail++;
        } else {
            this.arr[--this.head] = x;
        }
        int current_buffer_size = this.arr.length;
        if (this.head == 0) {
            // double array and add buffer slots in front
            int[] new_arr = new int[current_buffer_size*2];
            for (int i=0; i<this.tail; i++) {
                new_arr[i+current_buffer_size] = this.arr[i];
            }
            this.arr = new_arr;
            this.head += current_buffer_size;
            this.tail += current_buffer_size;
        }
    }

    public void addLast(int x) {
        this.size++;
        this.arr[this.tail++] = x;
        int current_buffer_size = this.arr.length;
        if (this.tail == current_buffer_size) {
            int[] new_arr = new int[current_buffer_size*2];
            for (int i=this.head; i<current_buffer_size; i++) {
                new_arr[i] = this.arr[i];
            }
            this.arr = new_arr;
        }
    }

    public int popFirst() {
        if (this.empty()) throw new NullPointerException("Deque is empty");
        int result = this.arr[this.head++];
        this.size--;
        int half_buffer_size = this.arr.length/2;
        if (this.head >= half_buffer_size) {
            int[] new_arr = new int[this.arr.length - half_buffer_size/2];
            for (int i=this.head; i<this.tail; i++) {
                new_arr[i-half_buffer_size/2] = this.arr[i];
            }
            this.arr = new_arr;
            this.head -= half_buffer_size/2;
            this.tail = this.head+this.size;
        }
        return result;
    }

    public int popLast() {
        if (this.empty()) throw new NullPointerException(("Deque is empty"));
        int result = this.arr[--this.tail];
        this.size--;
        int half_buffer_size = this.arr.length/2;
        if (this.arr.length - this.tail > half_buffer_size/2) {
            int[] new_arr = new int[this.arr.length - half_buffer_size/2];
            for (int i=this.head; i<this.tail; i++) {
                new_arr[i] = this.arr[i];
            }
            this.arr = new_arr;
        }
        return result;
    }
}
