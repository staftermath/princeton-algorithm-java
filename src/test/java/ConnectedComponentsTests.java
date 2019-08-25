import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class ConnectedComponentsTests {
    private ConnectedComponents cc;

    @BeforeEach
    public void setUp() {
        Graph graph = new Graph(11);
        int[] edges = {0, 1, 0, 2, 0, 6, 0, 5, 5, 3, 3, 4, 4, 6, 4, 5, 7, 8, 9, 10};
        for (int i = 1; i < edges.length; i += 2) {
            graph.addEdge(edges[i - 1], edges[i]);
        }
        this.cc = new ConnectedComponents(graph);
    }

    @Test
    public void test() {
        assertEquals(3, this.cc.getCount());
        assertEquals(0, this.cc.getComponent(1));
        assertEquals(1, this.cc.getComponent(8));
        assertEquals(2, this.cc.getComponent(10));
        assertTrue(this.cc.isConnected(0, 4));
        assertFalse(this.cc.isConnected(10, 8));
    }
}
