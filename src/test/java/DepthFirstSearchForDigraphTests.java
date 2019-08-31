import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DepthFirstSearchForDigraphTests {
    private Digraph graph;
    private static final Logger LOGGER = Logger.getLogger("DFS Test For Digraph Log");

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting Up");
        this.graph = new Digraph(11);
        int[] edges = {0, 1, 0, 2, 0, 6, 0, 5, 5, 3, 3, 4, 4, 6, 4, 5, 7, 8, 9, 10};
<<<<<<< HEAD
        this.addEdges(graph, edges);
=======
        for (int i = 1; i < edges.length; i += 2) {
            this.graph.addEdge(edges[i - 1], edges[i]);
        }
>>>>>>> master
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
<<<<<<< HEAD
        this.addEdges(graphWithNoCycle, edges);
        DepthFirstSearch dfsNoCycle = new DepthFirstSearch(graphWithNoCycle, 0);
        assertFalse(dfsNoCycle.hasCycle());
    }

    private void addEdges(Graph G, int[] edges) {
        if (edges.length % 2 != 0) LOGGER.warning("edges must have even length");
        for (int i = 1; i < edges.length; i += 2) {
            G.addEdge(edges[i - 1], edges[i]);
        }
    }

    @Test
    public void testIsolatedVertex() {
        Digraph graph = new Digraph(4);
        int[] edges = {1, 2, 1, 3, 2, 3};
        this.addEdges(graph, edges);
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        assertTrue(dfs.hasPathTo(0));
        assertFalse(dfs.hasPathTo(3));
=======
        for (int i = 1; i < edges.length; i += 2) {
            graphWithNoCycle.addEdge(edges[i - 1], edges[i]);
        }
        DepthFirstSearch dfsNoCycle = new DepthFirstSearch(graphWithNoCycle, 0);
        assertFalse(dfsNoCycle.hasCycle());
>>>>>>> master
    }
}
