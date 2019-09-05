import org.jetbrains.annotations.NotNull;

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int either() {
        return this.v;
    }

    public int other(int v) {
        if (this.v == v) return this.w;
        else if (this.w == v) return this.v;
        else throw new RuntimeException("Inconsistent edge.");
    }

    @Override
    public int compareTo(@NotNull Edge edge) {
        return Double.compare(this.weight(), edge.weight());
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", this.v, this.w, this.weight());
    }
}
