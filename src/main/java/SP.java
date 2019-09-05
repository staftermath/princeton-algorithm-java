import gherkin.lexer.Ar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class SP {
    final int s;
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    IndexMinPQ<Double> pq;

    public SP(EdgeWeightedDigraph G, int s) throws QueueFullException, QueueEmptyException {
        this.s = s;
        this.edgeTo = new DirectedEdge[G.V()];
        this.distTo = new double[G.V()];
        for (int i=0; i<G.V(); i++) {
            this.distTo[i] = Double.POSITIVE_INFINITY;
        }
        this.distTo[s] = 0;
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }

    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (this.distTo[w] > distTo[v] + e.weight()) {
            this.distTo[w] = distTo[v] + e.weight();
            this.edgeTo[w] = e;
        }
    }

    private void relax(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e: G.adj(s)) {
            int to = e.to();
            if (this.distTo[to] > this.distTo[s] + e.weight()) {
                this.distTo[to] = this.distTo[s] + e.weight();
                this.edgeTo[to] = e;
                if (pq.contains(to)) pq.changeKey(to, this.distTo[to]);
                else pq.insert(to, this.distTo[to]);
            }
        }
    }

    public double distTo(int v) {
        return this.distTo[v];
    }

    public boolean hasPathTo(int v) {
        return this.distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        ArrayList<DirectedEdge> result = new ArrayList<>();
        DirectedEdge to = this.edgeTo[v];
        result.add(to);
        while (to.from() != this.s) {
            to = this.edgeTo[to.from()];
            result.add(to);
        }
        Collections.reverse(result);
        return result;
    }
}
