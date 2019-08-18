

public class UnionFind {
    private int[] index = null;
    public UnionFind (int n) {
        index = new int[n];
        for (int i = 0; i<index.length; i++) {
            index[i] = i;
        }
    }
    public void union(int x, int y) {
        if (getIndex(x) != getIndex(y)) {
            int x_root = root(x);
            int y_root = root(y);
            index[x_root] = y_root;
        }
    }

    public int root(int x) {
        while (x != getIndex(x)) {
            index[x] = getIndex(getIndex(x));
            x = getIndex(x);
        }
        return x;
    }

    public boolean isConnected(int x, int y) {
        return root(x) == root(y);
    }

    public int getIndex(int x) {
        return index[x];
    }
}
