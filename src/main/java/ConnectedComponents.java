import org.jetbrains.annotations.NotNull;

public class ConnectedComponents {
    private int[] id;
    private int count = -1;
    private boolean[] marked;
    public ConnectedComponents(Graph G) {
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];
        for (int i=0; i<G.V(); i++) {
            if (!marked[i]) {
                count++;
                dfs(G, i);
            }
        }
    }

    private void dfs(@NotNull Graph G, int v) {
        for (Integer neighbor: G.adj(v)) {
            if (!marked[neighbor]) {
                id[neighbor] = count;
                marked[neighbor] = true;
                dfs(G, neighbor);
            }
        }
    }

    public int getCount() {
        return count+1;
    }

    public int getComponent(int v) {
        return this.id[v];
    }

    public boolean isConnected(int v, int w) {
        return this.getComponent(v) == this.getComponent(w);
    }
}
