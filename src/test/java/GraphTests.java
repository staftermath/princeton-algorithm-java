import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class GraphTests {

    @Test
    public void testGraph() {
        Graph graph = new Graph(5);
        int[] edges = {0, 1, 0, 2, 1, 2, 3, 4, 2, 4};
        for (int i=1; i<edges.length; i += 2) {
            graph.addEdge(edges[i-1], edges[i]);
        }
        Assert.assertEquals(5, graph.E());
        ArrayList<Integer> adjecentOf0 = new ArrayList<Integer>(2);
        adjecentOf0.add(1);
        adjecentOf0.add(2);
        Assert.assertEquals(adjecentOf0, graph.adj(0));

        Assert.assertEquals(2, Graph.degree(graph, 0));
        Assert.assertEquals(3, Graph.degree(graph, 2));
        Assert.assertEquals(3, Graph.maxDegree(graph));
        Assert.assertEquals(2, Graph.averageDegree(graph), 0.00001);

        Assert.assertEquals(0, Graph.numberOfSelfLoops(graph));
        graph.addEdge(0, 0);
        Assert.assertEquals(1, Graph.numberOfSelfLoops(graph));
        graph.deleteEdge(0, 0);
        Assert.assertEquals(0, Graph.numberOfSelfLoops(graph));
        graph.deleteEdge(2, 0);
        Assert.assertEquals(2, Graph.degree(graph, 2));
    }


}
