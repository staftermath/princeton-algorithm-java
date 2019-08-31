import java.util.ArrayList;
import java.util.HashMap;

public class Digraph extends Graph {
    public Digraph(int capacity) {
        super(capacity);
    }

    @Override
    public void addEdge(int v, int w) {
        if (!this.E.containsKey(v)) this.E.put(v, new ArrayList<Integer>());
        this.E.get(v).add(w);
    }

    public Digraph reverse() {
        Digraph reversed = new Digraph(this.V());
        for (int i=0; i<this.V(); i++) {
            for (Integer v: this.adj(i)) {
                reversed.addEdge(v, i);
            }
        }
        return reversed;
    }

    @Override
    public int E() {
        int e = 0;
        for (Integer i: this.E.keySet()) {
            e += this.E.get(i).size();
        }
        return e;
    }
}
