import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class MSTTests {
    EdgeWeightedGraph ewg;
    int[] v = {0, 2, 1, 0, 5, 1, 1, 2, 4, 1, 4, 0, 6, 3, 6, 6};
    int[] w = {7, 3, 7, 2, 7, 3, 5, 7, 5, 2, 7, 4, 2, 6, 0, 4};
    double[] e = {0.16, 0.17, 0.19, 0.26, 0.28, 0.29, 0.32, 0.34, 0.35, 0.36, 0.37, 0.38, 0.40, 0.52, 0.58, 0.93};

    private void addEdges(int[] v, int[] w, double[] e, EdgeWeightedGraph G) {
        for (int i=0; i<v.length; i++) {
            G.addEdge(new Edge(v[i], w[i], e[i]));
        }
    }

    @BeforeEach
    public void setUp() {
        ewg = new EdgeWeightedGraph(8);
        this.addEdges(this.v, this.w, this.e, ewg);
    }

    @Test
    public void testMST() throws QueueEmptyException, QueueFullException {
        MST mst = new MST(this.ewg);
        assertEquals(0.16+0.17+0.19+0.26+0.28+0.35+0.40, mst.weight(), 0.00001);
    }
}
