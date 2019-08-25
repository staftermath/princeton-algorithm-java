import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;


public class DepthFirstSearchTests {
    private Graph graph;
    private static final Logger LOGGER = Logger.getLogger("DFS Test Log");

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting Up");
        this.graph = new Graph(11);
        int[] edges = {0, 1, 0, 2, 0, 6, 0, 5, 5, 3, 3, 4, 4, 6, 4, 5, 7, 8, 9, 10};
        for (int i = 1; i < edges.length; i += 2) {
            this.graph.addEdge(edges[i - 1], edges[i]);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void testHasPathToReturnTrue(int vertex) {
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        assertTrue(dfs.hasPathTo(vertex));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10})
    public void testHasPathToReturnFalse(int vertex) {
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        assertFalse(dfs.hasPathTo(vertex));
    }

    @Test
    public void testPathTo() {
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        Iterator<Integer> path = dfs.pathTo(6).iterator();
        Integer[] expected = {6, 0};
        Iterator<Integer> iterExpected = Arrays.asList(expected).iterator();
        while (path.hasNext()) {
            assertTrue(iterExpected.hasNext());
            assertEquals(iterExpected.next(), path.next());
        }
        assertFalse(iterExpected.hasNext());
    }

    @Test
    public void testHasCycle() {
        DepthFirstSearch dfsHasCycle = new DepthFirstSearch(graph, 0);
        assertTrue(dfsHasCycle.hasCycle());
        Graph graphWithNoCycle = new Graph(11);
        int[] edges = {0, 1, 0, 2, 0, 6, 0, 5, 5, 3, 3, 4, 7, 8, 9, 10};
        for (int i = 1; i < edges.length; i += 2) {
            graphWithNoCycle.addEdge(edges[i - 1], edges[i]);
        }
        DepthFirstSearch dfsNoCycle = new DepthFirstSearch(graphWithNoCycle, 0);
        assertFalse(dfsNoCycle.hasCycle());
    }
}
