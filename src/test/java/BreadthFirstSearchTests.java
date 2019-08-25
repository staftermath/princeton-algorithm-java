import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;

public class BreadthFirstSearchTests {
    private Graph graph;
    private static final Logger LOGGER = Logger.getLogger("BFS Test Log");

    @BeforeEach
    public void setUp() {
        this.graph = new Graph(11);
        int[] edges = {0, 1, 0, 2, 0, 6, 0, 5, 5, 3, 3, 4, 4, 6, 4, 5, 7, 8, 9, 10};
        for (int i = 1; i < edges.length; i += 2) {
            this.graph.addEdge(edges[i - 1], edges[i]);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void testHasPathToReturnTrue(int vertex) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);
        Assert.assertTrue(bfs.hasPathTo(vertex));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10})
    public void testHasPathToReturnFalse(int vertex) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);
        Assert.assertFalse(bfs.hasPathTo(vertex));
    }
}
