import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;


public class EdgeWeightedGraphTests {
    EdgeWeightedGraph ewg;
    int[] v = {0, 0, 0, 1, 1, 1, 7, 7, 4, 4, 4, 3, 2, 2, 5, 5};
    int[] w = {1, 7, 4, 3, 2, 7, 2, 5, 7, 5, 6, 6, 3, 6, 2, 6};
    double[] e = {5, 8, 9, 15, 12, 4, 7, 6, 5, 4, 20, 9, 3, 11, 1, 13};

    @BeforeEach
    public void setUp() {
        ewg = new EdgeWeightedGraph(this.v.length);
        this.addEdges(this.v, this.w, this.e, ewg);
    }

    @ParameterizedTest
    @CsvSource({"0,3","1,4","5,4"})
    public void testAdj(int vertex, int sizeOfNeighbor) {
        int count = 0;
        for (Edge e: this.ewg.adj(vertex)) {
            count++;
        }
        assertEquals(sizeOfNeighbor, count);
    }

    private void addEdges(int[] v, int[] w, double[] e, EdgeWeightedGraph G) {
        for (int i=0; i<v.length; i++) {
            G.addEdge(new Edge(v[i], w[i], e[i]));
        }
    }
}
