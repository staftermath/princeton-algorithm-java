import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    int V;
    HashMap<Integer, ArrayList<Integer>> E;
    public Graph(int V) {
        this.V = V;
        this.E = new HashMap<Integer, ArrayList<Integer>>(V);
    }

    public int V() {
        return this.V;
    }

    public int E() {
        int e = 0;
        for (Integer i: this.E.keySet()) {
            e += this.E.get(i).size();
        }
        return e/2;
    }

    public void addEdge(int v, int w) {
        if (!this.E.containsKey(v)) {
            this.E.put(v, new ArrayList<Integer>());
        }
        if (!this.E.containsKey(w)) {
            this.E.put(w, new ArrayList<Integer>());
        }
        this.E.get(v).add(w);
        this.E.get(w).add(v);
    }

    public boolean deleteEdge(int v, int w) {
        if (!this.E.containsKey(v)) return false;
        int index = this.E.get(v).indexOf(w);
        if (index == -1) return false;
        this.E.get(v).remove(index);
        return true;
    }

    public Iterable<Integer> adj(int v) {
        return this.E.getOrDefault(v, new ArrayList<Integer>());
    }

    public static int degree(Graph G, int v) {
        int degree = 0;
        for (Integer i: G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for (int i=0; i<G.V(); i++) {
            int thisDegree = Graph.degree(G, i);
            if (thisDegree > max) max = thisDegree;
        }
        return max;
    }

    public static double averageDegree(Graph G) {
        return G.E()*2.0/G.V();
    }

    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (Integer i: G.E.keySet()) {
            for (Integer j: G.E.get(i)) {
                if (j.equals(i)) count++;
            }
        }
        return count/2;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.V()).append(" vertices ").append(E).append(" edges\n");
        for (int v=0; v<this.V(); v++) {
            s.append(v).append(": ");
            for (int w: this.adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
