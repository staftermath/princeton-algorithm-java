import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BreadthFirstSearchTests {
    private Graph graph;
    private static final Logger LOGGER = Logger.getLogger("BFS Test Log");

    @BeforeEach
    public void setUp() {
        this.graph = new Graph(11);
        int[] edges = {0, 1, 0, 2, 0, 6, 0, 5, 5, 3, 3, 4, 4, 6, 4, 5, 7, 8, 9, 10};
        this.addEdges(graph, edges);
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

    private void addEdges(Graph G, int[] edges) {
        if (edges.length % 2 != 0) LOGGER.warning("edges must have even length");
        for (int i = 1; i < edges.length; i += 2) {
            G.addEdge(edges[i - 1], edges[i]);
        }
    }

    @Test
    public void testIsolatedVertex() {
        Graph graph = new Graph(4);
        int[] edges = {1, 2, 1, 3, 2, 3};
        this.addEdges(graph, edges);
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);
        assertTrue(bfs.hasPathTo(0));
        assertFalse(bfs.hasPathTo(3));
    }
}
