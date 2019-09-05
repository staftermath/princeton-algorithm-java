
public class MST {
    private Queue<Edge> mst;
    private boolean[] marked;
    private MinPQ<Edge> pq;

    public MST(EdgeWeightedGraph G) throws QueueFullException, QueueEmptyException {
        this.marked = new boolean[G.V()];
        pq = new MinPQ<Edge>(G.E());
        mst = new Queue<Edge>();
        int i=0;
        while (mst.size()<G.V()-1) {
            if (!this.marked[i]) {
                marked[i] = true;
                for (Edge e: G.adj(i)) {
                    if (!this.marked[e.other(i)]) pq.insert(e);
                }
                Edge newEdge = (Edge) pq.popTop();
                while (this.marked[newEdge.either()] && this.marked[newEdge.other(newEdge.either())]) {
                    newEdge = (Edge) pq.popTop();
                }
                mst.enqueue(newEdge);
                i = newEdge.either();
                if (this.marked[i]) i = newEdge.other(i);
            }
        }

    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0;
        for (Edge e: mst) {
            weight += e.weight();
        }
        return weight;
    }
}
