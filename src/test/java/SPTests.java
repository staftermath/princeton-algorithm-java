import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SPTests {
    EdgeWeightedDigraph ewg;
    int[] v = {0, 0, 0, 1, 1, 1, 7, 7, 4, 4, 4, 3, 2, 2, 5, 5};
    int[] w = {1, 7, 4, 3, 2, 7, 2, 5, 7, 5, 6, 6, 3, 6, 2, 6};
    double[] e = {5, 8, 9, 15, 12, 4, 7, 6, 5, 4, 20, 9, 3, 11, 1, 13};

    private void addEdges(int[] v, int[] w, double[] e, EdgeWeightedDigraph G) {
        for (int i=0; i<v.length; i++) {
            G.addEdge(new DirectedEdge(v[i], w[i], e[i]));
        }
    }

    @BeforeEach
    public void setUp() {
        ewg = new EdgeWeightedDigraph(this.v.length);
        this.addEdges(this.v, this.w, this.e, ewg);
    }

    @ParameterizedTest
    @CsvSource({"7,8", "6,25", "2,14", "3,17", "5,13"})
    public void testSP(int to, double distance) throws QueueEmptyException, QueueFullException {
        SP sp = new SP(this.ewg, 0);
        assertEquals(distance, sp.distTo(to), 0.00001);
    }

}
