/**
 * Implement depth-first search on graph. It also contains method to find if the graph has cycle.
 */

import java.util.Stack;

public class DepthFirstSearch extends Search {
    private boolean[] marked;
    private int count;
    private int[] edgeTo;
    public int source;
    private boolean hasCycle;

    public DepthFirstSearch(Graph graph, int s) {
        super(graph, s);
        source = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        if (graph.E.containsKey(s)) {
            dfs(graph, s, s);
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }

    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        count++;
        for (Integer i: graph.adj(v)) {
            if (!marked[i]) {
                edgeTo[i] = v;
                dfs(graph, i, v);
            } else if (i != u){
                this.hasCycle = true;
            }
        }
    }

    public boolean hasPathTo(int v) {
        return this.marked(v);
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x=v; x!=source; x=edgeTo[x]) {
            path.push(x);
        }
        path.push(source);
        return path;
    }

    public boolean hasCycle() {
        return this.hasCycle;
    }
}
