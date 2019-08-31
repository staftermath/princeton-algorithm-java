import java.util.Stack;

public class BreadthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private boolean hasCycle;

    public BreadthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s, s);
    }

    /**
     *
     * @param G graph
     * @param v target node
     * @param u parent node
     */
    private void bfs(Graph G, int v, int u) {
        if (!marked[v]) {
            Deque queue = new Deque();
            queue.addFirst(v);
            marked[v] = true;
            while (!queue.empty()) {
                int s = queue.popFirst();
                for (Integer i: G.adj(s)) {
                    if (!marked[i]) {
                        marked[i] = true;
                        edgeTo[i] = s;
                        queue.addLast(i);
                    } else if (i != u){
                        this.hasCycle = true;
                    }
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return this.marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) return null;
        Stack<Integer> stack = new Stack<>();
        for (int i=v; i!=this.s; i=edgeTo[i]) {
            stack.push(i);
        }
        return stack;
    }

    public boolean hasCycle() {
        return this.hasCycle;
    }
}
