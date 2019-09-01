
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Edge>[]) new Bag[V];
        for (int i=0; i<V; i++) {
            this.adj[i] = new Bag<Edge>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        this.adj[v].add(e);
        this.adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return this.adj[v];
    }
    public Iterable<Edge> edges() {
        Bag<Edge> result = new Bag<Edge>();
        for (int i=0; i<this.V; i++) {
            for (Edge e: this.adj(i)) {
                if (e.other(i) > i) result.add(e);
            }
        }
        return result;
    }


}
