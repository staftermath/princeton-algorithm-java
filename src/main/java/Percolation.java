public class Percolation {
    int top = 0;
    int height;
    int width;
    int bottom;
    private UnionFind union_find = null;
    boolean[] filled = null;


    public Percolation(int height, int width) {
        this.height = height;
        this.width = width;
        this.bottom = height*width+1;
        this.union_find = new UnionFind(this.bottom+1);
        this.filled = new boolean[this.bottom+1];
        // top and bottom are always filled to begin with
        this.filled[this.bottom] = true;
        this.filled[this.top] = true;
        // connect top with first row
        for (int i=1; i<=width; i++) {
            this.union_find.union(this.top, i);
        }
        // connect last row with bottom
        for (int i=this.bottom-width; i<this.bottom; i++) {
            this.union_find.union(i, this.bottom);
        }
    }

    public boolean isFilled(int x) {
        return filled[x];
    }

    public boolean isFilled(int height, int width) {
        return this.isFilled(height*this.width+width+1);
    }

    private void connect(int x, int y) {
        try {
            if (this.isFilled(x) && this.isFilled(y) && !this.union_find.connected(x, y)) {
                this.union_find.union(x, y);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {}
    }

    private int getIndex(Coordinate x) {
        return this.getIndex(x.getHeight(), x.getWidth());
    }

    private int getIndex(int x, int y) {
        if (x >= height || x < 0 || y >= width || y < 0)
            throw new IndexOutOfBoundsException(String.format("h: %d, w: %d is out of bounds", x, y));

        return x*this.width+y+1;
    }

    public void fill(Coordinate x) {
        int idx = this.getIndex(x);
        if (!this.isFilled(idx)) {
            this.filled[idx] = true;
            this.connect(idx-width, idx);
            this.connect(idx, idx+width);

            if ((idx-1) % this.width != 0) {
                this.connect(idx-1, idx);
            }
            if ((idx+1) % this.width != 0) {
                this.connect(idx, idx+1);
            }

        }
    }

    public boolean isConnected(int x, int y) {
        if ((x == this.top || x == this.bottom || this.isFilled(x)) &&
            (y == this.top || y == this.bottom || this.isFilled(y)))
            return this.union_find.connected(x, y);
        else return false;
    }

    public boolean isPercolated() {
        return this.isConnected(this.top, this.bottom);
    }

    public static void main(String[] args) {
        int height = Integer.parseInt(args[0]);
        int width = Integer.parseInt(args[1]);
        int iteration = Integer.parseInt(args[2]);
        long startTime = System.currentTimeMillis();
        MonteCarlo monteCarlo = new MonteCarlo(height, width);
        double percolated = 0;
        int area = height*width;
        for (int i=0; i<iteration; i++) {
            if (i%1000 == 0) System.out.printf("Iteration %d completed.\n", i);
            Percolation percolation = new Percolation(height, width);
            Coordinate[] sampled = monteCarlo.sample();
            for (int j=0; j<sampled.length; j++) {
                percolation.fill(sampled[j]);
                if (percolation.isPercolated()) {
                    percolated += j*1.0/area;
                    break;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("Percolation probability: %2.2f %% \n", percolated*100.0/iteration);
        System.out.printf("Execution time in Seconds: %.3f.\n", (endTime-startTime)/1000.0 );
    }
}
