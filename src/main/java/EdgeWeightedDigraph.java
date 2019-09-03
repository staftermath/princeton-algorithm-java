import org.jetbrains.annotations.NotNull;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i=0; i<V; i++) {
            this.adj[i] = new Bag<DirectedEdge>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(DirectedEdge e) {
        this.adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return this.adj[v];
    }
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> result = new Bag<DirectedEdge>();
        for (int i=0; i<this.V; i++) {
            for (DirectedEdge e: this.adj(i)) {
                result.add(e);
            }
        }
        return result;
    }
}

class DirectedEdge implements Comparable<DirectedEdge>{
    final int from;
    final int to;
    final double weight;

    DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    int from() {
        return this.from;
    }

    int to() {
        return this.to;
    }

    double weight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return String.format("%d->%d (%.5f)", from, to, weight);
    }

    @Override
    public int compareTo(@NotNull DirectedEdge directedEdge) {
        return Double.compare(this.weight, directedEdge.weight);
    }
}